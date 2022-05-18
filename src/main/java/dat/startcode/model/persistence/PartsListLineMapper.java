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

    public ArrayList<PartsListLine> retrievePartsList() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<PartsListLine> partsList = new ArrayList<>();

        String sql = "SELECT partslist_order_id, email, total_width, total_length, order_price, shed_id, accepted FROM carport.partslist_order";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int partslistOrderId = rs.getInt("partslist_order_id");
                    String email = rs.getString("email");
                    int width = rs.getInt("total_width");
                    int length = rs.getInt("total_length");
                    int orderPrice = rs.getInt("order_price");
                    int shedId = rs.getInt("shed_id");
                    boolean accepted = rs.getBoolean("accepted");
                    Order order = new Order(partslistOrderId, email, width, length, orderPrice, shedId, accepted);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return partsList;
    }

}
