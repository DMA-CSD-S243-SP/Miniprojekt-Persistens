package control;

import database.SaleOrderDaoImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.DataAccessException;
import database.SaleOrderDB;
import model.Customer;
import model.Employee;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

/**
 * Controller for creating, reading, updating, and deleting SaleOrders and
 * SaleOrderLines. A new instance should be made for each sale.
 * 
 * @author Lumière Schack, Anders Have & Christoffer Søndergaard
 * @version 13/03/2025 - 23:52
 */
public class SaleOrderController
{
	private EmployeeController employeeController;
	private CustomerController customerController;
	private ProductController productController;

	private SaleOrder saleOrder;

	
    /**
     * Constructs a SaleOrderController and initializes necessary controllers.
     * 
     * @throws DataAccessException if database access fails.
     */
	public SaleOrderController() throws DataAccessException
	{
		// Creates a productController
		employeeController = new EmployeeController();
		productController = new ProductController();
		customerController = new CustomerController();
	}

	
    /**
     * Starts a new sale order with default values.
     */
	public void startSaleOrder()
	{
		// Creates a saleOrder object in a very bare-bone state with associations yet to be formed
		saleOrder = new SaleOrder(null, null, false, null, null, false, null);
	}
	

    /**
     * Retrieves a list of all employees.
     * 
     * @return a list of employees.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public List<Employee> showAllEmployees() throws DataAccessException, SQLException
	{
		// Attempts to execute the code within the braces
		try
		{
			// Assigns the employeeController instance to find all employees
			return employeeController.findAllEmployees();
		}

		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
	}

	
    /**
     * Retrieves an employee by their ID.
     * 
     * @param employeeId the ID of the employee.
     * @return the Employee object.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public Employee selectEmployeeById(int employeeId) throws DataAccessException, SQLException
	{
		// Attempts to execute the code within the braces
		try
		{
			// Assigns the employeeController instance to find an employeeId matching the one supplied in the method parameter
			return employeeController.findEmployeeById(employeeId);
		}
		
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
	}

	
    /**
     * Assigns an employee to the current sale order.
     * 
     * @param employeeId the ID of the employee.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public void setEmployeeToSaleOrder(int employeeId) throws DataAccessException, SQLException
	{
		Employee employee;
		 
		try
		{
			// Assigns the employeeController instance to find an employeeId matching the
			// one supplied in the method parameter and store it within the employee variable 
			employee = employeeController.findEmployeeById(employeeId);
		}
		
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
		
		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
	 
		 // Sets the saleOrder instance's employee attribute to the specified employee, thereby forming an association between the two
		 saleOrder.setEmployee(employee);
	}
	
	
    /**
     * Retrieves a list of all customers.
     * 
     * @return a list of customers.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public List<Customer> showAllCustomers() throws DataAccessException, SQLException
	{
		// Attempts to execute the code within the braces
		try
		{
			// Assigns the employeeController instance to find all customers
			return customerController.findAllCustomers();
		}

		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
	}

	
    /**
     * Finds a customer by their email address.
     * 
     * @param customerEmail the email of the customer.
     * @return the Customer object.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public Customer selectCustomerByEmail(String customerEmail) throws DataAccessException, SQLException
	{
		// Attempts to execute the code within the braces
		try
		{
			// Assigns the customerController instance to find a customerEmail matching the one supplied in the method parameter
			return customerController.findCustomerByEmail(customerEmail);
		}
		
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
	}

	
    /**
     * Assigns a customer to the current sale order.
     * 
     * @param emailAddress the email of the customer.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public void setCustomerToSaleOrder(String emailAddress) throws DataAccessException, SQLException
	{
		// Assigns the customerController instance to find an emailAddress matching the
		// one supplied in the method parameter and store it within the customer
		// variable
		Customer customer = customerController.findCustomerByEmail(emailAddress);

		// Sets the saleOrder instance's customer attribute to the specified customer,
		// thereby forming an association between the two
		saleOrder.setCustomer(customer);
	}
	
	
    /**
     * Retrieves a list of all products.
     * 
     * @return a list of products.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public List<Product> showAllProducts() throws DataAccessException, SQLException
	{
		// Attempts to execute the code within the braces
		try
		{
			// Assigns the productController instance to find all products
			return productController.findAllProducts();
		}

		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
		
/*	- TODO reenable this once exception handling is added
		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
*/
	}

	
    /**
     * Creates a new SaleOrderLine.
     */
	public void createSaleOrderLine()
	{
		SaleOrderLine saleOrderLine = new SaleOrderLine(0, null, 0);
	}
	
	
    /**
     * Finds a product by its ID.
     * 
     * @param productId the ID of the product.
     * @return the Product object.
     * @throws DataAccessException if data retrieval fails.
     * @throws SQLException if an SQL error occurs.
     */
	public Product selectProductById(int productId) throws DataAccessException, SQLException
	{
		// Attempts to execute the code within the braces
		try
		{
			// Assigns the productController instance to find a productId matching the one supplied in the method parameter
			return productController.findProductById(productId);
		}
		
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
/*
		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons such as:
			// - invalid sql syntax
			// - connection failure
			// - constraint violations
			// - missing tables or columns
			exception.printStackTrace();

			// Rethrows the exception in order to notify the caller of this method
			throw exception;
		}
*/
	}
	 
	
    /**
     * Adds a product to a SaleOrderLine.
     * 
     * @param quantity the quantity of the product.
     * @param product the product object.
     * @param unitSoldAtPrice the price per unit.
     * @return the created SaleOrderLine.
     */
	public SaleOrderLine addProductToSalesOrderLine(int quantity, Product product, double unitSoldAtPrice)
	{
		// Creates a saleOrderLine object with the specified quantity, for the specified product with the supplied unit price
		SaleOrderLine saleOrderLine = new SaleOrderLine(quantity, product, unitSoldAtPrice);
		
		return saleOrderLine;
	}


    /**
     * Adds a SaleOrderLine to the sale order.
     * 
     * @param saleOrderLine the SaleOrderLine to be added.
     */
	public void addSaleOrderLine(SaleOrderLine saleOrderLine)
	{
		// Adds the saleOrderLine to the saleOrder
		saleOrder.addSaleOrderLine(saleOrderLine);
	}
		
	
    /**
     * Calculates the total price of the sale order.
     */
	public void getTotalOrderPrice()
	{
		// Calculates the total order price, taking club membership and private customer, along with
		// payment thresholds for respective roles in to consideration, and subtracting discounts and 
		// freight  charges depending on what the customer is entitled to
		saleOrder.getTotalOrderPrice();
	}
	
	
    /**
     * Finalizes the sale order by setting necessary dates and statuses.
     */
	public void finalizeSaleOrder()
	{
		// Sets the createdDate attribute of the saleOrder instance to today's date
		saleOrder.setCreatedDate(LocalDate.now());
		
		// Sets the deliveryDate attribute of the saleOrder instance to 7 day's from today's date
		saleOrder.setDeliveryDate(saleOrder.getDeliveryDate());
		
		// Sets the paymentDate attribute of the saleOrder instance to 30 day's from today's date
		saleOrder.setPaymentDate(saleOrder.getPaymentDate());

		// Sets the invoiceStatus attribute to false, to indicate the invoice has not been paid yet
		saleOrder.setInvoiceStatus(false);
		
		// Sets the deliveryStatus attribute to false, to indicate the package has not been delivered to the customer yet
		saleOrder.setDeliveryStatus(false);
	}

	
    /**
     * Prints details of the sale order.
     */
	public void printSaleOrderDetails()
	{
		saleOrder.printSaleOrder();
	}
	
	
    /**
     * Prints the invoice of the sale order.
     */
	public void printSaleOrderInvoice()
	{
		saleOrder.printInvoice();
	}
	

    /**
     * Inserts the sale order into the database.
     * 
     * @throws SQLException if an SQL error occurs.
     * @throws DataAccessException if data access fails.
     */
	public void insertSaleOrderToDatabase() throws SQLException, DataAccessException
	{
		SaleOrderDaoImpl dao = new SaleOrderDB();
		dao.insertSaleOrderToDatabase(saleOrder);
	}
}
