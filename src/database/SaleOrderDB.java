package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.SaleOrder;


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
			+ "emailAddress, employeeId, createdDate, deliveryDate, deliveryStatus) VALUES (? ? ? ? ? ? ? ?)";
	
	private PreparedStatement insertSaleOrderToDatabasePreparedStatement;

	
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
		try
		{
		// fills the ?'s in the preparedStatement with the values from the SaleOrder object 
		insertSaleOrderToDatabasePreparedStatement.setInt(1, saleOrder.getOrderId());
		insertSaleOrderToDatabasePreparedStatement.setInt(2, saleOrder.getCustomer().getCustomerNumber());
		insertSaleOrderToDatabasePreparedStatement.setInt(3, saleOrder.getInvoiceNumber());
		insertSaleOrderToDatabasePreparedStatement.setString(4, saleOrder.getCustomer().getEmailAddress());
		insertSaleOrderToDatabasePreparedStatement.setInt(5, saleOrder.getEmployee().getEmployeeId());
		insertSaleOrderToDatabasePreparedStatement.setDate(6, java.sql.Date.valueOf(saleOrder.getCreatedDate()));
		insertSaleOrderToDatabasePreparedStatement.setDate(7, java.sql.Date.valueOf(saleOrder.getPaymentDate()));
		insertSaleOrderToDatabasePreparedStatement.setBoolean(8, saleOrder.isDeliveryStatus());

		//executes the PreparedStatement
		insertSaleOrderToDatabasePreparedStatement.executeUpdate();
		}
		catch (SQLException exception)
		{
			throw new DataAccessException("could not insert into database", exception);
		}
		return saleOrder;
	}
}
