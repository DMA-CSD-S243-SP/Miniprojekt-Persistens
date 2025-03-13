package control;

import database.SaleOrderDaoImpl;

import java.sql.SQLException;

import database.DataAccessException;
import database.SaleOrderDB;
import model.SaleOrder;
import model.SaleOrderLine;

/**
 * Stateful controller for creating, reading, updating, and deleting SaleOrders
 * and SaleOrderLines. A new instance should be made for each sale.
 * 
 * @author Lumière Schack & Anders Have
 * @version 13/03/2025 - 15:25
 */
public class SaleOrderController
{
	private SaleOrder saleOrder;
	private SaleOrderLine saleOrderLine;
	
	public SaleOrderController() throws DataAccessException 
	{
		
	}
	
	public void startOrder()
	{
		saleOrder = new SaleOrder(null, null, false, null, null, false, null);
	}
	
	
	public void setEmployeeToSaleOrder(int employeeId) throws SQLException, DataAccessException 
	{
		EmployeeController emC = new EmployeeController();
		saleOrder.setEmployee(emC.findEmployeeById(employeeId));
	}
	
	
	public void setCustomerToSaleOrder(String emailAddress) throws DataAccessException, SQLException
	{
		CustomerController cuC = new CustomerController();
		saleOrder.setCustomer(cuC.findCustomerByEmail(emailAddress));
	}
	
	
	public void setProductToSaleOrderLine(int productId) throws DataAccessException
	{
		ProductController prC = new ProductController();
		
		saleOrderLine.setProduct(prC.findProductById(productId));
		saleOrderLine.setUnitSoldAtPrice(prC.findProductById(productId).getSalesPrice());
	}
	
	
	public void setProductQuantityToSaleOrderLine(int productQuantity) throws DataAccessException
	{		
		saleOrderLine.setQuantity(productQuantity);
		saleOrder.addSaleOrderLine(saleOrderLine);
	}
	
	
	public void finalizeSaleOrder()
	{

	}
	
	
	public void insertSaleOrderToDatabase() throws SQLException, DataAccessException
	{
		SaleOrderDaoImpl dao = new SaleOrderDB();
		dao.insertSaleOrderToDatabase(saleOrder);
	}
	
}
