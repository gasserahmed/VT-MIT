package business.order;

import java.sql.Connection;
import java.util.List;

/**
 * Created by gregory on 4/7/17.
 */
public interface LineItemDao {

    public void create(Connection connection, long orderId, long bookId, int quantity);

    public List<LineItem> findByOrderId(long orderId);
}
