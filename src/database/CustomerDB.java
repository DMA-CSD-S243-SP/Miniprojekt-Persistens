package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;


/**
 * This class is responsible for accessing and managing customer objects stored
 * in a database.
 * 
 * It implements the employeeDaoImpl, meaning it implements its methods
 * 
 * @author Anders Have, Lumière Schack & Christoffer Søndergaard
 * @version 19/03/2025 - 17:43
 */
public class CustomerDB implements CustomerDaoImpl
{
	// Selects all of the data within the Customer table in the database
	private static final String FIND_ALL_QUERIES = "SELECT emailAddress, id, firstName, lastName, title, phoneNumber, streetName, houseNumber, floorNumber, doorNumber, stateName, postalCode, clubMember FROM Customer";

	// This extends on the FIND_ALL_QUERIES by adding a filtering condition with a placeholder in the form of "?" for email address
	private static final String FIND_ALL_CUSTOMEREMAIL_QUERY = FIND_ALL_QUERIES + " WHERE emailAddress = ?";
	
	// PreparedStatement are used to execute queries efficiently, and prevent SQL injections
	// PreparedStatement for retrieving all customers from the database
	private PreparedStatement findAllCustomer; 
	
	// PreparedStatement for retrieving a customer based on their email address
	private PreparedStatement findByCustomerEmail;

	
	/**
	 * Constructor for CustomerDB.
	 * Initializes prepared statements for executing SQL queries.
	 * 
	 * @throws SQLException if there is an issue with the database connection
	 */
	public CustomerDB() throws SQLException
	{
		// Prepares the SQL statement for retrieving all customers
		findAllCustomer = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_QUERIES);
		
		// Prepares the SQL statement for retrieving a customer by email
		findByCustomerEmail = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_CUSTOMEREMAIL_QUERY);
	}
	
	
    /**
     * Retrieves all customers from the database.
     * 
     * @return a list of all customers
     * @throws DataAccessException if retrieval fails
     */
	@Override
	public List<Customer> findAllCustomers() throws DataAccessException 
	{
		// Gets a connection to the database
		Connection databaseConnection = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepare a SQL statement to retrieve all customers
			findAllCustomer = databaseConnection.prepareStatement(FIND_ALL_QUERIES);
			
			// Executes the prepared statement and stores the result set
			ResultSet customerResultSet = findAllCustomer.executeQuery();
			
			// Converts the result set into a list of Customer objects
			List<Customer> listOfCustomers = buildObjects(customerResultSet);
			
			// Returns the list of Customer objects
			return listOfCustomers;
		}
		
		catch (SQLException exception) 
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find Customer objects in the database", exception);
		}
	}


    /**
     * Finds a Customer object by searching for a customer with a matching email address.
     * 
     * @param customerEmail the email address to search for
     * @return the corresponding Customer object, or null if not found
     * @throws DataAccessException if retrieval fails
     */
	@Override
	public Customer findCustomerByEmail(String customerEmail) throws DataAccessException 
	{
		// Gets a connection to the database
		Connection databaseConnection  = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepares a SQL statement to find and retrieve a customer with a matching email address
			findByCustomerEmail = databaseConnection.prepareStatement(FIND_ALL_CUSTOMEREMAIL_QUERY);
			
			// Adds the email address provided in the method's parameter to the String instead of the placeholder
			findByCustomerEmail.setString(1, customerEmail);
			
			// Executes the query, and stores the retrieved data in the variable named resultSet, which is a ResultSet object
			ResultSet resultSet = findByCustomerEmail.executeQuery();
			
			// Creates and initializes a Customer object as null, which will later be populated with Customer specific data
			Customer customer = null;
			
			// Iterates through the resultSet while there are still more rows in the database's table
			if(resultSet.next())
			{
				// Converts the retrieved database row into a Customer object using the buildObject method
				customer = buildObject(resultSet);
			}
			
			// Returns the customer with a matching e-mail or null if no customer has the specified emailAddress
			return customer;
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find a Customer object with an e-mail address matching: " + customerEmail, exception);
		}	
	}

	
    /**
     * Builds a specific Customer object from a database result set.
     * 
     * @param resultSet the result set containing customer data
     * @return a Customer object with the extracted data
     * @throws SQLException if accessing the result set fails
     */
	private Customer buildObject(ResultSet resultSet) throws SQLException 
	{
		// Creates a Customer object stored within the customer variable based off of the method's provided resultSet
		Customer customer = new Customer(
				resultSet.getInt("id"),
				resultSet.getString("firstName"),
				resultSet.getString("lastName"),
				resultSet.getString("phoneNumber"),
				resultSet.getString("emailAddress"),
				resultSet.getBoolean("clubMember"),
				// country - We supply null here, as country can be deducted from the supplied State, hence no need to waste storage within the database for this
				null,

				resultSet.getString("stateName"),
				
				// city - We supply null here, as country can be deducted from the supplied Postal Code, hence no need to waste storage within the database for this
				null,
				resultSet.getInt("postalCode"),
				resultSet.getString("streetName"),
				resultSet.getInt("houseNumber"),
				resultSet.getInt("floorNumber"),
				resultSet.getString("doorNumber")
				);
		
		return customer;
	}
	

    /**
     * Converts a result set into a list of Customer objects.
     * 
     * @param resultSet the result set containing multiple customer records
     * @return a list of Customer objects
     * @throws SQLException if accessing the result set fails
     */
	private List<Customer> buildObjects(ResultSet resultSet) throws SQLException 
	{
		// Creates an empty list to store Customer objects within
		List<Customer> customerList = new ArrayList<>();
		
		// Iterates through the result set while there are still more rows in the database's table
		while(resultSet.next())
		{
			// Converts each row into a Customer object and add it to the list
			customerList.add(buildObject(resultSet));
		}
		
		// Returns the populated list of Customer objects
		return customerList;
	}
}
