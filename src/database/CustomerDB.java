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
 * @version 13/03/2025 - 9:20
 */
public class CustomerDB implements CustomerDaoImpl
{
	private static final String FIND_ALL_QUERIES = "SELECT firstName, lastName, customerType, phoneNumber, emailAddress, clubMember, purchaseThreshhold, country, state,"
			+ "postalCode, streetName, houseNumber, floorNumber, doorNumber from Customer";
	private static final String FIND_ALL_CUSTOMEREMAIL_QUERY = FIND_ALL_QUERIES + "where emailAddress = ?";
	
	private PreparedStatement findAll; 
	private PreparedStatement findByCustomerEmail;
	
	public CustomerDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_QUERIES);
		findByCustomerEmail = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_CUSTOMEREMAIL_QUERY);
	}
	@Override
	public List<Customer> findAllCustomers() throws DataAccessException 
	{
		try 
		{
			ResultSet resultSet = findAll.executeQuery();
			List<Customer> res = buildObjects(resultSet);
			return res;
		} catch (SQLException e) 
		{
			throw new DataAccessException("Could not find all", e);
		}
	}

	@Override
	public Customer findCustomerByEmail(String customerEmail) throws DataAccessException 
	{
		try {
			findByCustomerEmail.setInt(0, 0);
			ResultSet resultSet = findByCustomerEmail.executeQuery();
			Customer c = null;
			if(resultSet.next()) 
			{
				c = buildObject(resultSet);
			}
			return c;
		} 
		catch (SQLException e) {
			throw new DataAccessException("Could not find by email = " + customerEmail, e);
		}	
		}

	/**
	 * 
	 * 
	 * @param resultSet
	 * @return returns a Custoemr object with the specified variables from resultSet
	 * @throws SQLException
	 */
	private Customer buildObject(ResultSet resultSet) throws SQLException 
	{
		Customer c = new Customer(
				resultSet.getString("firstName"), 
				resultSet.getString("lastName"), 
				resultSet.getString("customerType"), 
				resultSet.getString("phoneNumber"), 
				resultSet.getString("emailAddress"), 
				resultSet.getBoolean("clubMember"), 
				resultSet.getDouble("purchaseThreshhold"),  
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
	 * 
	 * 
	 * @param resultSet
	 * @return Customer objects for all Customers in the database
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
