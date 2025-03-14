package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a sale order, containing details about the customer, employee,
 * order status, and the list of sale order lines.
 * 
 * @author Christoffer Søndergaard
 * @date 13.03.2025 - 14:04
 */
public class SaleOrder
{
    private Customer customer;
    private Employee employee;
    private static int orderId = 10000;
    private static int invoiceNumber = 1000000;
    private boolean invoiceStatus;
    private LocalDate paymentDate;
    private LocalDate createdDate;
    private boolean deliveryStatus;
    private LocalDate deliveryDate;
    private double totalOrderPrice;
    private List<SaleOrderLine> saleOrderLineList;

    // The freightcharge is set at a fixed value of 45.00 
    private final double freightCharge = 45.00;
    
    // Due to missing information about club member discounts, it has been set to a fixed value of 10.00% for all club members
    private final double discountPercentage = 10.00;
    
    
    /**
     * Constructs a new SaleOrder with default values and initializes the sale order line list.
     */
    public SaleOrder(Customer customer, Employee employee, boolean invoiceStatus, LocalDate paymentDate, LocalDate createdDate, boolean deliveryStatus, LocalDate deliveryDate)
    {
    	this.customer = customer;
    	this.employee = employee;
    	this.invoiceStatus = invoiceStatus;
    	this.createdDate = createdDate;
    	this.deliveryStatus = deliveryStatus;
    	this.deliveryDate = deliveryDate;
    	this.paymentDate = paymentDate;
    	this.saleOrderLineList = new ArrayList<>();

    	// Adds +1 to the value of orderID
    	setOrderId(orderId);
    	
    	// Adds +1 to the value of invoiceNumber
    	setInvoiceNumber(invoiceNumber);
    }

    
    /**
     * Retrieves the customer associated with the sale order.
     *
     * @return the customer.
     */
    public Customer getCustomer()
    {
        return customer;
    }

    
    /**
     * Sets the customer for the sale order.
     *
     * @param customer the customer to be set.
     */
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    
    /**
     * Retrieves the employee handling the sale order.
     *
     * @return the employee.
     */
    public Employee getEmployee()
    {
        return employee;
    }

    
    /**
     * Sets the employee responsible for the sale order.
     *
     * @param employee the employee to be set.
     */
    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    
    /**
     * Retrieves the unique order ID.
     *
     * @return the order ID.
     */
    public int getOrderId()
    {
        return orderId;
    }

    
    /**
     * Sets the order ID, by adding + 1 to counter of orderIDs.
     *
     * @param orderId the order ID to be set.
     */
    private void setOrderId(int orderId)
    {
    	// Adds +1 to the current value of the static counter for orderId
        SaleOrder.orderId = orderId + 1;
    }

    
    /**
     * Retrieves the invoice number for the sale order.
     *
     * @return the invoice number.
     */
    public int getInvoiceNumber()
    {
        return invoiceNumber;
    }

    
    /**
     * Sets the invoice number, by adding + 1 to counter of invoiceNumbers.
     *
     * @param invoiceNumber the invoice number to be set.
     */
    private void setInvoiceNumber(int invoiceNumber)
    {
    	// Adds +1 to the current value of the static counter for invoiceNumber
        SaleOrder.invoiceNumber = invoiceNumber + 1;
    }

    
    /**
     * Retrieves the invoice status.
     *
     * @return true if the invoice is paid, otherwise false.
     */
    public boolean getInvoiceStatus()
    {
        return invoiceStatus;
    }

    
    /**
     * Sets the invoice status.
     *
     * @param invoiceStatus true if the invoice is paid, otherwise false.
     */
    public void setInvoiceStatus(boolean invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    
    /**
     * Retrieves the payment date, by retrieving the createdDate and adding 30 days on top.
     *
     * @return the payment date.
     */
    public LocalDate getPaymentDate()
    {
        return this.createdDate.plusDays(30);
    }

    
    /**
     * Sets the payment date.
     *
     * @param paymentDate the payment date to be set.
     */
    public void setPaymentDate(LocalDate paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    
    /**
     * Retrieves the creation date of the sale order.
     *
     * @return the created date.
     */
    public LocalDate getCreatedDate()
    {
        return createdDate;
    }


    /**
     * Sets the created date of the sale order.
     *
     * @param createdDate the created date to be set.
     */
    public void setCreatedDate(LocalDate createdDate)
    {
        this.createdDate = createdDate;
    }

    
    /**
     * Retrieves the delivery date, by retrieving the createdDate and adding 7 days to it.
     *
     * @return the payment date.
     */
    public LocalDate getDeliveryDate()
    {
        return this.createdDate.plusDays(7);
    }

    
    /**
     * Sets the payment date.
     *
     * @param paymentDate the payment date to be set.
     */
    public void setDeliveryDate(LocalDate deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }
    
    
    /**
     * Retrieves the delivery status of the sale order.
     *
     * @return true if the order is delivered, otherwise false.
     */
    public boolean isDeliveryStatus()
    {
        return deliveryStatus;
    }

    
    /**
     * Sets the delivery status.
     *
     * @param deliveryStatus true if the order is delivered, otherwise false.
     */
    public void setDeliveryStatus(boolean deliveryStatus)
    {
        this.deliveryStatus = deliveryStatus;
    }

    
    /**
     * Adds a SaleOrderLine to the order.
     *
     * @param saleOrderLine the sale order line to be added.
     */
    public void addSaleOrderLine(SaleOrderLine saleOrderLine)
    {
        this.saleOrderLineList.add(saleOrderLine);
    }

    
    /**
     * Retrieves the total order price by summing all sale order lines, and then
     * providing private customers with free freight charges (-45.00 subtraction) on
     * orders above 2500.00, and providing club members with 10% discount on orders
     * above 1500.00
     *
     * @return the total order price after potential freight charge subtractions and club member discounts.
     */
    public double getTotalOrderPrice()
    {
    	// Sets the totalOrderPrice for the sale order to 0
        totalOrderPrice = 0;
        
        // Iterates / loops through all of the saleOrderLines
        for (SaleOrderLine saleOrderLine : saleOrderLineList)
        {
        	// Adds the total price of a saleOrderLine to the totalOrderPrice variable
            totalOrderPrice += saleOrderLine.getSaleOrderLineTotalPrice();
        }
        
        // If the customer is a club member then execute this section
        if(customer.isClubMember() == true)
        {
        	// If the value of the totalOrderPrice is larger than the customer's required purchase threshold then execute this section
        	if(totalOrderPrice > customer.getPurchaseThreshhold())
        	{
        		// Subtracts 10% of the sale order's total price
        		totalOrderPrice = applyClubMemberDiscount(totalOrderPrice);
        	}
        }
         
        // If the customer is not a club member then execute this section
        else
        {
        	// If the value of the totalOrderPrice is larger than the customer's required purchase threshold then execute this section
        	if(totalOrderPrice > customer.getPurchaseThreshhold())
        	{
        		// Subtracts the costs of the usual freight charges from the value of the totalOrderPrice variable
        		totalOrderPrice = applyFreeFreightCharge(totalOrderPrice);
        	}
        }
        
        // Returns the total order price, with possible freight charge subtractions or club member discount 
        return totalOrderPrice;
    }


    /**
     * Subtracts the cost of freight charges, if an order exceeds the purchase threshold
     * currently set to be 2500 for private customers, and 1500 for club members.
     * 
     * @param totalOrderPrice the total price of the order.
     * @return the adjusted freight charge.
     */
    public double applyFreeFreightCharge(double totalOrderPrice)
    {
		// Subtracts the 45.00 freight cost from the order's total price
		totalOrderPrice = totalOrderPrice - freightCharge;
    	
    	return totalOrderPrice;
    }

    
    /**
     * Applies a club member discount to the total sale order's price.
     *
     * @param totalOrderPrice the total price before discount.
     * @return the discounted total price.
     */
    public double applyClubMemberDiscount(double totalOrderPrice)
    {
    	// Subtracts the discountPercentage (10%) from the totalOrderPrice
    	totalOrderPrice *= (1 - (discountPercentage / 100));
    	
        return totalOrderPrice;
    }

    
    /**
     * Finalizes the sale order by calculating the total price and printing the invoice.
     */
    public void finalizeSaleOrder()
    {
        totalOrderPrice = getTotalOrderPrice();
   
        printSaleOrder();
        printInvoice();
    }

    
    public void printSaleOrder()
    {
    	// Used to calculate the subTotal price of all the products
    	double subTotal = 0.00;
    
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	
    	System.out.println("Ordreoplysninger ");
    	System.out.println("--------------------------------");
    	System.out.println("");
    	System.out.println("Ordrenummer: " + orderId);
    	System.out.println("Ordre oprettelsesdato: " + createdDate);
    	System.out.println("Leveringsdato: " + deliveryDate);
    	System.out.println("Betalingsdato: " + paymentDate);
    	System.out.println("Betjent af: " + employee.getFirstName() + " " + employee.getLastName());
    	System.out.println("Medarbejdernummer: " + employee.getEmployeeId());
    	System.out.println("");
    	System.out.println("");
    	
    	
    	
    	System.out.println("Kundeoplysninger ");
    	System.out.println("--------------------------------");
    	System.out.println("");
    	System.out.println("Kundenavn: " + getCustomer().getFirstName() + " " + getCustomer().getLastName());
    	System.out.println("Kundenummer: " + this.getCustomer().getCustomerNumber());
    	System.out.println("Telefonnummer: " + this.getCustomer().getPhoneNumber());
    	System.out.println("E-mail: " + this.getCustomer().getEmailAddress());
    	System.out.println("Leveringsadresse: " + getCustomer().getCountry() + " " + getCustomer().getState() + " " + getCustomer().getCity() + " " + getCustomer().getPostalCode() + " " + getCustomer().getStreetName() + " "	+ getCustomer().getHouseNumber() + " " + getCustomer().getFloorNumber() + " " + getCustomer().getDoorNumber() + " ");
    	System.out.println("");
    	System.out.println("");
    	
    	
    	
    	System.out.println("Produkter ");
    	System.out.println("--------------------------------");
    	System.out.println("");

        // Iterates / loops through all of the saleOrderLines
        for (SaleOrderLine saleOrderLine : saleOrderLineList)
        {
        	// Adds the value of the saleOrderLine's total price to the subTotal
        	subTotal = subTotal + saleOrderLine.getSaleOrderLineTotalPrice();

        	System.out.println(saleOrderLine.getQuantity() + "x " + "produktid: " +saleOrderLine.getProduct().getProductId() + " produktnavn: " + saleOrderLine.getProduct().getName() + " :    " + saleOrderLine.getProduct().getSalesPrice());
        }
        
    	System.out.println("");
    	System.out.println("");
    	
    	
    	
    	System.out.println("Økonomiske Detaljer ");
    	System.out.println("--------------------------------");
    	System.out.println("");
    	System.out.println("Subtotal: " + subTotal);
    	
        // If the customer is a club member then execute this section
        if(customer.isClubMember() == true)
        {
        	// If the value of the totalOrderPrice is larger than the customer's required purchase threshold then execute this section
        	if(totalOrderPrice > customer.getPurchaseThreshhold())
        	{
        		System.out.println("Rabat: " + discountPercentage + "%");
        	}
        }
         
        // If the customer is not a club member then execute this section
        else
        {
        	// If the value of the totalOrderPrice is larger than the customer's required purchase threshold then execute this section
        	if(totalOrderPrice > customer.getPurchaseThreshhold())
        	{
        		System.out.println("Fragtomkostninger: Gratis forsendelse");
        	}
        }

    	System.out.println("Totalpris: " + totalOrderPrice);
    	System.out.println("Betalingsstatus: " + invoiceStatus);
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    }
    
    
    
    /**
     * Prints the invoice details.
     */
    public void printInvoice()
    {
    	System.out.println("Faktura ");
    	System.out.println("--------------------------------");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("Fakturanummer: " + this.invoiceNumber);
    	System.out.println("Fakturadato: " + this.createdDate);
    	System.out.println("Forventet Leveringsdato: " + this.deliveryDate);
    	System.out.println("");
    	System.out.println("Virksomhedsnavn: Western Style Ltd.");
    	System.out.println("CVR-Nummer: 1234567890.");
    	System.out.println("Sct. Mathias Gade 1, 8800 Viborg");
    	System.out.println("");
    	System.out.println("Kundenavn: " + getCustomer().getFirstName() + " " + getCustomer().getLastName());
    	System.out.println("Kundeaddresse: " 
    	+ getCustomer().getCountry() + " "
    	+ getCustomer().getState() + " "
    	+ getCustomer().getCity() + " "
		+ getCustomer().getPostalCode() + " "
		+ getCustomer().getStreetName() + " "
		+ getCustomer().getHouseNumber() + " "	
		+ getCustomer().getFloorNumber() + " "	
    	+ getCustomer().getDoorNumber() + " ");
    	System.out.println("");
    	System.out.println("");
    	
    	
    	
    	System.out.println("Varer: ");
        
        // Iterates / loops through all of the saleOrderLines
        for (SaleOrderLine saleOrderLine : saleOrderLineList)
        {
        	System.out.println(saleOrderLine.getQuantity() + "x " + saleOrderLine.getProduct().getName() + " :    " + saleOrderLine.getProduct().getSalesPrice());
        }

        // If the customer is a club member then execute this section
        if(customer.isClubMember() == true)
        {
        	// If the value of the totalOrderPrice is larger than the customer's required purchase threshold then execute this section
        	if(totalOrderPrice > customer.getPurchaseThreshhold())
        	{
        		System.out.println("Rabat: " + discountPercentage + "%");
        	}
        }
         
        // If the customer is not a club member then execute this section
        else
        {
        	// If the value of the totalOrderPrice is larger than the customer's required purchase threshold then execute this section
        	if(totalOrderPrice > customer.getPurchaseThreshhold())
        	{
        		System.out.println("Gratis forsendelse: Ja");
        	}
        }
                
        System.out.println("Total pris: " + totalOrderPrice);
    }
}