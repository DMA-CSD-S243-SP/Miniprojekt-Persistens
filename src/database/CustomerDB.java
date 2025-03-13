package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DataAccessException;
import model.Customer;

public class CustomerDB implements CustomerDaoImpl
{
	private static final String FIND_ALL_QUERIES;
	private static final String FIND_ALL_CUSTOMEREMAIL_QUERY;
	
	private PreparedStatement findAll; 
	private PreparedStatement findByCustomerEmail;
	
	public CustomerDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_QUERIES);
		findByCustomerEmail = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_CUSTOMEREMAIL_QUERY);

	@Override
	public List<Customer> findAllCustomers() throws DataAccessException 
	{
		try 
		{
			ResultSet rs = findAll.executeQuery();
			List<Customer> res = buildObjects(rs);
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
			ResultSet rs = findByCustomerEmail.executeQuery();
			Customer c = null;
			if(rs.next()) 
			{
				c = buildObject(rs);
			}
			return g;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + id);
		}	}

	private Customer buildObject(ResultSet resultSet) throws SQLException 
	{
		Customer c = new Customer(
				resultSet.getString("firstName"), 
				resultSet.getString("lastName"), 
				resultSet.getString("customerType"), 
				resultSet.getString("phoneNumber"), 
				resultSet.getString("emailAddress"), 
				resultSet.getBoolean("TODO"), 
				resultSet.getDouble("purchaseThreshhold"), 
				resultSet.getDouble("freightCharge"), 
				resultSet.getDouble("discountPercentage"), 
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
	
	
	private List<Customer> buildObjects(ResultSet resultSet) throws SQLException 
	{
		Customer c= new Customer(
				resultSet.getString("firstName"), 
				resultSet.getString("lastName"), 
				resultSet.getString("customerType"), 
				resultSet.getString("phoneNumber"), 
				resultSet.getString("emailAddress"), 
				resultSet.getBoolean("TODO"), 
				resultSet.getDouble("purchaseThreshhold"), 
				resultSet.getDouble("freightCharge"), 
				resultSet.getDouble("discountPercentage"), 
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
	
}
