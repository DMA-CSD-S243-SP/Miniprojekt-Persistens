package control;

import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import database.EmployeeDB;
import database.EmployeeDaoImpl;
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
		
	/**
	 * this method returns a list of all products in the database
	 * 
	 * @return returns a list of all Products objects
	 * @throws DataAccessException
	 */
	public List<Product> findAllProducts() throws DataAccessException, SQLException
	{
		ProductDaoImpl dao = new ProductDB();
		return dao.findAllProducts();
	}
	
	/**
	 * this method searched the database for product object that match the search parameter
	 * 
	 * @param productId
	 * @return a product object that match the search parameter
	 * @throws DataAccessException
	 */
	public Product findProductById(int productId) throws SQLException, DataAccessException 
	{
		ProductDaoImpl dao = new ProductDB();
		return dao.findProductById(productId);
	}
}
