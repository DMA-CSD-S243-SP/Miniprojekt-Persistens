package tests;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.CustomerDB;
import database.DBConnection;
import database.DataAccessException;

/** 
 * a test class for testing the database class CustomerDB
 * 
 * @author Anders Have
 * @version 14/03/2025 - 8:50
 */
public class TestCustomerDB 
{
	private CustomerDB customerDB;
	
	//opens connection to the database
	@BeforeAll
	public static void setUp() 
	{		
		DBConnection.getInstance().getConnection();
	}
	
	@BeforeEach
	public void setUpDB() throws SQLException
	{
		customerDB = new CustomerDB();
	}
	
	// closes  the connection to the database after each test
	@AfterAll
	public static void tearDown()
	{
		DBConnection.getInstance().disconnect();
	}

	/**
	 * this test checks if the list of customers that the method FindAllCustomers is not empty.
	 * findAllCustomer gets all data from the customer tabel in the database.
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	@Test
	public void testDatabaseAccessFindAllCustomers() throws SQLException, DataAccessException
	{
		Assertions.assertFalse(customerDB.findAllCustomers().isEmpty());
	}
}
