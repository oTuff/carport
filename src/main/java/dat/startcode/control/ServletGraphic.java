package dat.startcode.control;

import com.mysql.cj.Session;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.Calculator;
import dat.startcode.model.services.SVG;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

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
        // Opret ordre
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        Order order = new Order((User) request.getSession().getAttribute("user"), carportWidth, carportLength);


        ProductMapper productMapper = new ProductMapper(connectionPool);
        ArrayList<Product> products = null;
        try {
            products = productMapper.retrieveProduct();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        Calculator calc = new Calculator(order,products);

        int rafterQuantity = order.getPartsListLines().get(5).getQuantity();

        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 50);

        svg.addRect(0, 15, 4.5, carportLength);
        svg.addRect(0, carportWidth - 15, 4.5, carportLength);


        for (int x = 0; x < rafterQuantity; x++) {
            svg.addRect(100 + 50 * x, 0, carportWidth, 4.5);
        }
        request.setAttribute("svgdrawing", svg.toString());
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
