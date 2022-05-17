package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeleteOrder", value = "/servletdeleteorder")
public class ServletDeleteOrder extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String partslistOrderId = request.getParameter("partslistOrderId");
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        orderMapper.deleteOrder(Integer.parseInt(partslistOrderId));
        response.sendRedirect(request.getContextPath() + "/servletadminpanel");
    }
}
