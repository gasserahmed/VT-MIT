package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.YearMonth;
import java.util.regex.Pattern;

public class DefaultOrderService implements OrderService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        // NOTE: THIS METHOD PROVIDED NEXT PROJECT
        return null;
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {
        validateCustomer(customerForm);
        validateCart(cart);

        // NOTE: MORE CODE PROVIDED NEXT PROJECT

        return -1;
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

        YearMonth currentYearMonth = YearMonth.now();
        YearMonth ccExpiryYearMonth = YearMonth.of(Integer.parseInt(ccExpiryYear), Integer.parseInt(ccExpiryMonth));
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
}
