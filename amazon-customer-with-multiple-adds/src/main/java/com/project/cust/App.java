package com.project.cust;

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
        
        aService.addNewAddress(2);
        
       // aService.saveCustomer();
        
        
        
        
        
        
        
    }
}
