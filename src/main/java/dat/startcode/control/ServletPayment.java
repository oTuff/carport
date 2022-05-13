package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;

import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ServletPayment", urlPatterns = {"/servletpayment"})
public class ServletPayment extends HttpServlet {
    private ConnectionPool connectionPool;
    private List<Order> myOrderList;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        OrderMapper orderMapper = new OrderMapper(connectionPool);

        String orderId = request.getParameter("partslistOrderId");
        try {
            request.setAttribute("orderId", orderMapper.retrieveOrder(Integer.parseInt(orderId)));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/payment.jsp").forward(request, response);
    }
}

