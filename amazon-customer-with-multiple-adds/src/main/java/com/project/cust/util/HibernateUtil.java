package com.project.cust.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.project.cust.entities.Address;
import com.project.cust.entities.Customer;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static {
		try {
			
			if(sessionFactory==null) {
				
			sessionFactory =	new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class).buildSessionFactory();
				
			}
			
		}catch (Exception e) {
			
			throw new RuntimeException("Error in creating session factory"+e.getMessage());
			 
		}
	}
	
	public static SessionFactory getsessionFactory() {
		return sessionFactory;
	}

}
