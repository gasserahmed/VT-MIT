package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

    private BookDao bookDao;
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private LineItemDao lineItemDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setLineItemDao(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        Order order = orderDao.findByOrderId(orderId);
        Customer customer = customerDao.findByCustomerId(order.getCustomerId());
        List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
        List<Book> books = lineItems
                .stream()
                .map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
                .collect(Collectors.toList());
        return new OrderDetails(order, customer, lineItems, books);
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {
        validateCustomer(customerForm);
        validateCart(cart);
        try (Connection connection = JdbcUtils.getConnection()) {
            Date date = getDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
            return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    date, cart, connection);
        } catch (SQLException e) {
            throw new BookstoreDbException("Error during close connection for customer order", e);
        } catch (ParseException e) {
            throw new ApiException("Error during parse credit card date for customer order", e);
        }
    }

    private long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, Date date,
            ShoppingCart cart, Connection connection) {
        try {
            connection.setAutoCommit(false);
            long customerId = customerDao.create(
                    connection, name, address, phone, email,
                    ccNumber, date);
            long customerOrderId = orderDao.create(
                    connection,
                    cart.getComputedSubtotal() + cart.getSurcharge(),
                    generateConfirmationNumber(), customerId);
            for (ShoppingCartItem item : cart.getItems()) {
                lineItemDao.create(connection, customerOrderId,
                        item.getBookId(), item.getQuantity());
            }
            connection.commit();
            return customerOrderId;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new BookstoreDbException("Failed to roll back transaction", e1);
            }
            return 0;
        }
    }

    private void validateCustomer(CustomerForm customerForm) {
        String name = customerForm.getName();
        String address = customerForm.getAddress();
        String phone = customerForm.getPhone();
        String email = customerForm.getEmail();
        String ccNumber = customerForm.getCcNumber();
        String ccExpiryMonth = customerForm.getCcExpiryMonth();
        String ccExpiryYear = customerForm.getCcExpiryYear();


        if (!nameIsValid(name)) {
            throw new ApiException.InvalidParameter("Invalid name field");
        }

        if (!addressIsValid(address)) {
            throw new ApiException.InvalidParameter("Invalid address field");
        }

        if (!phoneIsValid(phone)) {
            throw new ApiException.InvalidParameter("Invalid phone field");
        }

        if (!emailIsValid(email)) {
            throw new ApiException.InvalidParameter("Invalid email field");
        }

        if (!ccNumberIsValid(ccNumber)) {
            throw new ApiException.InvalidParameter("Invalid credit card number field");
        }

        if (!ccExpiryDateIsValid(ccExpiryMonth, ccExpiryYear)) {
            throw new ApiException.InvalidParameter("Invalid expiry date");

        }
    }

    private boolean nameIsValid(String name) {
        if (stringIsNullOrEmpty(name)) {
            return false;
        }

        return name.length() >= 4 && name.length() <= 45;
    }

    private boolean addressIsValid(String address) {
        if (stringIsNullOrEmpty(address)) {
            return false;
        }

        return address.length() >= 4 && address.length() <= 45;
    }

    private boolean phoneIsValid(String phone) {
        if (stringIsNullOrEmpty(phone)) {
            return false;
        }

        phone = phone.replaceAll("[\\s-()]", ""); // remove all spaces, dashes, and parens
        return phone.length() == 10;
    }

    private boolean emailIsValid(String email) {
        if (stringIsNullOrEmpty(email)) {
            return false;
        }

        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).matches();
    }

    private boolean ccNumberIsValid(String ccNumber) {
        if (stringIsNullOrEmpty(ccNumber)) {
            return false;
        }

        ccNumber = ccNumber.replaceAll("[\\s-]", ""); // remove all spaces, dashes, and parens
        return ccNumber.length() >= 14 && ccNumber.length() <= 16;
    }

    private boolean ccExpiryDateIsValid(String ccExpiryMonth, String ccExpiryYear) {
        if (stringIsNullOrEmpty(ccExpiryMonth) || stringIsNullOrEmpty(ccExpiryYear)) {
            return false;
        }

        int ccExpiryYearInt = Integer.parseInt(ccExpiryYear);
        int ccExpiryMonthInt = Integer.parseInt(ccExpiryMonth);
        if (ccExpiryMonthInt < 1 || ccExpiryMonthInt > 12) {
            return false;
        }

        YearMonth currentYearMonth = YearMonth.now();
        YearMonth ccExpiryYearMonth = YearMonth.of(ccExpiryYearInt, ccExpiryMonthInt);
        return currentYearMonth.compareTo(ccExpiryYearMonth) <= 0;

    }

    private void validateCart(ShoppingCart cart) {
        if (cart.getItems().size() <= 0) {
            throw new ApiException.InvalidParameter("Cart is empty.");
        }

        cart.getItems().forEach(item -> {
            if (item.getQuantity() < 1 || item.getQuantity() > 99) {
                throw new ApiException.InvalidParameter("Invalid quantity");
            }

            Book databaseBook = bookDao.findByBookId(item.getBookId());
            if (item.getBookForm().getPrice() != databaseBook.getPrice()) {
                throw new ApiException.InvalidParameter("Item's price does not match its corresponding price in the database.");
            }

            if (item.getBookForm().getCategoryId() != databaseBook.getCategoryId()) {
                throw new ApiException.InvalidParameter("Item's category does not match its corresponding category in the database.");
            }
        });
    }

    private boolean stringIsNullOrEmpty(String value) {
        return value == null || value.equals("");
    }

    private Date getDate(String monthString, String yearString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM");
        return simpleDateFormat.parse(yearString + "/" + monthString);
    }

    private int generateConfirmationNumber() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(999999999);
    }
}
