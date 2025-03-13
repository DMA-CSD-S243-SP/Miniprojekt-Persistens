package database;

import java.util.List;
import model.Customer;


/**
 * TODO
 * 
 * @author Anders Have & Lumière Schack
 * @version 13/03/2025 - 10:54
 */
public interface CustomerDaoImpl 
{
	/**
	 * Method creates a list containing a shallow clone of every Customer in the
	 * database. If fullAssociation is false then associations will only be filled
	 * with information found in the respective row of each Customer in the Customer
	 * table. Otherwise associations will be filled in with the information from the
	 * row that the Customer has a key to.
	 * 
	 * @param fullAssociation - Whether the Customers' associations should be looked
	 *                        up in the database.
	 * @return A list containing a shallow clone of every Customer in the database.
	 */
	List<Customer> findAllCustomers() throws DataAccessException;
	
	/**
	 * Method creates a shallow clone of the Customer that has the given email. If no
	 * matching Customer is found the method returns null. If fullAssociation is
	 * false then associations will only be filled with information found in the
	 * respective row of the Customer in the Customer table. Otherwise associations
	 * will be filled in with the information from the row the Customer has a key
	 * to.
	 * 
	 * @param CustomerId      - The id used for the querry.
	 * @param fullAssociation - Whether the Customer's associations should be looked
	 *                        up in the database.
	 * @return A shallow clone of the Customer that has an id that matches the
	 *         querry or NULL if no match is found.
	 */

	Customer findCustomerByEmail(String customerEmail) throws DataAccessException;
}

