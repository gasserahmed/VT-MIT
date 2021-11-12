
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;
import business.order.DefaultOrderService;
import business.order.OrderService;

public class ApplicationContext {

    private BookDao bookDao;

    private CategoryDao categoryDao;

    private final OrderService orderService;

    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        categoryDao = new CategoryDaoJdbc();
        bookDao = new BookDaoJdbc();
        orderService = new DefaultOrderService();
        ((DefaultOrderService)orderService).setBookDao(bookDao);
    }

    public BookDao getBookDao() { return bookDao; }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public OrderService getOrderService() { return orderService; }
}
