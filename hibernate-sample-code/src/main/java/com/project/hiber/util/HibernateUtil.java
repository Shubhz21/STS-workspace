package com.project.hiber.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.project.hiber.entities.Certificate;
import com.project.hiber.entities.Student;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static {
		try {
			
			if(sessionFactory==null) {
				
			sessionFactory =	new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Certificate.class).addAnnotatedClass(Student.class).buildSessionFactory();
				
			}
			
		}catch (Exception e) {
			
			throw new RuntimeException("Error in creating session factory"+e.getMessage());
			 
		}
	}
	
	public static SessionFactory getsessionFactory() {
		return sessionFactory;
	}

}
