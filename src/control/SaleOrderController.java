package control;

import database.SaleOrderDaoImpl;
import database.SaleOrderDB;
import model.SaleOrder;
import model.SaleOrderLine;

/**
 * Stateful controller for creating, reading, updating, and deleting SaleOrders
 * and SaleOrderLines. A new instance should be made for each sale.
 * 
 * @author Lumière Schack
 */
public class SaleOrderController
{
	private SaleOrder saleOrder;
}
