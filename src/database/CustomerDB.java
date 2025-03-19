package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 *  This class is responsible for accessing 
 * and managing customer objects stored in a database.
 * 
 * It implements the customerDapImpl, 
 * meaning its implements its methods
 * 
 * @author Anders Have
 * @version 13/03/2025 - 13:35
 */
public class CustomerDB implements CustomerDaoImpl
{
	// it selects all the data in the Customer tabel 
	private static final String FIND_ALL_QUERIES = "SELECT emailAddress, id, firstName, lastName, title, phoneNumber, streetName, houseNumber, floorNumber,"
			+ " doorNumber, stateName, postalCode, clubMember from Customer";
	// this builds on FIND_ALL_QUERIES by adding a filter with a placeholder 
	private static final String FIND_ALL_CUSTOMEREMAIL_QUERY = FIND_ALL_QUERIES + " where emailAddress = ?";
	
	private PreparedStatement findAllCustomer; 
	private PreparedStatement findByCustomerEmail;
	
	public CustomerDB() throws SQLException {
		findAllCustomer = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_QUERIES);
		findByCustomerEmail = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_CUSTOMEREMAIL_QUERY);
	}
	
	
	/**
	 * this method finds all the Customers in the database
	 */
	@Override
	public List<Customer> findAllCustomers() throws DataAccessException 
	{
		Connection con = DBConnection.getInstance().getConnection();
		try 
		{
			findAllCustomer = con.prepareStatement(FIND_ALL_QUERIES);
			//executes the preparedstatement
			ResultSet resultSet = findAllCustomer.executeQuery();
			
			//makes a list and asks buildObjects to make the data into java objects 
			List<Customer> res = buildObjects(resultSet);
			return res;
		} catch (SQLException e) 
		{
			throw new DataAccessException("Could not find all", e);
		}
	}

	
	/**
	 * this method find a single Customer by searching with customerEmail.
	 */
	
    /**
     * Finds a single Customer object by searching for a customer with a matching email address.
     * 
     * @param customerEmail the email address to search for
     * @return the corresponding Customer object, or null if not found
     * @throws DataAccessException if retrieval fails
     */
	@Override
	public Customer findCustomerByEmail(String customerEmail) throws DataAccessException 
	{
		Connection databaseConnection  = DBConnection.getInstance().getConnection();
		
		try
		{
			
			findByCustomerEmail = databaseConnection.prepareStatement(FIND_ALL_CUSTOMEREMAIL_QUERY);
			
			// Adds the parameter to the String instead of the placeholder.
			findByCustomerEmail.setString(1, customerEmail);
			
			// Executes the preparedstatement
			ResultSet resultSet = findByCustomerEmail.executeQuery();
			
			Customer customer = null;
			
			if(resultSet.next())
			{
				// makes a Customer object out of the data found.
				customer = buildObject(resultSet);
			}
			
			return customer;
		}
		
		catch (SQLException exception)
		{
			throw new DataAccessException("Unable to find a Customer object with an e-mail address matching: " + customerEmail, exception);
		}	
	}

	
    /**
     * Builds a specific Customer object from a database result set.
     * 
     * @param customerResultSet the result set containing customer data
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
				/* resultSet.getString("customerType") */// null,
				resultSet.getString("phoneNumber"),
				resultSet.getString("emailAddress"),
				resultSet.getBoolean("clubMember"),
				/* resultSet.getString("country") */ null,
				resultSet.getString("stateName"),
				/* resultSet.getString("city") */ null,
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
     * @param customerResultSet the result set containing multiple customer records
     * @return a list of Customer objects
     * @throws SQLException if accessing the result set fails
     */
	private List<Customer> buildObjects(ResultSet resultSet) throws SQLException 
	{
		List<Customer> customerList = new ArrayList<>();
		
		while(resultSet.next())
		{
			customerList.add(buildObject(resultSet));
		}
		
		return customerList;
	}	
}
