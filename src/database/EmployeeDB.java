package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;


/**
 * TODO
 * 
 * @author Anders Have
 * @version 13/03/2025 - 11:00
 */
public class EmployeeDB implements EmployeeDaoImpl 
{
	private static final String FIND_ALL_QUERIES = "SELECT employeeId, firstName, lastName, title, phoneNumber, emailAddress, country, state,"
			+ "postalCode, streetName, houseNumber, floorNumber, doorNumber from employee";
	private static final String FIND_ALL_EMPLOYEEID_QUERY = FIND_ALL_QUERIES + "where employeeId = ?";
	
	private PreparedStatement findAllEmployee; 
	private PreparedStatement findByEmployeeId;
	
	public EmployeeDB() throws SQLException 
	{
		findAllEmployee = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_QUERIES);
		findByEmployeeId = DBConnection.getInstance().getConnection()
				.prepareStatement(FIND_ALL_EMPLOYEEID_QUERY);
	}
	
	
	@Override
	public List<Employee> findAllEmployees(boolean fullAssociation) throws DataAccessException
	{
		try 
		{
			ResultSet resultSet = findAllEmployee.executeQuery();
			List<Employee> res = buildObjects(resultSet);
			return res;
		} catch (SQLException e) 
		{
			throw new DataAccessException("Could not find all", e);
		}
	}


	@Override
	public Employee findEmployeeById(String employeeId, boolean fullAssociation) throws DataAccessException
	{
		try {
			findByEmployeeId.setString(1, employeeId);
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
					resultSet.getInt("employeeId"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("title"),
					resultSet.getString("phoneNumber"),
					resultSet.getString("emailAddress"),
					resultSet.getString("country"),
					resultSet.getString("state"),
					resultSet.getString("city"),
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
