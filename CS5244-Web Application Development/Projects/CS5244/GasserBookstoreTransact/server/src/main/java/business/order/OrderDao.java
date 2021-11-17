package business.order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {

    public long create(Connection connection, int amount, int confirmationNumber, long customerId);

    public List<Order> findAll();

    public Order findByOrderId(long orderId);

    public List<Order> findByCustomerId(long customerId);
}
