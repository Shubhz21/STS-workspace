package com.project.cust;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.cust.entities.Address;
import com.project.cust.entities.Customer;
import com.project.cust.util.HibernateUtil;


public class AmazonService {
	
	static Transaction transaction = null;
	
	static SessionFactory sessionFactory = HibernateUtil.getsessionFactory();
	
	
	public void addNewAddress(long customerId) {
    
    
    
    
    
    
  //  SessionFactory sessionFactory = HibernateUtil.getsessionFactory();
    
   
    
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
    	
Scanner s = new Scanner(System.in);
		
		System.out.println("enter addresses of customer: address,pincode:  \n");
		
	
	       String address = s.next();
	       String pincode = s.next();
	       
    	
    
        ssn.createNativeQuery("INSERT INTO address (address, pincode, customerId) VALUES (:addr, :pin, :cid)")
               .setParameter("addr", address)
               .setParameter("pin", pincode)
               .setParameter("cid", customerId)
               .executeUpdate();

       
//        ssn.createNativeQuery("INSERT INTO address (address, pincode, customerId) VALUES (:addr, :pin, :cid)")
//               .setParameter("addr", "Dubai, UAE")
//               .setParameter("pin", "5252")
//               .setParameter("cid", customerId)
//               .executeUpdate();
    	
    
    
    
    
    
    
//    a1.setCustomer(c1);
//    a2.setCustomer(c1);
//    
//    c1.getAddress().add(a2);
//    c1.getAddress().add(a1);
//    
//    ssn.persist(a1);
//    ssn.persist(a2);
    
    transaction.commit();
    
    System.out.println("Address updated and assigned to customerId: "+customerId);
    
    }catch (Exception e) {
 	   
    	
    	
    	
    	
    	
 	   if(transaction!=null) {
 		   transaction.rollback();
 	   }
 	   else {
		e.printStackTrace();
 	   }
	}
	
	}
	
	
	public void addNewCustomer( ) {
		
		
		Customer customer = new Customer();
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("enter all details of customer: name,mail,phone. \n");
		
	
	       String name = s.next();
	       String mail = s.next();
	       String  phone = s.next();
	       
	       
	       
		
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
	
	
	public void getAllCustomers()  {
		
		
		
		
		
		try(Session ssn = sessionFactory.openSession()) {
			
			transaction = ssn.beginTransaction();
			
			List<Customer> c = ssn.createNativeQuery("select * from customer",Customer.class).getResultList();
			
			
			System.out.println(" Id  |  customerName  |  phone  |  mail  |");
			
			for(Customer cust : c) {
				
				System.out.println(cust.getCustomerId()+" | "+cust.getCustomerName()+" | "+cust.getPhone()+" | "+cust.geteMail()+" | ");
				
			}
			
			transaction.commit();
			
			Thread t = new Thread();
			
			t.sleep(3000);
			
		}catch (Exception e) {
			
			if(transaction!= null) {
				
				transaction.rollback();
				
			}
			else {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	
	public void deleteCustomer(long customerId) {
		
        
		
		
		
		try (Session ssn = sessionFactory.openSession()){
			
			transaction = ssn.beginTransaction();
			
			Query addQry = ssn.createNativeQuery("delete from address where customerId = :custId");
			addQry.setParameter("custId", customerId);
			
			int affctdRows = addQry.executeUpdate();
			
			Query custQry = ssn.createNativeQuery("delete from customer where customerId = :id");
			
			custQry.setParameter("id", customerId);
			
			int totalRowsAffected = custQry.executeUpdate();
			
			System.out.println("Total customers and associated addresses deleted: customers: "+totalRowsAffected+" addresses: "+affctdRows+"\n\n");
			
			List<Customer> cust = ssn.createNativeQuery("select * from customer",Customer.class).getResultList();
			
			for(Customer customers : cust) {
				
				System.out.println(customers.getCustomerId()+" | "+customers.getCustomerName()+" | "+customers.getPhone()+" | "+customers.geteMail()+" | ");

			}
			
			transaction.commit();
			
			Thread t = new Thread();
			
			t.sleep(3000);
			
			
		}catch (Exception e) {
			
			if(transaction != null) {
				transaction.rollback();
			}
			else {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void updateCustomer(long customerId) {
		
		
		
		
		
		try (Session ssn = sessionFactory.openSession()){
			
			transaction = ssn.beginTransaction();
			
			//Customer cust = ssn.get(Customer.class, customerId);
			
			Scanner s = new Scanner(System.in);
			
			System.out.println("please enter values name and email: \n");
			
			String name = s.nextLine();
			String eml = s.nextLine();
			
			
			
			Query qry = ssn.createNativeQuery("update customer set customerName = :custName , eMail = :email where customerId = :custId");
					qry.setParameter("custName", name);
					qry.setParameter("email", eml);
					qry.setParameter("custId", customerId);
					
                    int afftdRows = qry.executeUpdate();
					
					System.out.println("total customers updated: "+afftdRows);
					
					transaction.commit();
			
		}catch (Exception e) {
			
			if(transaction!=null) {
				transaction.rollback();
			}else {
				e.printStackTrace();
			}
			
			
			
		}
		
	}
	
	public void getCustById(long customerId) {
		
		try (Session ssn = sessionFactory.openSession()){
			
			transaction=ssn.beginTransaction();
			
             Customer c = (Customer) ssn.createNativeQuery("select * from customer where customerId = :custId",Customer.class)
            		 .setParameter("custId", customerId)
            		 .getSingleResult();
			
			
			
			
				
				System.out.println(c.getCustomerId()+" | "+c.getCustomerName()+" | "+c.getPhone()+" | "+c.geteMail()+" | ");
				
			
			
			
			
			transaction.commit();
			
			
			
			
			
			
			
		}catch (Exception e) {
			
			if(transaction!=null) {
				
				transaction.rollback();
				
			}else {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	public void getAllAddressesByCustId(long customerId) {
		
		SessionFactory ssnFctry = HibernateUtil.getsessionFactory();
		
		try (Session ssn = ssnFctry.openSession()){
			
			transaction = ssn.beginTransaction();
			
			List<Address> qry = ssn.createNativeQuery("select * from address where customerId = :custId",Address.class)
					.setParameter("custId",customerId)
					.getResultList();
			
			
			for(Address ads : qry) {
				System.out.println(ads.getAddressId()+" | "+ads.getAddress()+" | "+ads.getCustomerId()+" | "+ads.getPincode());
			}
			
			transaction.commit();
			
		}catch (Exception e) {
			
			if(transaction!=null){
				transaction.rollback();
			}
			else {
				e.printStackTrace();
			}
		}
		 
		
	}
	
	
	
	// sample methods for checking HQL----->
	
	public void getAll() {
		
		Session ssn = sessionFactory.openSession();
		
		
		String hql = "from Customer where customerId=2";
		
		Query<Customer> qry = ssn.createQuery(hql); 
		
		Customer cust = qry.getSingleResult();
		
		//for(Customer customer : cust) {
			
			System.out.println(cust.getCustomerId()+" | "+cust.getCustomerName()+" | "+cust.getPhone()+" | "+cust.geteMail()+" | ");
			
		//}
		
	}
	
	public void updateSingleData() {
		
		Session ssn = sessionFactory.openSession();
		
		transaction = ssn.beginTransaction();
		
		
		
		String hql = "update Customer set customerName= 'shubham_phunde' where customerId = 2 ";
		
		Query<Customer> c = ssn.createQuery(hql);
		
		int  effect = c.executeUpdate();
		
		
		
		transaction.commit();
		
		System.out.println("affected rows: "+effect);
		
	}
	
	
	

}
