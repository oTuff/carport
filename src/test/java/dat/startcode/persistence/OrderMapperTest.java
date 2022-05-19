package dat.startcode.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest extends HttpServlet {

    private final static String USER = "carport";
    private final static String PASSWORD = "carport";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    private static OrderMapper orderMapper;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        orderMapper = new OrderMapper(connectionPool);
    }

    @BeforeEach
    void setUp() {      // Deleting all data from the tables
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Remove all rows from all tables
                stmt.execute("DELETE FROM partslist_order");
                stmt.execute("DELETE FROM user");
                stmt.execute("INSERT INTO `user` VALUES " +           // Inserting a few values into user
                        "('a@a.dk', 'Adminbruger', '1234', 100000, 'Nørgaardsvej 30', 2800, 'admin'), " +
                        "('b@b.dk', 'Testbruger', '1234', 100000, 'Nørgaardsvej 30', 2800, 'user')");
                stmt.execute("INSERT INTO `partslist_order` VALUES " +        // Inserting a few values into order
                        "(1, 'b@b.dk', 600, 720, 10000, 0, 1)," +
                        "(2, 'c@c.dk', 600, 780, 5000, 0, 1)");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }


    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void insertOrder() throws DatabaseException {
        Order order = new Order(0, "a@a.dk", 400, 200, 100, 0, false);
        assertNotNull(order); // Insert new order and checking if that it's not null
        orderMapper = new OrderMapper(connectionPool);
        assertEquals(true, orderMapper.insertOrder(order));
}

    @Test
    void retrieveAllOrders() throws DatabaseException {
        List<Order> orders = orderMapper.retrieveAllOrders();
        assertEquals(2, orders.size());
    }

    @Test
    void retrieveOrder() throws DatabaseException { // Remember to take an order in your own partslist_order table. (FIX)
        assertEquals(new Order(1, "b@b.dk", 600, 720, 10000, 0, true), orderMapper.retrieveOrder(1));
    }

    @Test
    void acceptOrder() throws DatabaseException {

    }

    @Test
    void deleteOrder() throws DatabaseException {
    }
}