package Controller;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.ProductDB;
import database.ProductDaoImpl;
import model.Product;

/**
 * This class tells ProductDaoImpl to look in the database
 * to find all products and finds a product object by Id
 * 
 * @author Line Bertelsen
 * @date 13.03.25 - 11:00
 */
public class ProductController
{
	private ProductDaoImpl productDB;
	
	public ProductController() throws DataAccessException
	{
		try 
		{
			productDB = new ProductDB();
		} 
		
		catch (SQLException exception) 
		{
			throw new DataAccessException("Can't create GroupDB", exception);
		}
	}
	
	/**
	 * this method returns a list of all products in the database
	 * 
	 * @return returns a list of all Products objects
	 * @throws DataAccessException
	 */
	public List<Product> findAllProducts() throws DataAccessException 
	{
		return productDB.findAllProducts();
	}
	
	/**
	 * this method searched the databse for product object that match the search parameter
	 * 
	 * @param productId
	 * @return a product object that match the search parameter
	 * @throws DataAccessException
	 */
	public Product findProductById(int productId) throws DataAccessException 
	{
		return productDB.findProductById(productId);
	}
}
