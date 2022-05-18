package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        "FROM partslist_line l"+
        "INNER JOIN unit u ON l.unit_id"+
        "INNER JOIN product p ON l.product_id" +
        "WHERE partslist_order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("product_name");
                    int length = rs.getInt("product_length");
                    String unitName = rs.getString("unit_name");
                    int orderPrice = rs.getInt("parts_price");
                    String description = rs.getString("description");
                    boolean accepted = rs.getBoolean("accepted");
//                    partsList.add(new PartsListLine())
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return partsList;
    }

}
