package dat.startcode.model.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest extends HttpServlet {

    private final static String USER = "carport";
    private final static String PASSWORD = "carport";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    private static ProductMapper productMapper;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        productMapper = new ProductMapper(connectionPool);
    }

    @BeforeEach
    void setUp() {      // Deleting all data from the tables
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Remove all rows from all tables
                stmt.execute("DELETE FROM product");
                stmt.execute("DELETE FROM unit");
                stmt.execute("INSERT INTO `unit` VALUES " +           // Inserting a few values into zip
                        "(1, 'Stk')," +
                        "(2, 'Pakke')," +
                        "(3, 'Rulle')");
                stmt.execute("INSERT INTO `product` VALUES " +        // Inserting a few values into member
                        "(1, '25x200 mm trykimp Brædt', 100, 1)," +
                        "(2, '25x125mm trykimp Brædt', 799, 1)," +
                        "(3, '38x73 mm Lægte ubh', 20, 1)");
            }
        } catch (SQLException throwables) {
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void retrieveProduct() throws DatabaseException {
        List<Product> productsList = productMapper.retrieveAllProducts();
        assertEquals(productsList.get(0), new Product(1, "25x200 mm trykimp Brædt", 100, 1));
        assertEquals(productsList.get(1), new Product(2, "50x500mm trykimp Brædt", 799, 1));
        assertEquals(productsList.get(2), new Product(3, "38x73 mm Lægte ubh", 20, 1));
    }

    @Test
    void retrieveAllProducts() throws DatabaseException {
        List<Product> productsList = productMapper.retrieveAllProducts();
        assertEquals(21, productsList.size());
    }

    @Test
    void getProductId() throws DatabaseException {         // Testing if the product contains the right member ID
        assertEquals(new Product(2, "50x500mm trykimp Brædt", 799, 1), productMapper.getProductId(2));
    }

    @Test
    void updateProduct() throws DatabaseException {
        boolean result = productMapper.updateProduct(new Product(2, "50x500mm trykimp Brædt", 799, 1));
        assertTrue(result);     // Update the product's new value. In this case, name and price
        Product p1 = productMapper.getProductId(2);      // Get the product by product_id.
        assertEquals(799, p1.getPrice());    // Inserting a new value of price, and then get the new price

    }
}













