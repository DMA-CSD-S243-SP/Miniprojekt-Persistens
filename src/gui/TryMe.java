package gui;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.SaleOrderController;
import database.DataAccessException;
import model.Customer;
import model.Employee;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;


/**
 * TryMe test class used for testing and as a proof of concept
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 14/03/2025 - 00:54
 */
public class TryMe
{
    
	
    public static void main(String[] args)
    {
    	// Create Products
        Product product1 = new Product(1, "Cowboyhat - Sandfarvet", 200.00, 250.00, "USA", 20);
        Product product2 = new Product(2, "Cowboyhat - Krokodilleskind", 225.00, 251.00, "USA", 20);
        Product product3 = new Product(3, "Cowboyhat - Mørk", 120.00, 200.00, "USA", 20);
        Product product4 = new Product(4, "Cowboyhat - Enhjørningskind", 225.00, 1500.00, "USA", 20);
        Product product5 = new Product(5, "Realistisk Revolver - 50 caliber", 50.00, 750.00, "Ungarn", 15);
        Product product6 = new Product(6, "Cowboystøvler - Brunt Læder", 90.00, 251.00, "Polen", 30);
        Product product7 = new Product(7, "Cowboystøvler - Sort Læder", 90.00, 500.00, "Polen", 30);
        Product product8 = new Product(8, "Hælsporre", 40.00, 200.00, "Ungarn", 30);
        Product product9 = new Product(9, "Buffaloslips", 60.00, 100.00, "USA", 40);
    	
    	// Create Employees
    	Employee employee1 = new Employee(1000, "Jens", "Hansen", "Manager", "12345678", "jens@example.com", "Danmark", "Hovedstaden", "København", 1000, "Strøget", 10, 2, "A");
        Employee employee2 = new Employee(1001, "Marie", "Nielsen", "Ekspedient", "87654321", "marie@example.com", "Danmark", "Midtjylland", "Aarhus", 8000, "Åboulevarden", 20, 3, "B");
    	Employee employee3 = new Employee(1002, "Anders", "Larsen", "Ekspedient", "11223344", "anders@example.com", "Danmark", "Syddanmark", "Odense", 5000, "Vestergade", 5, 1, "C");

    	// Create Customers
    	Customer customer1 = new Customer(101, "Mikkel", "Poulsen", "Regular", "22334455", "mikkel@example.com", true, "Danmark", "Hovedstaden", "Frederiksberg", 2000, "Gammel Kongevej", 15, 1, "1A");
        Customer customer2 = new Customer(102, "Sofie", "Jørgensen", "VIP", "33445566", "sofie@example.com", true, "Danmark", "Nordjylland", "Aalborg", 9000, "Boulevarden", 25, 2, "2B");
    	Customer customer3 = new Customer(103, "Rasmus", "Madsen", "Business", "44556677", "rasmus@example.com", false, "Danmark", "Sjælland", "Roskilde", 4000, "Algade", 30, 4, "3C");

    	
    	
    	
    	// Creates the lists we use to hold our different objects within
    	List<Employee> employeeList = new ArrayList<>();
    	List<Customer> customerList = new ArrayList<>();
    	List<Product> productList = new ArrayList<>();
    	
    	// Creates a SaleOrderController and store it within the saleOrderController variable
    	SaleOrderController saleOrderController = new SaleOrderController();

    	
    	//Step: 1
    	// Creates a new SaleOrder instance with default values.
    	saleOrderController.startSaleOrder();
    	
    	
    	
    	
    	//Step: 2
    	try
		{
			employeeList = saleOrderController.showAllEmployees();
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	//Step: 3
    	Employee employee = null;
    	
    	try
		{
    		employee = saleOrderController.selectEmployeeById(employee1.getEmployeeId());
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	// Step: 4
    	try
		{
    		saleOrderController.setEmployeeToSaleOrder(employee.getEmployeeId());
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	
    	
    	// Step: 5
    	try
		{
			customerList = saleOrderController.showAllCustomers();
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	// Step: 6
    	Customer customer = null;
    	
    	try
		{
    		customer = saleOrderController.selectCustomerByEmail(customer1.getEmailAddress());
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	
    	// Step: 7
    	try
		{
    		saleOrderController.setCustomerToSaleOrder(customer.getEmailAddress());
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	

    	// Step: 8
    	try
		{
			productList = saleOrderController.showAllProducts();
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	
    	// Step: 9
    	saleOrderController.createSaleOrderLine();
    	
    	
    	
    	
    	
    	// Step: 10
    	Product product = null;
    	
    	try
		{
    		product = saleOrderController.selectProductById(product.getProductId());
		}
    	
		// Attempts to catch exceptions of the DataAccessException type
		catch (DataAccessException exception)
		{
			// This is a generic exception usually thrown when there is an issue accessing the database
			exception.printStackTrace();
		}

		// Attempts to catch exceptions of the SQLException type
		catch (SQLException exception)
		{
			// This is typically thrown when an SQL operation fails for various reasons
			exception.printStackTrace();
		}
    	
    	
    	
    	
    	
    	
    	// Step: 11
    	SaleOrderLine saleOrderLine10 = saleOrderController.addProductToSalesOrderLine(1, product, product.getSalesPrice());
    	SaleOrderLine saleOrderLine11 = saleOrderController.addProductToSalesOrderLine(2, product, product.getSalesPrice());
    	SaleOrderLine saleOrderLine12 = saleOrderController.addProductToSalesOrderLine(5, product, product.getSalesPrice());

    	
    	
    	
    	
    	// Step: 12
    	saleOrderController.addSaleOrderLine(saleOrderLine10);
    	saleOrderController.addSaleOrderLine(saleOrderLine11);
    	saleOrderController.addSaleOrderLine(saleOrderLine12);
    	
    	
    	
    	// Step: 13
    	saleOrderController.getTotalOrderPrice();
    	
    	
    	
    	// Step: 14
    	saleOrderController.finalizeSaleOrder();
    	
    	
    	
    	
    	// Step: 15
    	saleOrderController.printSaleOrderDetails();
    	
    
    	
    	
    	// Step: 16
    	saleOrderController.printSaleOrderInvoice();
    	
    
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	

    	
/*
    	//----------------------------------//
        // Club member purchases for 1501	//
        //----------------------------------//        
         	
        // Create Sale Order with 3 selected products
        SaleOrder saleOrder1 = new SaleOrder(customer1, employee1, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine1 = new SaleOrderLine(1, product1, product1.getSalesPrice());
        SaleOrderLine saleOrderLine2 = new SaleOrderLine(1, product2, product2.getSalesPrice());
        SaleOrderLine saleOrderLine3 = new SaleOrderLine(5, product3, product3.getSalesPrice());
        
        // Adds sale order lines to the SaleOrder
        saleOrder1.addSaleOrderLine(saleOrderLine1);
        saleOrder1.addSaleOrderLine(saleOrderLine2);
        saleOrder1.addSaleOrderLine(saleOrderLine3);

        // Finalizes the sale order
        saleOrder1.finalizeSaleOrder();

        
        


    	//----------------------------------//
        // Club member purchases for 1500	//
        //----------------------------------//  
       
        // Create Sale Order with a selected products
        SaleOrder saleOrder2 = new SaleOrder(customer2, employee2, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine4 = new SaleOrderLine(1, product4, product4.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder2.addSaleOrderLine(saleOrderLine4);

        // Finalizes the sale order
        saleOrder2.finalizeSaleOrder();

        


    	//--------------------------------------//
        // Private customer purchases for 2501	//
        //--------------------------------------// 

        // Create Sale Order with a selected products
        SaleOrder saleOrder3 = new SaleOrder(customer3, employee3, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine5 = new SaleOrderLine(3, product5, product5.getSalesPrice());
        SaleOrderLine saleOrderLine6 = new SaleOrderLine(1, product6, product6.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder3.addSaleOrderLine(saleOrderLine5);
        saleOrder3.addSaleOrderLine(saleOrderLine6);

        // Finalizes the sale order
        saleOrder3.finalizeSaleOrder();



        
        
    	//--------------------------------------//
        // Private customer purchases for 2500	//
        //--------------------------------------// 
        
        // Create Employee
    	Employee employee4 = new Employee(1002, "Anders", "Larsen", "Ekspedient", "11223344", "anders@example.com", "Danmark", "Syddanmark", "Odense", 5000, "Vestergade", 5, 1, "C");
     	
        // Create Customer
    	Customer customer4 = new Customer(103, "Rasmus", "Madsen", "Business", "44556677", "rasmus@example.com", false, "Danmark", "Sjælland", "Roskilde", 4000, "Algade", 30, 4, "3C");
        
        // Create Sale Order with a selected products
        SaleOrder saleOrder4 = new SaleOrder(customer4, employee4, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine7 = new SaleOrderLine(4, product7, product7.getSalesPrice());
        SaleOrderLine saleOrderLine8 = new SaleOrderLine(2, product8, product8.getSalesPrice());
        SaleOrderLine saleOrderLine9 = new SaleOrderLine(1, product9, product9.getSalesPrice());

        // Adds sale order lines to the SaleOrder
        saleOrder4.addSaleOrderLine(saleOrderLine7);
        saleOrder4.addSaleOrderLine(saleOrderLine8);
        saleOrder4.addSaleOrderLine(saleOrderLine9);

        // Finalizes the sale order
        saleOrder4.finalizeSaleOrder();
*/
        
    }
}