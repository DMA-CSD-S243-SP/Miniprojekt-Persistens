package database;

import java.util.List;
import model.Product;


/**
 * 
 * @author Line Bertelsen, Lumière Schack & Christoffer Søndergaaard
 * @version 19/03/2025 - 20:58
 */
public interface ProductDaoImpl
{
	/**
	 * Method creates a list containing a shallow clone of every Product in the
	 * database. If fullAssociation is false then associations will only be filled
	 * with information found in the respective row of each Product in the Product
	 * table. Otherwise associations will be filled in with the information from the
	 * row that the Product has a key to.
	 * 
	 * @param fullAssociation - Whether the Products' associations should be looked up in the database.
	 * @return A list containing a shallow clone of every Product in the database.
	 */
	List <Product> findAllProducts() throws DataAccessException;
	
	
	/**
	 * Method creates a shallow clone of the Product that has the given productId. If no
	 * matching Product is found the method returns null. If fullAssociation is
	 * false then associations will only be filled with information found in the
	 * respective row of the Product in the Product table. Otherwise associations
	 * will be filled in with the information from the row the Product has a key
	 * to.
	 * 
	 * @param productId - The id used for the query.
	 * @param fullAssociation - Whether the Product's associations should be looked up in the database.
	 * @return A shallow clone of the Product that has an id that matches the query or NULL if no match is found.
	 */
	Product findProductById(int productId) throws DataAccessException;
}
