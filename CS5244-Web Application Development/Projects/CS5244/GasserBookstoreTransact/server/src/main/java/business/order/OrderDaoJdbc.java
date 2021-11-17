package business.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import business.BookstoreDbException.BookstoreQueryDbException;
import business.BookstoreDbException.BookstoreUpdateDbException;

import static business.JdbcUtils.getConnection;

public class OrderDaoJdbc implements OrderDao {

    private static final String CREATE_ORDER_SQL =
            "INSERT INTO customer_order (amount, confirmation_number, customer_id) " +
                    "VALUES (?, ?, ?)";

    private static final String FIND_ALL_SQL =
            "SELECT customer_order_id, customer_id, amount, date_created, confirmation_number " +
                    "FROM customer_order";

    private static final String FIND_BY_CUSTOMER_ID_SQL =
            "SELECT customer_order_id, customer_id, amount, date_created, confirmation_number " +
                    "FROM customer_order WHERE customer_id = ?";

    private static final String FIND_BY_CUSTOMER_ORDER_ID_SQL =
            "SELECT customer_order_id, customer_id, amount, date_created, confirmation_number " +
                    "FROM customer_order WHERE customer_order_id = ?";

    @Override
    public long create(Connection connection, int amount, int confirmationNumber, long customerId) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, amount);
            statement.setInt(2, confirmationNumber);
            statement.setLong(3, customerId);
            int affected = statement.executeUpdate();
            if (affected != 1) {
                throw new BookstoreUpdateDbException("Failed to insert an order, affected row count = " + affected);
            }
            long customerOrderId;
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                customerOrderId = rs.getLong(1);
            } else {
                throw new BookstoreUpdateDbException("Failed to retrieve customerOrderId auto-generated key");
            }
            return customerOrderId;
        } catch (SQLException e) {
            throw new BookstoreUpdateDbException("Encountered problem creating a new customer ", e);
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                Order order = readOrder(resultSet);
                result.add(order);
            }
        } catch (SQLException e) {
            throw new BookstoreQueryDbException("Encountered problem finding all orders", e);
        }
        return result;
    }

    @Override
    public Order findByOrderId(long orderId) {
        Order result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CUSTOMER_ORDER_ID_SQL)) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = readOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new BookstoreQueryDbException("Encountered problem finding customer order id = " + orderId, e);
        }
        return result;
    }

    @Override
    public List<Order> findByCustomerId(long customerId) {
        List<Order> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CUSTOMER_ID_SQL)) {
            statement.setLong(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = readOrder(resultSet);
                    result.add(order);
                }
            }
        } catch (SQLException e) {
            throw new BookstoreQueryDbException("Encountered problem finding customer id = " + customerId, e);
        }
        return result;
    }

    private Order readOrder(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("customer_order_id");
        int amount = resultSet.getInt("amount");
        Date dateCreated = resultSet.getTimestamp("date_created");
        int confirmationNumber = resultSet.getInt("confirmation_number");
        long customerId = resultSet.getLong("customer_id");
        return new Order(orderId, amount, dateCreated, confirmationNumber, customerId);
    }

}
