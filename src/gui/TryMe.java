package gui;


import java.time.LocalDate;

import model.Customer;
import model.Employee;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;


public class TryMe
{
    public static void main(String[] args)
    {
    	// Create Products
        Product product1 = new Product(1, "Cowboyhat - Sandfarvet", 200.00, 250.00, "USA", 20);
        Product product2 = new Product(2, "Cowboyhat - Krokodilleskind", 225.00, 251.00, "USA", 20);
        Product product3 = new Product(3, "Cowboyhat - Mørk", 120.00, 200.00, "USA", 20);

        Product product4 = new Product(4, "Cowboyhat - Enhjørningskind", 225.00, 1500.00, "USA", 20);

        Product product5 = new Product(5, "Realistisk Revolver - 50 caliber", 50.00, 750.00, "Ungarn", 15);
        Product product6 = new Product(6, "Cowboystøvler - Brunt Læder", 90.00, 251.00, "Polen", 30);

        Product product7 = new Product(7, "Cowboystøvler - Sort Læder", 90.00, 500.00, "Polen", 30);
        Product product8 = new Product(8, "Hælsporre", 40.00, 200.00, "Ungarn", 30);
        Product product9 = new Product(9, "Buffaloslips", 60.00, 100.00, "USA", 40);
    	
    	
    	
    	
   	
    	//----------------------------------//
        // Club member purchases for 1501	//
        //----------------------------------//        
         	
    	// Create Employees
    	Employee employee1 = new Employee(1000, "Jens", "Hansen", "Manager", "12345678", "jens@example.com", "Danmark", "Hovedstaden", "København", 1000, "Strøget", 10, 2, "A");

    	// Create Customers
    	Customer customer1 = new Customer(101, "Mikkel", "Poulsen", "Regular", "22334455", "mikkel@example.com", true, "Danmark", "Hovedstaden", "Frederiksberg", 2000, "Gammel Kongevej", 15, 1, "1A");

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

        // Finalizes the sale order
        saleOrder1.finalizeSaleOrder();

        
        


    	//----------------------------------//
        // Club member purchases for 1500	//
        //----------------------------------//  
       
        // *** Customer is a club member and purchases for exactly 1500
    	
        // Create Employee
        Employee employee2 = new Employee(1001, "Marie", "Nielsen", "Ekspedient", "87654321", "marie@example.com", "Danmark", "Midtjylland", "Aarhus", 8000, "Åboulevarden", 20, 3, "B");
     	
        // Create Customer
        Customer customer2 = new Customer(102, "Sofie", "Jørgensen", "VIP", "33445566", "sofie@example.com", true, "Danmark", "Nordjylland", "Aalborg", 9000, "Boulevarden", 25, 2, "2B");
        
        // Create Sale Order with a selected products
        SaleOrder saleOrder2 = new SaleOrder(customer2, employee2, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine4 = new SaleOrderLine(1, product4, product4.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder2.addSaleOrderLine(saleOrderLine4);

        // Finalizes the sale order
        saleOrder2.finalizeSaleOrder();

        


    	//--------------------------------------//
        // Private customer purchases for 2501	//
        //--------------------------------------// 
        
    	// Customer is a private customer and purchases for just above 2500 (2501)
        
        // Create Employee
    	Employee employee3 = new Employee(1002, "Anders", "Larsen", "Ekspedient", "11223344", "anders@example.com", "Danmark", "Syddanmark", "Odense", 5000, "Vestergade", 5, 1, "C");
     	
        // Create Customer
    	Customer customer3 = new Customer(103, "Rasmus", "Madsen", "Business", "44556677", "rasmus@example.com", false, "Danmark", "Sjælland", "Roskilde", 4000, "Algade", 30, 4, "3C");
        
        // Create Sale Order with a selected products
        SaleOrder saleOrder3 = new SaleOrder(customer3, employee3, false, LocalDate.now(), LocalDate.now(), false, LocalDate.now());

        // Add SaleOrderLines
        SaleOrderLine saleOrderLine5 = new SaleOrderLine(3, product5, product5.getSalesPrice());
        SaleOrderLine saleOrderLine6 = new SaleOrderLine(1, product6, product6.getSalesPrice());
       
        // Adds sale order lines to the SaleOrder
        saleOrder3.addSaleOrderLine(saleOrderLine5);
        saleOrder3.addSaleOrderLine(saleOrderLine6);

        // Finalizes the sale order
        saleOrder3.finalizeSaleOrder();



        
        
    	//--------------------------------------//
        // Private customer purchases for 2500	//
        //--------------------------------------// 
        
    	// Customer is a private customer and purchases for just above 2500 (2501)
        
        // Create Employee
    	Employee employee4 = new Employee(1002, "Anders", "Larsen", "Ekspedient", "11223344", "anders@example.com", "Danmark", "Syddanmark", "Odense", 5000, "Vestergade", 5, 1, "C");
     	
        // Create Customer
    	Customer customer4 = new Customer(103, "Rasmus", "Madsen", "Business", "44556677", "rasmus@example.com", false, "Danmark", "Sjælland", "Roskilde", 4000, "Algade", 30, 4, "3C");
        
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
    }
}