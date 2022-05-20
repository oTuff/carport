package dat.startcode.model.persistence;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public OrderMapperTest(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        System.out.println(connectionPool);
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Remove all rows from all tables
                stmt.execute("delete from user");
                // Indsæt et par brugere
                stmt.execute("insert into user (username, password, role) " +
                        "values ('user','1234','user'),('admin','1234','admin'), ('ben','1234','user')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void insertOrder() {
        Order order = new Order(0, "a@a.dk", 400, 200, 100, 0, false);


        OrderMapper orderMapper = new OrderMapper(connectionPool);
        System.out.println(orderMapper.insertOrder(order));
        //assertEquals(true, orderMapper.insertOrder(order));
    }

    @Test
    void retrieveAllOrders() {
    }

    @Test
    void retrieveMyOrders() {
    }

    @Test
    void retrieveOrder() {
    }

    @Test
    void acceptOrder() {
    }

    @Test
    void deleteOrder() {
    }
}