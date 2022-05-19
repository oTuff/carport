package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.Product;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartsListLineMapper { ConnectionPool connectionPool;

    public PartsListLineMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ArrayList<PartsListLine> retrievePartsList(Order order) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<PartsListLine> partsList = new ArrayList<>();

        String sql = "SELECT p.product_name, l.product_length, l.quantity, u.unit_name, l.parts_price, l.description"+
        "FROM product p"+
        "INNER JOIN unit u ON p.unit_id"+
        "INNER JOIN partslist_line l on p.product_id"+
        "WHERE l.partslist_order_id =?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,order.getPartslistOrderId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("product_name");
                    int length = rs.getInt("product_length");
                    int quantity = rs.getInt("quantity");
                    String unitName = rs.getString("unit_name");
                    int orderPrice = rs.getInt("parts_price");
                    String description = rs.getString("description");
                    boolean accepted = rs.getBoolean("accepted");
                    Product product = new Product(productName,0,unitName);
                    partsList.add(new PartsListLine(product,length,quantity,description,orderPrice));
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return partsList;
    }

    public void createPartsListLine(Order order, PartsListLine partsListLine) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "insert into partslist_line (product_id, partslist_order_id, product_length, quantity, parts_price, description) values (?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, partsListLine.getProduct().getProductId());
                ps.setInt(2,order.getPartslistOrderId());
                ps.setInt(3, partsListLine.getLength());
                ps.setInt(4, partsListLine.getQuantity());
                ps.setInt(6, partsListLine.getTotalPrice());
                ps.setString(7, partsListLine.getDescription());
                int rowsAffected = ps.executeUpdate();
                {
                    throw new DatabaseException(" ");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert partslist into database");
        }
    }

}
