package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.SaleOrder;
import model.SaleOrderLine;


/**
 * This class is responsible for accessing 
 * and managing Saleorder objects stored in a database.
 * 
 * It implements  SaleOrderDaoImpl, 
 * meaning its follows inplements its methods 
 * 
 * @author Anders Have
 * @version 13/03/2025 - 12:45
 */
public class SaleOrderDB implements SaleOrderDaoImpl 
{
	// it inserts a new SaleOrder into the table with specified values
	private static final String INSERTSALEORDER_QUERY = "INSERT into SaleOrder (orderId, customerId, invoiceNumber, "
			+ "emailAddress, employeeId, createdDate, deliveryDate, deliveryStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_SALEORDERLINE_QUERY = "INSERT INTO SaleOrderLine (productId, quantity, orderId) VALUES (?, ?, ?)";
	private static final String DELETE_SALEORDER_QUERY = "DELETE FROM SaleOrder WHERE orderId = ?";
	private static final String DELETE_SALEORDERLINE_QUERY = "DELETE FROM SaleOrderLine WHERE orderId = ?";
	
	private PreparedStatement insertSaleOrderToDatabasePreparedStatement;
	private PreparedStatement insertSaleOrderLinePreparedStatement;
	private PreparedStatement deleteSaleOrderStatement;
	private PreparedStatement deleteSaleOrderLineStatement;

	
	public SaleOrderDB() throws SQLException
	{
		insertSaleOrderToDatabasePreparedStatement = DBConnection.getInstance().getConnection().prepareStatement(INSERTSALEORDER_QUERY);
	}
	
	
	/**
	 * this method inserts the SaleOrder object into the SQLDatabase with the assiged values
	 * @throws SQLException 
	 */
	@Override
	public SaleOrder insertSaleOrderToDatabase(SaleOrder saleOrder) throws DataAccessException, SQLException 
	{
		Connection con = DBConnection.getInstance().getConnection();
		try
		{
		insertSaleOrderToDatabasePreparedStatement = con.prepareStatement(INSERTSALEORDER_QUERY);
		// fills the ?'s in the preparedStatement with the values from the SaleOrder object 
		insertSaleOrderToDatabasePreparedStatement.setInt(1, saleOrder.getOrderId());

		insertSaleOrderToDatabasePreparedStatement.setInt(2, saleOrder.getCustomer().getCustomerNumber());
		//insertSaleOrderToDatabasePreparedStatement.setInt(3, saleOrder.getInvoiceNumber());
		insertSaleOrderToDatabasePreparedStatement.setNull(3, Types.INTEGER); // Invoices are not implemented
		insertSaleOrderToDatabasePreparedStatement.setString(4, saleOrder.getCustomer().getEmailAddress());
		insertSaleOrderToDatabasePreparedStatement.setInt(5, saleOrder.getEmployee().getEmployeeId());
		insertSaleOrderToDatabasePreparedStatement.setDate(6, java.sql.Date.valueOf(saleOrder.getCreatedDate()));
		insertSaleOrderToDatabasePreparedStatement.setDate(7, java.sql.Date.valueOf(saleOrder.getPaymentDate()));
		
		insertSaleOrderToDatabasePreparedStatement.setBoolean(8, saleOrder.isDeliveryStatus());

		//executes the PreparedStatement
		insertSaleOrderToDatabasePreparedStatement.executeUpdate();
			for (SaleOrderLine saleOL : saleOrder.getOrderLines())
			{
				insertSaleOrderLineToDatabase(saleOL, saleOrder.getOrderId());
			}
		}
		catch (SQLException exception)
		{
			throw new DataAccessException("could not insert into database", exception);
		}
		
		
		return saleOrder;
	}
	
	private SaleOrderLine insertSaleOrderLineToDatabase(SaleOrderLine saleOL, int SaleOrderId) throws DataAccessException
	{
		Connection con = DBConnection.getInstance().getConnection();
		try
		{
			insertSaleOrderLinePreparedStatement = con.prepareStatement(INSERT_SALEORDERLINE_QUERY);
			
			insertSaleOrderLinePreparedStatement.setInt(1, saleOL.getProduct().getProductId());
			insertSaleOrderLinePreparedStatement.setInt(2, saleOL.getQuantity());
			insertSaleOrderLinePreparedStatement.setInt(3, SaleOrderId);
			
			insertSaleOrderLinePreparedStatement.executeUpdate();
		} catch (SQLException exception)
		{
			throw new DataAccessException("could not insert into database", exception);
		}
		return saleOL;
	}
	
	public void removeSaleOrder(int orderId) throws DataAccessException
	{
		Connection con = DBConnection.getInstance().getConnection();
		try
		{
			deleteSaleOrderLineStatement = con.prepareStatement(DELETE_SALEORDERLINE_QUERY);
			deleteSaleOrderStatement = con.prepareStatement(DELETE_SALEORDER_QUERY);
			deleteSaleOrderLineStatement.setInt(1, orderId);
			deleteSaleOrderStatement.setInt(1, orderId);
			deleteSaleOrderLineStatement.execute();
			deleteSaleOrderStatement.execute();
		} catch (SQLException exception)
		{
			throw new DataAccessException("could not insert into database", exception);
		}
	}
}
