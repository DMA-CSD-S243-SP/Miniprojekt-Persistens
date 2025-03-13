package database;

import java.util.List;

import model.Employee;

/**
 * Interface responsible for dictating how the Employee table can be accessed.
 * 
 * @author Lumière Schack
 */
public interface EmployeeDaoImpl
{
	/**
	 * Method creates a list containing a shallow clone of every employee in the
	 * database. If fullAssociation is false then associations will only be filled
	 * with information found in the respective row of each employee in the Employee
	 * table. Otherwise associations will be filled in with the information from the
	 * row that the employee has a key to.
	 * 
	 * @param fullAssociation - Whether the Employees' associations should be looked
	 *                        up in the database.
	 * @return A list containing a shallow clone of every Employee in the database.
	 */
	List<Employee> findAllEmployees(boolean fullAssociation) throws DataAccessException;

	/**
	 * Method creates a shallow clone of the Employee that has the given id. If no
	 * matching employee is found the method returns null. If fullAssociation is
	 * false then associations will only be filled with information found in the
	 * respective row of the employee in the Employee table. Otherwise associations
	 * will be filled in with the information from the row the that employee has a key
	 * to.
	 * 
	 * @param employeeId      - The id used for the querry.
	 * @param fullAssociation - Whether the Employee's associations should be looked
	 *                        up in the database.
	 * @return A shallow clone of the Employee that has an id that matches the
	 *         querry or NULL if no match is found.
	 * @throws DataAccessException 
	 */
	public Employee findEmployeeById(int employeeId, boolean fullAssociation) throws DataAccessException;
}
