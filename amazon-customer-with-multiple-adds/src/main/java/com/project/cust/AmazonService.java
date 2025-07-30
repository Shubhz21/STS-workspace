package com.project.cust;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.cust.entities.Address;
import com.project.cust.entities.Customer;
import com.project.cust.util.HibernateUtil;


public class AmazonService {
	
	
	public void addNewAddress(long customerId ) {
	
	
    
    Transaction transaction = null;
    
    
    
    
//    Address a1 = new Address();
//    Address a2 = new Address();
//    
//    a1.setAddress(" Bangalore city, secto-3");
//    a1.setPincode("8246");
//    
//    a2.setAddress("Mumbai, csmt ");
//    a2.setPincode("82467");
//    
    
    
    SessionFactory sessionFactory = HibernateUtil.getsessionFactory();
    
   // Session ssn = sessionFactory.openSession();
    
    try (Session ssn = sessionFactory.openSession()){
    	
    	transaction = ssn.beginTransaction();
    	
    	Customer c1 = ssn.get(Customer.class, customerId);
    	
    	c1  =  ssn.createNativeQuery("select * from customer where customerId = :cid",Customer.class)
    			.setParameter("cid", customerId)
    			.uniqueResult();
    	
    	if(c1 == null) {
    		System.out.println("Customer not found: "+customerId);
    		return;
    	}
    	
    	
    
        ssn.createNativeQuery("INSERT INTO address (address, pincode, customerId) VALUES (:addr, :pin, :cid)")
               .setParameter("addr", "Bangalore city, sector-3")
               .setParameter("pin", "8246")
               .setParameter("cid", customerId)
               .executeUpdate();

       
        ssn.createNativeQuery("INSERT INTO address (address, pincode, customerId) VALUES (:addr, :pin, :cid)")
               .setParameter("addr", "Mumbai, CSMT")
               .setParameter("pin", "82467")
               .setParameter("cid", customerId)
               .executeUpdate();
    	
    
    
    
    
    
    
//    a1.setCustomer(c1);
//    a2.setCustomer(c1);
//    
//    c1.getAddress().add(a2);
//    c1.getAddress().add(a1);
//    
//    ssn.persist(a1);
//    ssn.persist(a2);
    
    transaction.commit();
    
    System.out.println("Address updated..");
    
    }catch (Exception e) {
 	   
    	
    	
    	
    	
    	
 	   if(transaction!=null) {
 		   transaction.rollback();
 	   }
 	   else {
		e.printStackTrace();
 	   }
	}
	
	}
	
	
	public void saveCustomer( ) {
		
		
		Customer customer = new Customer();
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("enter all details of customer: name,mail,phone. ");
		
	
	       String name = s.next();
	       String mail = s.next();
	       String  phone = s.next();
	       
	       SessionFactory sessionFactory = HibernateUtil.getsessionFactory();
	       
		
		try(Session session = sessionFactory.openSession()) {
			
			Transaction beginTransaction = session.beginTransaction();
			
			customer.setCustomerName(name);
			customer.seteMail(mail);
			customer.setPhone(phone);
			
			
			
			session.persist(customer);
			
			beginTransaction.commit();
			
			System.out.println("updated data: "+" | "+customer.getCustomerName()+" | "+customer.getPhone()+" | "+customer.geteMail()+" | ");
			
		}catch (Exception e) {
			
			e.printStackTrace();
		
		}
		

		
	}

}
