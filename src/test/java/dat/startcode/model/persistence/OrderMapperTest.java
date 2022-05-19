package dat.startcode.model.persistence;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public OrderMapperTest(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Test
    void insertOrder() {
        Order order = new Order(0, "a@a.dk", 400, 200, 100, 0, false);
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        assertEquals(true, orderMapper.insertOrder(order));
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