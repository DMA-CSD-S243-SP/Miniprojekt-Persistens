package tests;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBConnection;
import database.DataAccessException;
import database.ProductDB;
import model.Product;

/** 
 * a test class for testing the database class ProductDB
 * 
 * @author Anders Have
 * @version 14/03/2025 - 10:15
 */
public class TestProductDB 
{
	private ProductDB productDB;
	
	//opens connection to the database
	@BeforeAll
	public static void setUp() 
	{		
		DBConnection.getInstance().getConnection();
	}
	
	@BeforeEach
	public void DB() throws SQLException
	{
		productDB = new ProductDB();
	}
	
	// closes  the connection to the database after each test
	@AfterAll
	public static void tearDown()
	{
		DBConnection.getInstance().disconnect();
	}
	
	/**
	 * this test should throw an Exception as we are trying to find a product that doesnt exist.
	 * the method findProductById in ProductDB catches this Exception.
	 * 
	 * @throws DataAccessException
	 * @throws SQLException
	 */

	/**
	 * this test checks if we can pull a product from the database.
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	@Test
	public void testDatabaseAccessFindAProduct() throws SQLException, DataAccessException
	{	    
	    Product product = productDB.findProductById(1);
	    Assertions.assertNotNull(product);
	    Assertions.assertSame(product.getProductId(), 1);
	}

	
	/**
	 * this test checks if the list of product that the method FindAllProducts is not empty.
	 * findAllProducts gets all data from the product tabel in the database.
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	@Test
	public void testDatabaseAccessFindAllProducts() throws SQLException, DataAccessException
	{		
		Assertions.assertFalse(productDB.findAllProducts().isEmpty());
	}
}
