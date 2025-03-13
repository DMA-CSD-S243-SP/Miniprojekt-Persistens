package model;


/**
 * Represents a line item in a sale order, containing information about the
 * product, quantity, and the price at which the product was sold.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 13/03/2025 - 11:48
 */
public class SaleOrderLine
{
	private int quantity;
	private Product product;
	private double unitSoldAtPrice;

	
    /**
     * Constructs a SaleOrderLine with the specified quantity, product, and unit price.
     *
     * @param quantity the number of products in this sale order line.
     * @param product the product being sold.
     * @param unitSoldAtPrice the price per unit of the product.
     */
    public SaleOrderLine(int quantity, Product product, double unitSoldAtPrice)
    {
        this.quantity = quantity;
        this.product = product;
        this.unitSoldAtPrice = unitSoldAtPrice;
    }

	
	/**
	 * Retrieves the quantity of the product in the sale order line.
	 *
	 * @return the quantity of the product.
	 */
	public int getQuantity()
	{
		return quantity;
	}

	
	/**
	 * Sets the quantity of the product in the sale order line.
	 *
	 * @param quantity the number of products in this sale order line.
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	
	/**
	 * Retrieves the product associated with this sale order line.
	 *
	 * @return the product in the sale order line.
	 */
	public Product getProduct()
	{
		return product;
	}

	
	/**
	 * Sets the product for this sale order line.
	 *
	 * @param product the product to be set.
	 */
	public void setProduct(Product product)
	{
		this.product = product;
	}

	
	/**
	 * Retrieves the unit price at which the product was sold.
	 *
	 * @return the unit price of the product's sales price.
	 */
	public double getUnitSoldAtPrice()
	{
		return this.product.getSalesPrice();
	}

	
	/**
	 * Sets the unit price at which the product was sold.
	 *
	 * @param unitSoldAtPrice the price per unit of the product.
	 */
	public void setUnitSoldAtPrice(double unitSoldAtPrice)
	{
		this.unitSoldAtPrice = unitSoldAtPrice;
	}

	
	/**
	 * Adds a product to the sale order line with a specified quantity and current sales price.
	 *
	 * @param product         the product to be added.
	 * @param unitSoldAtPrice the price per unit of the product.
	 * @param quantity        the quantity of the product being added.
	 */
	public void addProductToSaleOrderLine(Product product, double unitSoldAtPrice, int quantity)
	{
		this.product = product;
		this.unitSoldAtPrice = unitSoldAtPrice;
		this.quantity = quantity;
	}

	
	/**
	 * Calculates and returns the total price of the sale order line.
	 *
	 * @return the total price (quantity * unit price).
	 */
	public double getSaleOrderLineTotalPrice()
	{
		return quantity * unitSoldAtPrice;
	}
}