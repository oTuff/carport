package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductMapper implements IProductMapper {
    ConnectionPool connectionPool;

    public ProductMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ArrayList<Product> retrieveProduct() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT p.product_name, p.product_price, u.unit_name FROM carport.product p INNER JOIN carport.unit u ON p.unit_id";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("product_name");
                    int price = rs.getInt("product_price");
                    String unitName = rs.getString("unit_name");
                    products.add(new Product(name, price, unitName));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'product' from Database.");
        }
        return products;
    }

    public ArrayList<Product> retrieveAllProducts() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT p.product_id, p.product_name, p.product_price, p.unit_id FROM carport.product p";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    String name = rs.getString("product_name");
                    int price = rs.getInt("product_price");
                    int unitId = rs.getInt("unit_id");
                    products.add(new Product(productId, name, price, unitId));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'product' from Database.");
        }
        return products;
    }
}
