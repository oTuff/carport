package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;
import dat.startcode.model.persistence.PartsListLineMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletRequestSent", value = "/ServletRequestSent")
public class ServletRequestSent extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Order order = (Order) request.getSession().getAttribute("order");
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        PartsListLineMapper partsListLineMapper = new PartsListLineMapper(connectionPool);

        orderMapper.insertOrder(order);

        for (PartsListLine p : order.getPartsListLines()) {
            try {
                System.out.println(p);
                partsListLineMapper.createPartsListLine(order,p);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }

        System.out.println(order.getEmail() + "\n" +
                order.getWidth() + "\n" +
                order.getLength() + "\n" +
                order.getOrderPrice());


        request.getRequestDispatcher("/WEB-INF/requestsent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
