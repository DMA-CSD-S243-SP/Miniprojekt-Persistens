package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;


/**
 * This class is responsible for accessing 
 * and managing employee objects stored in a database.
 * 
 * It implements the employeeDapImpl, 
 * meaning its implements its methods
 * 
 * @author Anders Have
 * @version 13/03/2025 - 13:45
 */
public class EmployeeDB implements EmployeeDaoImpl 
{
	// this selects all the colums in the employee tabel
	private static final String FIND_ALL_QUERIES = "SELECT id, firstName, lastName, title, phoneNumber, emailAddress, streetName,"
			+ "streetName, houseNumber, floorNumber, doorNumber, stateName, postalCode from Employee";
	// this builds on FIND_ALL_QUERIES by adding a filter and a placeholder.
	private static final String FIND_ALL_EMPLOYEEID_QUERY = FIND_ALL_QUERIES + " where id = ?";
	
	private PreparedStatement findAllEmployee; 
	private PreparedStatement findByEmployeeId;
	
	
	public EmployeeDB() throws SQLException 
	{
		findAllEmployee = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_QUERIES);
		findByEmployeeId = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_EMPLOYEEID_QUERY);
	}
	
	
	/**
	 * 
	 */
	@Override
	public List<Employee> findAllEmployees(boolean fullAssociation) throws DataAccessException
	{
		Connection con = DBConnection.getInstance().getConnection();
		try 
		{
			findAllEmployee = con.prepareStatement(FIND_ALL_QUERIES);
			ResultSet resultSet = findAllEmployee.executeQuery();
			List<Employee> res = buildObjects(resultSet);
			return res;
		} catch (SQLException e) 
		{
			throw new DataAccessException("Could not find all", e);
		}
	}


	@Override
	public Employee findEmployeeById(int employeeId, boolean fullAssociation) throws DataAccessException
	{
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByEmployeeId = con.prepareStatement(FIND_ALL_EMPLOYEEID_QUERY);
			findByEmployeeId.setInt(1, employeeId);
			ResultSet resultSet = findByEmployeeId.executeQuery();
			Employee em = null;
			if(resultSet.next()) 
			{
				em = buildObject(resultSet);
			}
			return em;
		} 
		catch (SQLException e) 
		{
			throw new DataAccessException("Could not find by id = " + employeeId, e);
		}	
		}
	
	private Employee buildObject(ResultSet resultSet) throws SQLException
		{
			Employee em = new Employee(
					resultSet.getInt("id"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("title"),
					resultSet.getString("phoneNumber"),
					resultSet.getString("emailAddress"),
					/*resultSet.getString("country")*/ null,
					resultSet.getString("stateName"),
					/*resultSet.getString("city")*/ null,
					resultSet.getInt("postalCode"),
					resultSet.getString("streetName"),
					resultSet.getInt("houseNumber"),
					resultSet.getInt("floorNumber"),
					resultSet.getString("doorNumber")
					);
			return em;
		}


	private List<Employee> buildObjects(ResultSet resultSet) throws SQLException
	{
		List<Employee> em = new ArrayList<>();
		while(resultSet.next()) 
		{
			em.add(buildObject(resultSet));
		}
		return em;
	}
}
