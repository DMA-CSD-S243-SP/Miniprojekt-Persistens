package Controller;

import java.sql.SQLException;
import java.util.List;

import database.CustomerDB;
import database.DataAccessException;
import model.Customer;


/**
 * TODO
 * 
 * @author Anders Have
 * @version 12/03/2025 - 20:30
 */
public class CustomerController 
{

	private CustomerDB CDB;


	/**
	 * this method returns a list of all Customer objects in the database. 
	 * 
	 * @return returns a list of all Customer objects
	 * @throws DataAccessException 
	 */
	public CustomerController() throws DataAccessException 
	{
	try 
	{
		CDB = new CustomerDB();
	} catch (SQLException e) 
	{
		throw new DataAccessException("Can't create GroupDB", e);
	}
	}
	
	
	public List<Customer> findAllCustomers() throws DataAccessException
	{
		return CDB.findAllCustomers();
	}
	
	
	/**
	 * this method searched the database for a Customer object that matches the search
	 * parameter.
	 * 
	 * @param customerEmail - the search parameter 
	 * 
	 * @return a customer object that  matches the search parameter
	 * @throws DataAccessException
	 */
	public Customer findCustomerByEmail(String customerEmail) throws DataAccessException
	{
		return CDB.findCustomerByEmail(customerEmail);
	
	}
}
