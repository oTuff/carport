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

public class ProductMapper {
    ConnectionPool connectionPool;

    public ProductMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ArrayList<Product> retrieveProduct() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT p.product_name, p.product_price, p.unit_amount, u.unit_name FROM carport.product p INNER JOIN carport.unit u ON p.unit_id";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("product_name");
                    int price = rs.getInt("product_price");
                    String unitAmount = rs.getString("unit_amount");
                    String unitName = rs.getString("unit_name");
                    if (!(unitAmount == null)) {//if unitAmount exists it will add it to the name. e.g. "hulbånd 1x20 mm."+ "10 mtr."
                        name = name + " " + unitAmount;//todo maybe just put the unit description in the product name???
                    }
                    products.add(new Product(name, price, unitName));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'product' from Database.");
        }
        return products;
    }
}
