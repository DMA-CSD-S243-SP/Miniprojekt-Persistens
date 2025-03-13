package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 * TODO
 * 
 * @author Anders Have
 * @version 13/03/2025 - 13:35
 */
public class CustomerDB implements CustomerDaoImpl
{
	// it selects all the data in the Customer tabel 
	private static final String FIND_ALL_QUERIES = "SELECT emailAddress, id, firstName, lastName, title, phoneNumber, streetNumber, houseNumber, floorNumber,"
			+ " doorNumber, stateName, postalCode from Customer";
	// this builds on FIND_ALL_QUERIES by adding a filter with a placeholder 
	private static final String FIND_ALL_CUSTOMEREMAIL_QUERY = FIND_ALL_QUERIES + "where emailAddress = ?";
	
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
		try 
		{
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
	@Override
	public Customer findCustomerByEmail(String customerEmail) throws DataAccessException 
	{
		try {
			// adds the parameter to the String instead of the placeholder.
			findByCustomerEmail.setString(1, customerEmail);
			//executes the preparedstatement
			ResultSet resultSet = findByCustomerEmail.executeQuery();
			Customer c = null;
			if(resultSet.next()) 
			{
				// makes a Customer object out of the data found.
				c = buildObject(resultSet);
			}
			return c;
		} 
		catch (SQLException e) {
			throw new DataAccessException("Could not find by email = " + customerEmail, e);
		}	
		}

	
	/**
	 * builds a specific customer with data proviced by database
	 * 
	 * @param resultSet
	 * @return returns a Customer object with the specified variables from resultSet
	 * @throws SQLException
	 */
	private Customer buildObject(ResultSet resultSet) throws SQLException 
	{
		Customer c = new Customer(
				resultSet.getInt("customerId"),
				resultSet.getString("firstName"), 
				resultSet.getString("lastName"), 
				resultSet.getString("customerType"), 
				resultSet.getString("phoneNumber"), 
				resultSet.getString("emailAddress"), 
				resultSet.getBoolean("clubMember"), 
				resultSet.getString("country"), 
				resultSet.getString("state"), 
				resultSet.getString("city"), 
				resultSet.getInt("postalCode"), 
				resultSet.getString("streetName"), 
				resultSet.getInt("houseNumber"), 
				resultSet.getInt("floorNumber"), 
				resultSet.getString("doorNumber")
				);
		return c;
	}
	
	
	/**
	 * asks buildObject to create all Customers and adds them to a List
	 * 
	 * @param resultSet
	 * @return List of Customer objects for all Customers in the database
	 * @throws SQLException
	 */
	private List<Customer> buildObjects(ResultSet resultSet) throws SQLException 
	{
		List<Customer> c = new ArrayList<>();
		while(resultSet.next()) {
			c.add(buildObject(resultSet));
		}
		return c;
	}	
}
