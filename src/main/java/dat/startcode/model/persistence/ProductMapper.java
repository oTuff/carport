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

    @Override
    public Product getProductId(int productId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "productId=" + productId);
        Product product = null;
        String sql = "SELECT * FROM carport.product WHERE product_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, productId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");
                    int productPrice = rs.getInt("product_price");
                    int unitId = rs.getInt("unit_id");

                    product = new Product(productId, productName, productPrice, unitId);
                } else {
                    throw new DatabaseException("Product with id = " + productId + " does not exist");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Product with id = " + productId + " does not exist");
        }
        return product;
    }

    @Override
    public boolean updateProduct(Product product) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean result = false;
        String sql = "UPDATE carport.product SET product_name = ?, product_price = ?, unit_id = ? " +
                "WHERE product_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, product.getName());
                ps.setInt(2, product.getPrice());
                ps.setInt(3, product.getUnitId());
                ps.setInt(4, product.getProductId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Could not update product with id = " + product.getProductId());
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Could not update product with id = " + product.getProductId());
        }
        return result;
    }
}
