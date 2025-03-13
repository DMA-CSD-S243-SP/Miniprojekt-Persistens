package control;

import java.sql.SQLException;
import java.util.List;

import database.CustomerDB;
import database.CustomerDaoImpl;
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

	/**
	 * this method returns a list of all Customer objects in the database. 
	 * 
	 * @return returns a list of all Customer objects
	 * @throws DataAccessException 
	 */
	public List<Customer> findAllCustomers() throws DataAccessException, SQLException
	{
		CustomerDaoImpl dao = new CustomerDB();
		return dao.findAllCustomers();
	}
	
	
	/**
	 * this method searched the database for a Customer object that matches the search
	 * parameter.
	 * 
	 * @param customerEmail - the search parameter 
	 * 
	 * @return a customer object that  matches the search parameter
	 * @throws DataAccessException
	 * @throws SQLException 
	 */
	public Customer findCustomerByEmail(String customerEmail) throws DataAccessException, SQLException
	{
		CustomerDaoImpl dao = new CustomerDB();
		return dao.findCustomerByEmail(customerEmail);
	
	}
}
