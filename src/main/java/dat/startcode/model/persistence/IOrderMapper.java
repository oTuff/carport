package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IOrderMapper {
    public boolean insertOrder(Order order) throws DatabaseException;

    public List<Order> retrieveAllOrders() throws DatabaseException;

    public List<Order> retrieveMyOrders(User user) throws DatabaseException;

    public boolean acceptOrder(int partslistOrderId) throws DatabaseException;

    public List<Order> retrieveOrder(int partslistOrderId) throws DatabaseException;

    public boolean deleteOrder(int partslistOrderId) throws DatabaseException;
}
