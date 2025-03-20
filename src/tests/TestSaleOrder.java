package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import model.Customer;
import model.Employee;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;


/**
 * JUnit test class for testing SaleOrder's getTotalOrderPrice method.
 */
public class TestSaleOrder 
{
    private Product product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12;
    private Employee employee1, employee2, employee3, employee4;
    private Customer customer1, customer2, customer3, customer4;
	

    /**
     * Sets up test data before each test case runs.
     */
    @BeforeEach
    public void setUp()
    {
        // Create Products
        product1 = new Product(1, "Cowboyhat - Sandfarvet", 200.00, 250.00, "USA", 20);
        product2 = new Product(2, "Cowboyhat - Krokodilleskind", 225.00, 250.01, "USA", 20);
        product3 = new Product(3, "Cowboyhat - Mørk", 120.00, 200.00, "USA", 20);
        product4 = new Product(4, "Cowboyhat - Enhjørningskind", 225.00, 1500.00, "USA", 20);
        product5 = new Product(5, "Realistisk Revolver - 50 caliber", 50.00, 750.00, "Ungarn", 15);
        product6 = new Product(6, "Cowboystøvler - Brunt Læder", 90.00, 250.01, "Polen", 30);
   		product7 = new Product(7, "Cowboystøvler - Sort Læder", 90.00, 500.00, "Polen", 30);
		product8 = new Product(8, "Hælsporre", 40.00, 200.00, "Ungarn", 30);
		product9 = new Product(9, "Buffaloslips", 60.00, 100.00, "USA", 40);
		product10 = new Product(10, "Cowboy Lommeuld", 0.00, 0.01, "USA", 5);
		product11 = new Product(11, "Sterling Silver Cowboy MAGA Hat", 4200.00, 5000.00, "USA", 5);
		product12 = new Product(12, "Forgyldt 24Carat Cowboy MAGA Hat", 7000.00, 10000.00, "USA", 3);
		
		
        // Create Employees
        employee1 = new Employee(1000, "Jens", "Hansen", "Manager", "12345678", "jens@example.com", "Danmark", "Hovedstaden", "København", 1000, "Strøget", 10, 2, "A");
        employee2 = new Employee(1001, "Marie", "Nielsen", "Ekspedient", "87654321", "marie@example.com", "Danmark", "Midtjylland", "Aarhus", 8000, "Åboulevarden", 20, 3, "B");
        employee3 = new Employee(1002, "Anders", "Larsen", "Ekspedient", "11223344", "anders@example.com", "Danmark", "Syddanmark", "Odense", 5000, "Vestergade", 5, 1, "C");
    	employee4 = new Employee(1002, "Anders", "Larsen", "Ekspedient", "11223344", "anders@example.com", "Danmark", "Syddanmark", "Odense", 5000, "Vestergade", 5, 1, "C");

        
        // Create Customers
        customer1 = new Customer(101, "Mikkel", "Poulsen", "22334455", "mikkel@example.com", true, "Danmark", "Hovedstaden", "Frederiksberg", 2000, "Gammel Kongevej", 15, 1, "1A");
        customer2 = new Customer(102, "Sofie", "Jørgensen", "33445566", "sofie@example.com", true, "Danmark", "Nordjylland", "Aalborg", 9000, "Boulevarden", 25, 2, "2B");
        customer3 = new Customer(103, "Rasmus", "Madsen", "44556677", "rasmus@example.com", false, "Danmark", "Sjælland", "Roskilde", 4000, "Algade", 30, 4, "3C");
        customer4 = new Customer(103, "Andreas", "Jensen", "44556677", "rasmus@example.com", false, "Danmark", "Sjælland", "Roskilde", 4000, "Algade", 30, 4, "3C");
    }
    
    
    /**
     * Tests if the customer is a club member does not receive the 10% discount
     * when purchasing goods for exactly 0.01
     */
    @Test
    public void testGetTotalOrderPriceForClubMembersLowOrder()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder1 = new SaleOrder(customer2, employee2, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine1 = new SaleOrderLine(1, product10, product10.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder1.addSaleOrderLine(saleOrderLine1);

        // Assert total price should be 0.01
        assertEquals(0.01, saleOrder1.getTotalOrderPrice());
    }
    
    
    /**
     * Tests if the customer is a club member and receives the 10% discount
     * when purchasing goods for over 1500 (1500.01)
     */
    @Test
    public void testGetTotalOrderPriceForClubMembersWithOrderPerks()
    {
        // Create Sale Order with 3 selected products
        SaleOrder saleOrder1 = new SaleOrder(customer1, employee1, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine1 = new SaleOrderLine(1, product1, product1.getSalesPrice());
        SaleOrderLine saleOrderLine2 = new SaleOrderLine(1, product2, product2.getSalesPrice());
        SaleOrderLine saleOrderLine3 = new SaleOrderLine(5, product3, product3.getSalesPrice());
        
        // Adds sale order lines to the SaleOrder
        saleOrder1.addSaleOrderLine(saleOrderLine1);
        saleOrder1.addSaleOrderLine(saleOrderLine2);
        saleOrder1.addSaleOrderLine(saleOrderLine3);

        // Assert total price should be 1350,009
        assertEquals(1350.009, saleOrder1.getTotalOrderPrice());
    }

    
    /**
     * Tests if the customer is a club member does not receive the 10% discount
     * when purchasing goods for exactly 1500
     */
    @Test
    public void testGetTotalOrderPriceForClubMembersWithoutOrderPerks()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder2 = new SaleOrder(customer2, employee2, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine4 = new SaleOrderLine(1, product4, product4.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder2.addSaleOrderLine(saleOrderLine4);

        // Assert total price should be 1500
        assertEquals(1500.00, saleOrder2.getTotalOrderPrice());
    }
    
    
    /**
     * Tests if the customer is a club member does not receive the 10% discount
     * when purchasing goods for exactly 5000
     */
    @Test
    public void testGetTotalOrderPriceForClubMembersHighOrder()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder1 = new SaleOrder(customer2, employee2, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine1 = new SaleOrderLine(1, product11, product11.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder1.addSaleOrderLine(saleOrderLine1);

        // Assert total price should be 4500
        assertEquals(4500.00, saleOrder1.getTotalOrderPrice());
    }
    
    
    /**
     * Tests if the customer is a private customer and does not receive the free freight
     * order perk when purchasing goods for exactly 0.01 (45.01)
     */
    @Test
    public void testGetTotalOrderPriceForPrivateCustomersLowOrder()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder1 = new SaleOrder(customer4, employee1, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine1 = new SaleOrderLine(1, product10, product10.getSalesPrice());

        // Adds sale order lines to the SaleOrder
        saleOrder1.addSaleOrderLine(saleOrderLine1);

        // Finalizes the sale order
        saleOrder1.finalizeSaleOrder();
        
        // Assert total price should be 45.01
        assertEquals(45.01, saleOrder1.getTotalOrderPrice());
    }
    
    

    /**
     * Tests if the customer is a private customer and does receive the free freight
     * order perk when purchasing goods for over 2500 (2500.01)
     */
    @Test
    public void testGetTotalOrderPriceForPrivateCustomersWithOrderPerks()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder3 = new SaleOrder(customer3, employee3, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine5 = new SaleOrderLine(3, product5, product5.getSalesPrice());
        SaleOrderLine saleOrderLine6 = new SaleOrderLine(1, product6, product6.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder3.addSaleOrderLine(saleOrderLine5);
        saleOrder3.addSaleOrderLine(saleOrderLine6);
        
        // Assert total price should be 2500.01
        assertEquals(2500.01, saleOrder3.getTotalOrderPrice());
    }
    
    
    /**
     * Tests if the customer is a private customer and does not receive the free freight
     * order perk when purchasing goods for exactly 2500 (2500)
     */
    @Test
    public void testGetTotalOrderPriceForPrivateCustomersWithoutOrderPerks()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder4 = new SaleOrder(customer4, employee4, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine7 = new SaleOrderLine(4, product7, product7.getSalesPrice());
        SaleOrderLine saleOrderLine8 = new SaleOrderLine(2, product8, product8.getSalesPrice());
        SaleOrderLine saleOrderLine9 = new SaleOrderLine(1, product9, product9.getSalesPrice());

        // Adds sale order lines to the SaleOrder
        saleOrder4.addSaleOrderLine(saleOrderLine7);
        saleOrder4.addSaleOrderLine(saleOrderLine8);
        saleOrder4.addSaleOrderLine(saleOrderLine9);

        // Finalizes the sale order
        saleOrder4.finalizeSaleOrder();
        
        // Assert total price should be 2500
        assertEquals(2500.0, saleOrder4.getTotalOrderPrice());
    }
    
    
    
    /**
     * Tests if the customer is a private customer and does not receive the free freight
     * order perk when purchasing goods for exactly 10000.00 (10000.00)
     */
    @Test
    public void testGetTotalOrderPriceForPrivateCustomersHighOrder()
    {
        // Create Sale Order with a selected products
        SaleOrder saleOrder1 = new SaleOrder(customer4, employee1, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine1 = new SaleOrderLine(1, product12, product12.getSalesPrice());

        // Adds sale order lines to the SaleOrder
        saleOrder1.addSaleOrderLine(saleOrderLine1);

        // Finalizes the sale order
        saleOrder1.finalizeSaleOrder();
        
        // Assert total price should be 10000.00
        assertEquals(10000.00, saleOrder1.getTotalOrderPrice());
    }
}
