package dat.startcode.model.persistence;

import dat.startcode.model.entities.Product;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.ArrayList;

public interface IProductMapper {
    public ArrayList<Product> retrieveProduct() throws DatabaseException;

    public ArrayList<Product> retrieveAllProducts() throws DatabaseException;

    public Product getProductId(int productId) throws DatabaseException;

    public boolean updateProduct(Product product) throws DatabaseException;

}
