package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper implements IOrderMapper {
    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Order> retrieveAllOrders() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Order> orderList = new ArrayList<>();

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
                    orderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return orderList;
    }

    @Override
    public List<Order> retrieveMyOrders(User user) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Order> myOrderList = new ArrayList<>();

        String sql = "SELECT partslist_order_id, email, total_width, total_length, order_price, shed_id, accepted FROM carport.partslist_order WHERE email = '"+user.getEmail()+"'";

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
                    myOrderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return myOrderList;
    }

    @Override
    public List<Order> retrieveOrder(int partslistOrderId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Order> myOrderList = new ArrayList<>();

        String sql = "SELECT partslist_order_id, email, total_width, total_length, order_price, shed_id, accepted FROM carport.partslist_order WHERE partslist_order_id = '"+partslistOrderId+"'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    partslistOrderId = rs.getInt("partslist_order_id");
                    String email = rs.getString("email");
                    int width = rs.getInt("total_width");
                    int length = rs.getInt("total_length");
                    int orderPrice = rs.getInt("order_price");
                    int shedId = rs.getInt("shed_id");
                    boolean accepted = rs.getBoolean("accepted");
                    Order order = new Order(partslistOrderId, email, width, length, orderPrice, shedId, accepted);
                    myOrderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error while loading 'carport' from Database.");
        }
        return myOrderList;
    }

    @Override
    public void acceptOrder(int partslistOrderId) throws DatabaseException {
        String sql = "UPDATE partslist_order SET accepted = 1 WHERE partslist_order_id = " + partslistOrderId;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert update partslist_order_id in database");
        }
    }
}
