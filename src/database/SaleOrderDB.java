package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.SaleOrder;
import model.SaleOrderLine;


/**
 * This class is responsible for accessing and managing Saleorder objects stored
 * in a database.
 * 
 * It implements the SaleOrderDaoImpl, meaning it implements its methods
 * 
 * @author Anders Have & Christoffer Søndergaard
 * @version 19/03/2025 - 20:48
 */
public class SaleOrderDB implements SaleOrderDaoImpl
{
	// SQL query for inserting a new SaleOrder into the database
	private static final String INSERTSALEORDER_QUERY = "INSERT into SaleOrder (orderId, customerId, invoiceNumber, emailAddress, employeeId, createdDate, deliveryDate, deliveryStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	// SQL query for inserting a SaleOrderLine into the database
	private static final String INSERT_SALEORDERLINE_QUERY = "INSERT INTO SaleOrderLine (productId, quantity, orderId) VALUES (?, ?, ?)";

	// SQL query for deleting a SaleOrder from the database
	private static final String DELETE_SALEORDER_QUERY = "DELETE FROM SaleOrder WHERE orderId = ?";

	// SQL query for deleting all SaleOrderLines related to a specific SaleOrder
	private static final String DELETE_SALEORDERLINE_QUERY = "DELETE FROM SaleOrderLine WHERE orderId = ?";

	// PreparedStatement are used to execute queries efficiently, and prevent SQL injections
	// PreparedStatement for inserting new SaleOrder into the database
	private PreparedStatement insertSaleOrderToDatabasePreparedStatement;
	
	// PreparedStatement for inserting new SaleOrderLine into the database
	private PreparedStatement insertSaleOrderLinePreparedStatement;
	
	// PreparedStatement for deleting a SaleOrder from the database
	private PreparedStatement deleteSaleOrderStatement;
	
	// PreparedStatement for deleting a SaleOrderLine from the database
	private PreparedStatement deleteSaleOrderLineStatement;

	
    /**
     * Constructor for SaleOrderDB.
     * Initializes the prepared statements for executing SQL queries.
     * 
     * @throws SQLException if a database access error occurs
     */
	public SaleOrderDB() throws SQLException
	{
		// Prepares the SQL statement for inserting a SaleOrder objects into the database
		insertSaleOrderToDatabasePreparedStatement = DBConnection.getInstance().getConnection().prepareStatement(INSERTSALEORDER_QUERY);
	}
	

    /**
     * Inserts a SaleOrder object into the database with the assigned values.
     * 
     * @param saleOrder The SaleOrder object to be inserted
     * @return The inserted SaleOrder object
     * @throws DataAccessException If there is an issue accessing the database
     * @throws SQLException If an SQL error occurs
     */
	@Override
	public SaleOrder insertSaleOrderToDatabase(SaleOrder saleOrder) throws DataAccessException, SQLException
	{
		// Gets a connection to the database
		Connection databaseConnection  = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepares the SQL statement for inserting a SaleOrder
			insertSaleOrderToDatabasePreparedStatement = databaseConnection.prepareStatement(INSERTSALEORDER_QUERY);

            // Fills the placeholders "?" in the SQL statement with values from the SaleOrder object
			insertSaleOrderToDatabasePreparedStatement.setInt(1, saleOrder.getOrderId());
			insertSaleOrderToDatabasePreparedStatement.setInt(2, saleOrder.getCustomer().getCustomerNumber());

			// NOTE: Invoices are not implemented
			insertSaleOrderToDatabasePreparedStatement.setNull(3, Types.INTEGER);
			insertSaleOrderToDatabasePreparedStatement.setString(4, saleOrder.getCustomer().getEmailAddress());
			insertSaleOrderToDatabasePreparedStatement.setInt(5, saleOrder.getEmployee().getEmployeeId());
			insertSaleOrderToDatabasePreparedStatement.setDate(6, java.sql.Date.valueOf(saleOrder.getCreatedDate()));
			insertSaleOrderToDatabasePreparedStatement.setDate(7, java.sql.Date.valueOf(saleOrder.getPaymentDate()));
			insertSaleOrderToDatabasePreparedStatement.setBoolean(8, saleOrder.isDeliveryStatus());

			// Executes the prepared statement to insert the SaleOrder
			insertSaleOrderToDatabasePreparedStatement.executeUpdate();
			
			// Iterates through each SaleOrderLine in the SaleOrder
			for (SaleOrderLine saleOrderLine : saleOrder.getOrderLines())
			{
				// Inserts each SaleOrderLine associated with this SaleOrder based on the OrderId
				insertSaleOrderLineToDatabase(saleOrderLine, saleOrder.getOrderId());
			}
		}
		
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to insert SaleOrder in to the database", exception);
		}

		return saleOrder;
	}

	
    /**
     * Inserts a SaleOrderLine into the database for a specific SaleOrder.
     * 
     * @param saleOrderLine The SaleOrderLine to be inserted
     * @param saleOrderId The orderId of the SaleOrder that the SaleOrderLine is associated with
     * @return The inserted SaleOrderLine object
     * @throws DataAccessException If there is an issue accessing the database
     */
	private SaleOrderLine insertSaleOrderLineToDatabase(SaleOrderLine saleOL, int SaleOrderId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection  = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepares the SQL statement for inserting a SaleOrderLine
			insertSaleOrderLinePreparedStatement = databaseConnection.prepareStatement(INSERT_SALEORDERLINE_QUERY);

            // Fills the placeholders in the SQL statement with values from the SaleOrderLine object
			insertSaleOrderLinePreparedStatement.setInt(1, saleOL.getProduct().getProductId());
			insertSaleOrderLinePreparedStatement.setInt(2, saleOL.getQuantity());
			insertSaleOrderLinePreparedStatement.setInt(3, SaleOrderId);

			// Executes the prepared statement to insert the SaleOrderLine
			insertSaleOrderLinePreparedStatement.executeUpdate();
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to insert SaleOrderLine into the database", exception);
		}
		
		return saleOL;
	}

	
    /**
     * Removes a SaleOrder and its associated SaleOrderLines from the database.
     * 
     * @param orderId The ID of the SaleOrder to be removed
     * @throws DataAccessException If there is an issue accessing the database
     */
	public void removeSaleOrder(int orderId) throws DataAccessException
	{
		// Gets a connection to the database
		Connection databaseConnection  = DBConnection.getInstance().getConnection();
		
		try
		{
			// Prepares the SQL statement for deleting SaleOrderLines associated with the given orderId
			deleteSaleOrderLineStatement = databaseConnection.prepareStatement(DELETE_SALEORDERLINE_QUERY);

			// Prepares the SQL statement for deleting the SaleOrder with the given orderId
			deleteSaleOrderStatement = databaseConnection.prepareStatement(DELETE_SALEORDER_QUERY);
			
			// Sets the orderId parameter for both of the two prepared statements
			deleteSaleOrderLineStatement.setInt(1, orderId);
			deleteSaleOrderStatement.setInt(1, orderId);
			
			// Executes the prepared statements to delete the SaleOrder and its associated SaleOrderLines
			deleteSaleOrderLineStatement.execute();
			deleteSaleOrderStatement.execute();
		}
		
		catch (SQLException exception)
		{
			// If an SQL error occurs a custom exception is thrown with the specified details
			throw new DataAccessException("Unable to remove the SaleOrder from the database", exception);
		}
	}
}
