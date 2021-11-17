package business.order;

import business.book.Book;
import business.customer.Customer;

import java.util.Collections;
import java.util.List;

public class OrderDetails {

    private Order order;
    private Customer customer;
    private List<LineItem> lineItems;
    private List<Book> books;

    public OrderDetails(Order order, Customer customer,
                        List<LineItem> lineItems, List<Book> books) {
        this.order = order;
        this.customer = customer;
        this.lineItems = lineItems;
        this.books = books;
    }

    public Order getOrder() {
        return order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<LineItem> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }
}
