package dat.startcode.control;

import com.mysql.cj.Session;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletGraphic", value = "/ServletGraphic")
public class ServletGraphic extends HttpServlet {
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
        String carportWidth = request.getParameter("carportWidth");
        String carportLength = request.getParameter("carportLength");
        String address = request.getParameter("address");
        if(address != null){
            UserMapper userMapper = new UserMapper(connectionPool);
            try {
                User userSession = (User) request.getSession().getAttribute("user");
                if(userSession != null){
                    if(!address.equals(userSession.getAddress())){
                        userMapper.updateAddress(userSession.getEmail(), address);
                        User user = new User(userSession.getEmail(), userSession.getFullName(), userSession.getBalance(), address, userSession.getZipNr(), userSession.getRole());
                        request.getSession().setAttribute("user", user);
                    }
                }
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("WEB-INF/graphic.jsp").forward(request, response);
    }
}
