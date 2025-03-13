package database;

import java.sql.SQLException;

import model.SaleOrder;

/**
 * @author Anders Have
 * @version 13/03/2025 - 12:05
 */
public interface SaleOrderDaoImpl 
{
	// inserts a new SaleOrder into the SaleOrder table in the Database.
	SaleOrder insertSaleOrderToDatabase(SaleOrder SaleOrder) throws DataAccessException, SQLException;
}
