
package business;

import business.book.BookDao;
import business.book.BookDaoJdbc;
import business.category.CategoryDao;
import business.category.CategoryDaoJdbc;

public class ApplicationContext {

    private BookDao bookDao;

    private CategoryDao categoryDao;

    public static ApplicationContext INSTANCE = new ApplicationContext();

    private ApplicationContext() {
        categoryDao = new CategoryDaoJdbc();
        bookDao = new BookDaoJdbc();
    }

    public BookDao getBookDao() { return bookDao; }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}
