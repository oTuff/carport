package dat.startcode.control;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletMyOrders", value = "/servletmyorders")

public class ServletMyOrders extends HttpServlet {
    private ConnectionPool connectionPool;
    private List<Order> myOrdersList;


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
        myOrdersList = new ArrayList<>();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        List<Order> myOrderList = null;
        User myOrdersSession = (User) request.getSession().getAttribute("user");
        try {
            myOrderList = orderMapper.retrieveMyOrders(myOrdersSession);
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("myorderlist", myOrderList);
        request.getRequestDispatcher("WEB-INF/myorders.jsp").forward(request, response);
    }
}
