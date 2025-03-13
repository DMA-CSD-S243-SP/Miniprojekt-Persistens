package model;


/**
 * This class represents a product with details such as ID, name, prices,
 * country of origin, and minimum stock.
 * 
 * @author Line Bertelsen & Christoffer Søndergaard
 * @date 13.03.2025 - 10:35
 */
public class Product
{
	private int productId;
	private String name;
	private double purchasePrice;
	private double salesPrice;
	private String countryOfOrigin;
	private int minStock;

	
	/**
	 * Constructs a new Product with the specified attributes.
	 *
	 * @param productId       the unique identifier for the product
	 * @param name            the name of the product
	 * @param purchasePrice   the purchase price of the product
	 * @param salesPrice      the sales price of the product
	 * @param countryOfOrigin the country where the product originates
	 * @param minStock        the minimum stock level required
	 */
	public Product(int productId, String name, double purchasePrice, double salesPrice, String countryOfOrigin,
			int minStock)
	{
		this.productId = productId;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.countryOfOrigin = countryOfOrigin;
		this.minStock = minStock;
	}

	
	/**
	 * Gets the product ID.
	 *
	 * @return the product ID
	 */
	public int getProductId()
	{
		return productId;
	}

	
	/**
	 * Sets the product ID.
	 *
	 * @param productId the new product ID
	 */
	private void setProductId(int productId)
	{
		this.productId = productId;
	}

	
	/**
	 * Gets the name of the product.
	 *
	 * @return the product name
	 */
	public String getName()
	{
		return name;
	}

	
	/**
	 * Sets the name of the product.
	 *
	 * @param name the new product name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	
	/**
	 * Gets the purchase price of the product.
	 *
	 * @return the purchase price
	 */
	public double getPurchasePrice()
	{
		return purchasePrice;
	}

	
	/**
	 * Sets the purchase price of the product.
	 *
	 * @param purchasePrice the new purchase price
	 */
	public void setPurchasePrice(double purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}

	
	/**
	 * Gets the sales price of the product.
	 *
	 * @return the sales price
	 */
	public double getSalesPrice()
	{
		return salesPrice;
	}

	
	/**
	 * Sets the sales price of the product.
	 *
	 * @param salesPrice the new sales price
	 */
	public void setSalesPrice(double salesPrice)
	{
		this.salesPrice = salesPrice;
	}

	
	/**
	 * Gets the country of origin of the product.
	 *
	 * @return the country of origin
	 */
	public String getCountryOfOrigin()
	{
		return countryOfOrigin;
	}

	
	/**
	 * Sets the country of origin of the product.
	 *
	 * @param countryOfOrigin the new country of origin
	 */
	public void setCountryOfOrigin(String countryOfOrigin)
	{
		this.countryOfOrigin = countryOfOrigin;
	}

	
	/**
	 * Gets the minimum stock level.
	 *
	 * @return the minimum stock level
	 */
	public int getMinStock()
	{
		return minStock;
	}

	
	/**
	 * Sets the minimum stock level.
	 *
	 * @param minStock the new minimum stock level
	 */
	public void setMinStock(int minStock)
	{
		this.minStock = minStock;
	}

	
	/**
	 * Returns a string representation of the product.
	 *
	 * @return a string containing product details
	 */
	@Override
	public String toString()
	{
		return "Product [productId=" + productId + ", name=" + name + '\'' + ", purchasePrice=" + purchasePrice
				+ ", salesPrice=" + salesPrice + ", countryOfOrigin=" + countryOfOrigin + '\'' + ", minStock="
				+ minStock + "]";
	}
}
