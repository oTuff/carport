package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletCreateUser", value = "/servletcreateuser")
public class ServletCreateUser extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        int zipNr = Integer.parseInt(request.getParameter("zipnr"));

        UserMapper userMapper = new UserMapper(connectionPool);
        String role = "user";
        try {
            userMapper.createUser(email, fullName, password, address, zipNr, role);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
