package dat.startcode.control;

import com.mysql.cj.Session;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.*;
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
        doPost(request, response);

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

        Calculator calc = new Calculator(order, products);
        calc.calcPartsList();

        int rafterQuantity = order.getPartsListLines().get(5).getQuantity();
        int postQuantity = order.getPartsListLines().get(6).getQuantity();


        SVG svg = new SVG(100, 50, "0 0 800 600", 600, 600);

        SVG arrowSvg = new SVG(carportLength, carportWidth, "0 0 800 600", 600, 600);


        // Alle spær
        svg.addRect(0, 15, 4, carportLength);
        svg.addRect(0, carportWidth - 15, 4, carportLength);
        for (int x = 0; x < rafterQuantity; x++) {
            svg.addRect(2 + carportLength / (rafterQuantity - 1) * x, 0, carportWidth, 4);
        }

        // stolper
        for (int y = 0; y < 2; y++) {

            // Stolper i hver hjørne
            for (int x = 0; x < 2; x++) {
                svg.addRect(2 + (carportLength - 12) * x, (carportWidth - 30) * y + 12, 12, 12);
            }

            // Stolper i midten
            if (postQuantity > 4) {
                svg.addRect(carportLength / 2, (carportWidth - 30) * y + 12, 12, 12);
            }
        }

        // Stålbånd på kryds
        svg.addLine(2 + carportLength / (rafterQuantity - 1), 12, 2 + carportLength - (carportLength / (rafterQuantity - 1)), carportWidth - 12);
        svg.addLine(2 + carportLength - (carportLength / (rafterQuantity - 1)), 12, 2 + carportLength / (rafterQuantity - 1), carportWidth - 12);

        // Arrows
        arrowSvg.addSvg(svg);
        arrowSvg.addArrow(carportLength, carportWidth);


        request.setAttribute("svgdrawing", arrowSvg.toString());
        String address = request.getParameter("address");
        request.setAttribute("order", order);

        //Insert into database
//        OrderMapper orderMapper = new OrderMapper(connectionPool);
//        PartsListLineMapper partsListLineMapper = new PartsListLineMapper(connectionPool);
//
//        orderMapper.insertOrder(order);
//        order.setPartslistOrderId(1);
//
//        for (PartsListLine p : order.getPartsListLines()) {
//            try {
//                partsListLineMapper.createPartsListLine(order,p);
//            } catch (DatabaseException e) {
//                e.printStackTrace();
//            }
//        }

        UserMapper userMapper = new UserMapper(connectionPool);
        try {
            User userSession = (User) request.getSession().getAttribute("user");
            if (userSession != null) {
                //int String email, int width, int length, int orderPrice, int shedId, boolean accepted
                Order orderToInsert = new Order(0, userSession.getEmail(), carportWidth, carportLength, order.getOrderPrice(), 0, false);

                orderToInsert.setPartsListLines(order.getPartsListLines());
                HttpSession session = request.getSession();
                session.setAttribute("order", orderToInsert);

                if (address != null) {
                    if (!address.equals(userSession.getAddress())) {
                        userMapper.updateAddress(userSession.getEmail(), address);
                        User user = new User(userSession.getEmail(), userSession.getFullName(), userSession.getBalance(), address, userSession.getZipNr(), userSession.getRole());
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/graphic.jsp").forward(request, response);
    }
}
