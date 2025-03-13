package database;

import java.util.List;
import model.Customer;


/**
 * TODO
 * 
 * @author Anders Have
 * @version 13/03/2025 - 8:50
 */
public interface CustomerDaoImpl 
{
	List<Customer> findAllCustomers() throws DataAccessException;
	Customer findCustomerByEmail(String customerEmail) throws DataAccessException;
}

