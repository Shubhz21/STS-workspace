package com.project.cust;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.cust.entities.Customer;
import com.project.cust.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private AmazonService amazonService;
	
    public static void main(String[] args )
    {
        System.out.println( "Project is running.." );
        
        
        AmazonService aService = new AmazonService();
        
     //   aService.addNewAddress(3);
        
      //  aService.saveCustomer();
        
        
        int operation=0, id=0;
        
        boolean val=true;
        
        while(val) {
        	System.out.println("\nselect opearation:\n 1 - add new customer \n 2 - add new address to existing customers: \n 3 - for fetch all customers \n 4 - for delete customers: \n 5 - for update customer data: \n");
        	
        	Scanner s = new Scanner(System.in);
        	
        	operation = s.nextInt();
        	
        switch (operation) {
        
		case 1: aService.addNewCustomer();
			
			break;
			
		case 2: 
			System.out.println("pls insert custId: ");
			id=s.nextInt();
			aService.addNewAddress(id);

			break;
			
		case 3: aService.getAllCustomers();
			
			break;
			
		case 4: System.out.println("pls insert custId: ");
		     id=s.nextInt();
			aService.deleteCustomer(id);
		
		break;
		
		case 5: System.out.println("enter customerId that you want to update: ");
		
		id=s.nextInt();
		aService.updateCustomer(id);
		
		break;
		
		default:
			
			val= false;
			
			System.out.println("no valid operation found...shutdown..");
		}
        
        }
        
        
        
        
        
        
        
    }
}
