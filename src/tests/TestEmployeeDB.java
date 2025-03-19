package tests;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBConnection;
import database.DataAccessException;
import database.EmployeeDB;

/** 
 * a test class for testing the database class EmployeeDB
 * 
 * @author Anders Have
 * @version 14/03/2025 - 10:10
 */
public class TestEmployeeDB 
{
	private EmployeeDB employeeDB;
	
	//opens connection to the database
	@BeforeAll
	public static void setUp() 
	{		
		DBConnection.getInstance().getConnection();
	}
	
	@BeforeEach
	public void setUpDB() throws SQLException
	{
		employeeDB = new EmployeeDB();	
	}
	
	@AfterAll
	public static void tearDown()
	{
		
	}

	/**
	 * this test checks if the list of employees that the method FindAllEmployees is not empty.
	 * findAllEmployees gets all data from the employee tabel in the database.
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	@Test
	public void testDatabaseAccessFindAllEmployees() throws SQLException, DataAccessException
	{		
		Assertions.assertFalse(employeeDB.findAllEmployees(false).isEmpty());
	}
}
