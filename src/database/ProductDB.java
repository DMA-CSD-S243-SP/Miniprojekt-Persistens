package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

/**
 * This class is responsible for accessing 
 * and managing product objects stored in a database.
 * 
 * It implements the ProductDAO interface, 
 * meaning its follows a contract for interacting 
 * with person records in the database.
 * 
 * 1. Retrieves all persons from the database
 * 2. Retrives a person by their ID
 * 3. Placeholder for creating a Product 
 * 
 * @author Line Bertelsen
 * @date 13.03.25 - 09.36
 */

public class ProductDB implements ProductDaoImpl 
{
	//It select all columns (personId, name, email, adress_id) from the person table
	private static final String FIND_ALL_QUERIES = 
		"select productId, name, purchasePrice, salesPrice, countryOfOrigin, minStock from product";
	
	//It builds upon the FIND_ALL_QUERY which filters by 'productId' (productId)
	//The ? is a placeholder for a value that will be inserted later
	private static final String FIND_ALL_PRODUCTID_QUERY = 
			FIND_ALL_QUERIES + " where productId = ?"; 
	
	//Instance variables
	//This prepared statement store the compiled SQL queries
	//They are used to execute queries efficiently and prevent SQL injection
	private PreparedStatement findAllProductPreparedStatement;
	private PreparedStatement findProducutByIdPrepratedStatement;
	
	/**
	 * Constructor that throws the class dataAccessException
	 * Which means if something goes wrong, a custom exception 'dataAccessException' is thrown 
	 * 
	 * @throws SQLException
	 */
	public ProductDB() throws SQLException 
	{
		findAllProductPreparedStatement = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_QUERIES);
		findProducutByIdPrepratedStatement = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_PRODUCTID_QUERY);
	}
	
	/**
	 * Finds all products in database
	 * 
	 * @throws DataAccessException
	 */
	@Override
	public List<Product> findAllProducts() throws DataAccessException 
	{
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllProductPreparedStatement = con.prepareStatement(FIND_ALL_QUERIES);
			ResultSet resultSet = findAllProductPreparedStatement.executeQuery();
			List<Product> result = buildObjects(resultSet);
			return result;
		} catch (SQLException exception) {
			DataAccessException dataAccessException = new DataAccessException("Could not find all", exception);
			throw dataAccessException;
		}
	}
	
	
	/**
	 * This method find a product by their ID
	 * 
	 */
	@Override
	public Product findProductById(int productId) throws DataAccessException 
	{
		Connection con = DBConnection.getInstance().getConnection();
		try 
		{
			findProducutByIdPrepratedStatement = con.prepareStatement(FIND_ALL_PRODUCTID_QUERY);
			//setInt(1, productId) insert the productId parameter into the ? placeholder in FIND_BY_ID_QURIES
			findProducutByIdPrepratedStatement.setInt(1, productId);
			
			//This Executes the SQL query and store the result in resultSet (a ReasultSet object)
			ResultSet resultSet = findProducutByIdPrepratedStatement.executeQuery();
			
			//Product datatype the object result, is set to null
			Product product = null;	
			
			if(resultSet.next()) 
			{
				product = buildObject(resultSet);
			}
			return product;
		} 
		
		catch (SQLException exception) 
		{
			throw new DataAccessException("Could not find by id = " + productId, exception);
		}
	}
	
	private Product buildObject(ResultSet resultSet) throws SQLException 
	{
		Product product = new Product(
				resultSet.getInt("productId"),
				resultSet.getString("name"),
				resultSet.getDouble("purchasePrice"),
				resultSet.getDouble("salesPrice"),
				resultSet.getString("countryOfOrigin"),
				resultSet.getInt("minStock")
				);
		return product;
	}

	private List<Product> buildObjects(ResultSet resultSet) throws SQLException 
	{
		List<Product> result = new ArrayList<>();
		while(resultSet.next()) 
		{
			result.add(buildObject(resultSet));
		}
		return result;
	}

}
