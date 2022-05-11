package dat.startcode.model.persistence;

import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IOrderMapper {

    public List<Order> retrieveAllOrders() throws DatabaseException;
}
