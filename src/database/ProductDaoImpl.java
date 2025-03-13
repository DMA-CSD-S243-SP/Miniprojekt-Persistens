package database;

import java.util.List;
import model.Product;

public interface ProductDaoImpl
{
	List <Product> findAllProducts() throws DataAccessException; 
	Product findProductById(int productId) throws DataAccessException;
}
