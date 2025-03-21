package control;

import java.sql.SQLException;
import java.util.List;

import database.EmployeeDaoImpl;
import database.DataAccessException;
import database.EmployeeDB;
import model.Employee;

/**
 * Controller for creating, reading, updating, and deleting Employees.
 * 
 * @author Lumière Schack & Christoffer Søndergaard
 * @date 14.03.2025 - 00:01
 */
public class EmployeeController
{
	/**
	 * Gets information about all employees in the database, but not their full association.
	 * @return A list containing shallow representations of each employee.
	 * @throws DataAccessException 
	 * @throws SQLException 
	 */
	public List<Employee> findAllEmployees() throws DataAccessException, SQLException
	{
		EmployeeDaoImpl dao = new EmployeeDB();
		return dao.findAllEmployees(false);
	}
	
	/**
	 * 
	 * @param id - The id of the desired employee.
	 * @return The employee whom the id refers to.
	 * @throws SQLException 
	 * @throws DataAccessException 
	 */
	public Employee findEmployeeById(int employeeId) throws SQLException, DataAccessException
	{
		EmployeeDaoImpl dao = new EmployeeDB();
		return dao.findEmployeeById(employeeId, false);
	}
}
