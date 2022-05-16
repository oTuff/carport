package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletAcceptOrder", value = "/servletacceptorder")
public class ServletAcceptOrder extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String partslistOrderId = request.getParameter("partslistOrderId");
        System.out.println("partslistorderid: " + partslistOrderId);
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        try {
            orderMapper.acceptOrder(Integer.parseInt(partslistOrderId));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/servletadminpanel");
    }
}
