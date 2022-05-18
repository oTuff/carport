package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletProductUpdate", urlPatterns = {"/servletproductupdate"})
public class ServletProductUpdate extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        int productId = Integer.parseInt(idString);
        String productName = request.getParameter("name");
        int productPrice = Integer.parseInt(request.getParameter("price"));
        int unitId = Integer.parseInt(request.getParameter("unitId"));


        Product product = new Product(productId, productName, productPrice, unitId);
        ProductMapper productMapper = new ProductMapper(connectionPool);
        try {
            boolean result = productMapper.updateProduct(product);

        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("servletadminproducts").forward(request, response);
    }
}
