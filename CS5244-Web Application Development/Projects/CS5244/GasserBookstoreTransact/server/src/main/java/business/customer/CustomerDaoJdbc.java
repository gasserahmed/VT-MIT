package business.customer;

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

public class CustomerDaoJdbc implements CustomerDao {

    private static final String CREATE_CUSTOMER_SQL =
            "INSERT INTO `customer` (name, address, phone, email, cc_number, cc_exp_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL_SQL =
            "SELECT customer_id, name, address, " +
                    "phone, email, cc_number, cc_exp_date " +
                    "FROM customer";

    private static final String FIND_BY_CUSTOMER_ID_SQL =
            "SELECT customer_id, name, address, " +
                    "phone, email, cc_number, cc_exp_date " +
                    "FROM customer WHERE customer_id = ?";

    @Override
    public long create(Connection connection,
                       String name,
                       String address,
                       String phone,
                       String email,
                       String ccNumber,
                       Date ccExpDate) {
        try (PreparedStatement statement =
                     connection.prepareStatement(CREATE_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, ccNumber);
            statement.setDate(6, new java.sql.Date(ccExpDate.getTime()));
            int affected = statement.executeUpdate();
            if (affected != 1) {
                throw new BookstoreUpdateDbException("Failed to insert a customer, affected row count = " + affected);
            }
            long customerId;
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                customerId = rs.getLong(1);
            } else {
                throw new BookstoreUpdateDbException("Failed to retrieve customerId auto-generated key");
            }
            return customerId;
        } catch (SQLException e) {
            throw new BookstoreUpdateDbException("Encountered problem creating a new customer ", e);
        }

    }

    @Override
    public List<Customer> findAll() {
        List<Customer> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Customer c = readCustomer(resultSet);
                result.add(c);
            }
        } catch (SQLException e) {
            throw new BookstoreQueryDbException("Encountered problem finding all categories", e);
        }

        return result;

    }

    @Override
    public Customer findByCustomerId(long customerId) {

        Customer result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CUSTOMER_ID_SQL)) {
            statement.setLong(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = readCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new BookstoreQueryDbException("Encountered problem finding customer " + customerId, e);
        }
        return result;
    }

    private Customer readCustomer(ResultSet resultSet) throws SQLException {
        long customerId = resultSet.getLong("customer_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String ccNumber = resultSet.getString("cc_number");
        Date ccExpDate = resultSet.getDate("cc_exp_date");
        return new Customer(customerId, name, address, phone, email, ccNumber, ccExpDate);
    }
}
