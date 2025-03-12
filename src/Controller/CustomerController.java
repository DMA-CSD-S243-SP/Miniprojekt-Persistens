package Controller;

import java.util.ArrayList;
import java.util.List;

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
	 */
	public CustomerController()
	{
		
	}
	
	public List<Customer> findAllCustomers()
	{
		return CustomerDB.findAllCustomer();
	}
	
	
	/**
	 * this method searched the database for a Customer object that matches the search
	 * parameter.
	 * 
	 * @param customerEmail - the search parameter 
	 * 
	 * @return a customer object that  matches the search parameter
	 */
	public Customer findCustomerByEmail(String customerEmail)
	{
		return CustomerDB.findCustomerByEmail(customerEmail);
	
	}
}
