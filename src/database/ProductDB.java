package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;


/**
 * This class is responsible for accessing and managing product objects stored
 * in a database.
 * 
 * It implements the productDaoImpl, meaning it implements its methods
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @version 19/03/2025 - 20:20
 */
public class ProductDB implements ProductDaoImpl
{
	// Selects all of the data within the Product table in the database (id, name, purchasePrice, countryOfOrigin, minStock)
	private static final String FIND_ALL_QUERIES = "SELECT id, name, purchasePrice, salesPrice, countryOfOrigin, minStock FROM Product";

	// This extends on the FIND_ALL_QUERIES by adding a filtering condition with a placeholder in the form of "?" for product ids
	// The "?" will be replaced later in the code
	private static final String FIND_ALL_PRODUCTID_QUERY = FIND_ALL_QUERIES + " WHERE id = ?";

	// PreparedStatement are used to execute queries efficiently, and prevent SQL injections
	// PreparedStatement for retrieving all products from the database
	private PreparedStatement findAllProductPreparedStatement;
	
	// PreparedStatement for retrieving a product based on their email address
	private PreparedStatement findProducutByIdPrepratedStatement;

	
	/**
	 * Constructor for ProductDB.
	 * Initializes prepared statements for executing SQL queries.
	 * 
	 * @throws SQLException if there is an issue with the database connection
	 */
	public ProductDB() throws SQLException
	{
		// Prepares the SQL statement for retrieving all products
		findAllProductPreparedStatement = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_QUERIES);
		
		// Prepares the SQL statement for retrieving a product by its product id
		findProducutByIdPrepratedStatement = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_PRODUCTID_QUERY);
	}

	
    /**
     * Retrieves all products from the database.
     * 
     * @return a list of all products
     * @throws DataAccessException if retrieval fails
     */
	@Override
	public List<Product> findAllProducts() throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepare a SQL statement to retrieve all products
			findAllProductPreparedStatement = databaseConnection.prepareStatement(FIND_ALL_QUERIES);
			
			// Executes the prepared statement and stores the result set
			ResultSet resultSet = findAllProductPreparedStatement.executeQuery();
			
			// Converts the result set into a list of Product objects
			List<Product> listOfProducts = buildObjects(resultSet);
			
			// Returns the list of Product objects
			return listOfProducts;
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			DataAccessException dataAccessException = new DataAccessException("Unable to find Product objects in the database", exception);
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
		// Gets a connection to the database
		Connection databaseConnection = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepare a SQL statement to retrieve all products
			findProducutByIdPrepratedStatement = databaseConnection.prepareStatement(FIND_ALL_PRODUCTID_QUERY);

			// Adds the producId provided in the method's parameter to the String instead of the "?" placeholder
			findProducutByIdPrepratedStatement.setInt(1, productId);

			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = findProducutByIdPrepratedStatement.executeQuery();

			// Creates and initializes a Product object as null, which will later be populated with Product specific data
			Product product = null;

			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into a Product object using the buildObject method
				product = buildObject(resultSet);
			}
			
			// Returns the Product with a matching productId or null if no product has the specified emailAddress
			return product;
		}

		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find a Product object with a product id matching: " + productId, exception);
		}
	}

	
    /**
     * Builds a specific Product object from a database result set.
     * 
     * @param resultSet the result set containing product data
     * @return a Product object with the extracted data
     * @throws SQLException if accessing the result set fails
     */
	private Product buildObject(ResultSet resultSet) throws SQLException
	{
		// Creates a Product object stored within the product variable based off of the method's provided resultSet
		Product product = new Product(
				resultSet.getInt("id"),
				resultSet.getString("name"),
				resultSet.getDouble("purchasePrice"),
				resultSet.getDouble("salesPrice"),
				resultSet.getString("countryOfOrigin"),
				resultSet.getInt("minStock")
				);
		
		return product;
	}

	
    /**
     * Converts a result set into a list of Product objects.
     * 
     * @param resultSet the result set containing multiple product records
     * @return a list of Product objects
     * @throws SQLException if accessing the result set fails
     */
	private List<Product> buildObjects(ResultSet resultSet) throws SQLException
	{
		// Creates an empty list to store Product objects within
		List<Product> productList = new ArrayList<>();

		// Iterates through the result set while there are still more rows in the database's table
		while (resultSet.next())
		{
			// Converts each row into a Product object and add it to the list
			productList.add(buildObject(resultSet));
		}
		
		// Returns the populated list of Product objects
		return productList;
	}
}
