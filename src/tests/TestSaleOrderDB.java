package tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import database.DBConnection;
import database.DataAccessException;
import database.SaleOrderDB;
import model.Customer;
import model.Employee;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

public class TestSaleOrderDB
{
	private SaleOrderDB saleOrderDB;
	private SaleOrder saleOrder;
	private Employee employee;
	private Customer customer;
	private SaleOrderLine saleOrderLine;
	private Product p;
	private static int orderId;

	// opens connection to the database
	@BeforeAll
	public static void setUp()
	{
		DBConnection.getInstance().getConnection();
	}

	@BeforeEach
	public void setUpDB() throws SQLException
	{
		saleOrderDB = new SaleOrderDB();
		saleOrder = new SaleOrder(null, null, false, null, null, false, null);
		employee = new Employee(1000, "John", "Smith", "boss", "11111111", "johnsmith@example.com", "Denmark",
				"region midtjylland", "Mønsted", 8800, "Badstreet", 12, 0, null);
		customer = new Customer(101, "John", "Doe", "Private", "12121212", "mikkel@example.com", false, "Denmark",
				"region sydjylland", "Sønderborg", 1121, "Alexgade", 13, 5, "12B");
		p = new Product(1, "nuclear bomb", 100, 250, "Russia", 10);
	}

	@AfterAll
	public static void tearDown()
	{
		// Removes the added data 
		try
		{
			new SaleOrderDB().removeSaleOrder(orderId);
		} catch (Exception e) {}

	}

	@Test
	public void TestInsertOrder() throws DataAccessException, SQLException
	{
		saleOrder.setCustomer(customer);

		saleOrder.setEmployee(employee);

		saleOrderLine = new SaleOrderLine(1, p, 250);
		saleOrder.addSaleOrderLine(saleOrderLine);

		saleOrder.setCreatedDate(LocalDate.now());
		saleOrder.setDeliveryDate(LocalDate.now().plusDays(7));
		saleOrder.setPaymentDate(LocalDate.now().plusDays(30));
		orderId = saleOrder.getOrderId();

		Assertions.assertDoesNotThrow(() -> saleOrderDB.insertSaleOrderToDatabase(saleOrder));
	}
}
