package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

/**
 * This class is responsible for accessing and managing employee objects stored
 * in a database.
 * 
 * It implements the employeeDapImpl, meaning its implements its methods
 * 
 * @author Anders Have & Christoffer Søndergaard
 * @version 19/03/2025 - 19:33
 */
public class EmployeeDB implements EmployeeDaoImpl
{
	// Selects all of the data within the Employee table in the database
	private static final String FIND_ALL_QUERIES = "SELECT id, firstName, lastName, title, phoneNumber, emailAddress, streetName, streetName, houseNumber, floorNumber, doorNumber, stateName, postalCode from Employee";

	// This extends on the FIND_ALL_QUERIES by adding a filtering condition with a placeholder for employee id
	private static final String FIND_ALL_EMPLOYEEID_QUERY = FIND_ALL_QUERIES + " where id = ?";

	// PreparedStatement for retrieving all employees from the database
	private PreparedStatement findAllEmployee;
	
	// PreparedStatement for retrieving an employee based on their email address
	private PreparedStatement findByEmployeeId;

	/**
	 * Constructor for EmployeeDB.
	 * Initializes prepared statements for executing SQL queries.
	 * 
	 * @throws SQLException if there is an issue with the database connection
	 */
	public EmployeeDB() throws SQLException
	{
		// Prepares the SQL statement for retrieving all employees
		findAllEmployee = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_QUERIES);
		
		// Prepares the SQL statement for retrieving an Employee by their employee id
		findByEmployeeId = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_EMPLOYEEID_QUERY);
	}

	
    /**
     * Retrieves all employees from the database.
     * 
     * @return a list of all employees
     * @throws DataAccessException if retrieval fails
     */
	@Override
	public List<Employee> findAllEmployees(boolean fullAssociation) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepare a SQL statement to retrieve all employees
			findAllEmployee = databaseConnection.prepareStatement(FIND_ALL_QUERIES);
			
			// Executes the prepared statement and stores the result set
			ResultSet employeeResultSet = findAllEmployee.executeQuery();
			
			// Converts the result set into a list of Employee objects
			List<Employee> reslistOfEmployees = buildObjects(employeeResultSet);
			
			// Returns the list of Employee objects
			return reslistOfEmployees;
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find Employee objects in the database", exception);
		}
	}

	
    /**
     * Finds an Employee object by searching for an employee with a matching employee id.
     * 
     * @param employeeId the id of the employee to search for
     * @return the corresponding Employee object, or null if not found
     * @throws DataAccessException if retrieval fails
     */
	@Override
	public Employee findEmployeeById(int employeeId, boolean fullAssociation) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepares a SQL statement to find and retrieve an Employee with a matching employee id
			findByEmployeeId = databaseConnection.prepareStatement(FIND_ALL_EMPLOYEEID_QUERY);
			
			// Adds the employee id provided in the method's parameter to the String instead of the placeholder
			findByEmployeeId.setInt(1, employeeId);
			
			// Executes the query, and stores the retrieved data in the variable named resultSet
			ResultSet resultSet = findByEmployeeId.executeQuery();
			
			// Creates and initializes an Employee object as null, which will later be populated with Employee specific data
			Employee employee = null;
			
			// Iterates through the resultSet while there are still more rows in the database's table
			if (resultSet.next())
			{
				// Converts the retrieved database row into an Employee object using the buildObject method
				employee = buildObject(resultSet);
			}
			
			// Returns the employee with a matching employee id or null if no employee has the specified employee id
			return employee;
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to find an Employee object with an employee id matching: " + employeeId, exception);
		}
	}

	
    /**
     * Builds a specific Employee object from a database result set.
     * 
     * @param resultSet the result set containing Employee data
     * @return an Employee object with the extracted data
     * @throws SQLException if accessing the result set fails
     */
	private Employee buildObject(ResultSet resultSet) throws SQLException
	{
		// Creates a Employee object stored within the employee variable based off of the method's provided resultSet
		Employee employee = new Employee(
				resultSet.getInt("id"),
				resultSet.getString("firstName"),
				resultSet.getString("lastName"),
				resultSet.getString("title"),
				resultSet.getString("phoneNumber"),
				resultSet.getString("emailAddress"),
				// country - We supply null here, as country can be deducted from the supplied State, hence no need to waste storage within the database for this
				null,
				
				resultSet.getString("stateName"),
				
				// city - We supply null here, as country can be deducted from the supplied Postal Code, hence no need to waste storage within the database for this
				null,
				resultSet.getInt("postalCode"),
				resultSet.getString("streetName"),
				resultSet.getInt("houseNumber"),
				resultSet.getInt("floorNumber"),
				resultSet.getString("doorNumber")
				);
		
		return employee;
	}

	
    /**
     * Converts a result set into a list of Employee objects.
     * 
     * @param resultSet the result set containing multiple Employee records
     * @return a list of Employee objects
     * @throws SQLException if accessing the result set fails
     */
	private List<Employee> buildObjects(ResultSet resultSet) throws SQLException
	{
		// Creates an empty list to store Employee objects within
		List<Employee> em = new ArrayList<>();
		
		// Iterates through the result set while there are still more rows in the database's table
		while (resultSet.next())
		{
			// Converts each row into a Employee object and add it to the list
			em.add(buildObject(resultSet));
		}
		
		// Returns the populated list of Employee objects
		return em;
	}
}
