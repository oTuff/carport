package dat.startcode.model.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.ArrayList;

public interface IProductMapper {
    public ArrayList<Product> retrieveProduct() throws DatabaseException;

    public ArrayList<Product> retrieveAllProducts() throws DatabaseException;

}
