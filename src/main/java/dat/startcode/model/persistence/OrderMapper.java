package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
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

public class OrderMapper implements IOrderMapper {
    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public List<Order> retrieveAllOrders() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT partslist_order_id, email, total_width, total_length, order_price, shed_id FROM carport.user";

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
                    Order order = new Order(partslistOrderId, email, width, length, orderPrice, shedId);
                    orderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return orderList;
    }
}
