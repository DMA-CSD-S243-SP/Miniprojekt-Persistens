package database;

import java.util.List;
import model.Product;

/**
 * 
 * @author Line Bertelsen
 * @date 13.02.25 - 11.01
 */

public interface ProductDaoImpl
{
	List <Product> findAllProducts() throws DataAccessException; 
	Product findProductById(int productId) throws DataAccessException;
}
