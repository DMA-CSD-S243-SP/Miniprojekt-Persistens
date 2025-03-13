package control;

import java.util.List;

import database.EmployeeDaoImpl;
import database.EmployeeDB;
import model.Employee;

/**
 * Controller for creating, reading, updating, and deleting Employees.
 * 
 * @author Lumière Schack
 */
public class EmployeeController
{
	/**
	 * Gets information about all employees in the database, but not their full association.
	 * @return A list containing shallow representations of each employee.
	 */
	public List<Employee> findAllCustomers()
	{
		EmployeeDaoImpl dao = new EmployeeDB();
		return dao.findAllEmployees(false);
	}
	
	/**
	 * 
	 * @param id - The id of the desired employee.
	 * @return The employee whom the id refers to.
	 */
	public Employee findEmployeeById(String employeeId)
	{
		EmployeeDaoImpl dao = new EmployeeDB();
		return dao.findEmployeeById(employeeId);
	}
}
