package com.project.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.hiber.entities.Certificate;
import com.project.hiber.entities.Student;
import com.project.hiber.util.HibernateUtil;


public class App 
{
	
	private StudentService studentService;
	
    public static void main( String[] args )
    {
        System.out.println( "Project started" );
        
        
       SessionFactory sessionFactory =  HibernateUtil.getsessionFactory();
       
       System.out.println(sessionFactory);
       
       
       
       Student s2 = new Student();
//       
//       s2.setName("Rajiv");
//       s2.setPhone("1342234");
//       s2.setActive(true);
//       s2.setAbout("new student data");
//       s2.setCollege("MIT");
//       s2.setFatherName("unkwnn");
//       
//       
//       
//       
//       Certificate c1 = new Certificate();
//       
//       c1.setTitle("Hackathon_Competition");
//       c1.setLink("http://linkqwe.345");
//       c1.setAbout("Sponsored by company");
//       
//       c1.setStudent(s2);
//       
//       Certificate c2 = new Certificate();
//       
//       c2.setTitle("Python_Certification");
//       c2.setLink("http://linkqwe.345");
//       c2.setAbout("Sponsored by udemy");
//       c2.setLink("link");
//       
//       c2.setStudent(s2);
//       
//       s2.getCertificate().add(c1);
//       s2.getCertificate().add(c2);
//       
//       
//       
//       Session session = sessionFactory.openSession();
//       
       Transaction transaction=null;
//       
//       try {
//    	   
//    	   transaction = session.beginTransaction();
//    	
//    	   session.persist(s1);
//    	   session.persist(s2);
//    	 //  session.persist(c1);
//    	   
//    	   transaction.commit();
//    	   
//    	   System.out.println("Data saved...");
//    	   
//    	   
//    	   
//       }
//       
//       catch (Exception e) {
//    	   
//    	   if(transaction==null){
//    		   
//    		   transaction.rollback();
//    		   
//    	   }
//    	   else {
//    		   e.printStackTrace();
//    	   }
//		
//	}
       
       long id = s2.getStudentId();
       String name = s2.getName();
       String clg = s2.getCollege();
       String  phone = s2.getPhone();
       
       
       
       StudentService ss= new StudentService();
       
      // ss.getAllStudentsHQL();
       
       //ss.deleteStudent(8);
       
      // ss.getById(9);
       
//       ss.saveStudent();
//       
//       ss.getAllStudentsHQL();
       
       //ss.getStudentsByCriteria("NA");
       
       Certificate c3 = new Certificate();
     
     c3.setTitle("Java_Certification");
     c3.setLink("http://linkqwe.345");
     c3.setAbout("Sponsored by javadocs");
     c3.setLink("link");
     c3.setStudent(s2);
     
Certificate c4 = new Certificate();
     
     c4.setTitle("Java_Certification");
     c4.setLink("http://linkqwe.345");
     c4.setAbout("Sponsored by javadocs");
     c4.setLink("link");
     c4.setStudent(s2);
     
     
       
       s2.setName("Viraj");
       s2.setAbout("student");
       s2.setCollege("NA");
       s2.setFatherName("...");
       s2.setPhone("8245761406");
       
       s2.getCertificate().add(c3);
       s2.getCertificate().add(c4);
      
       //ss.updateStudent(11, s2);
       
       
       Session ssn = sessionFactory.openSession();
       
       try {
       
       transaction = ssn.beginTransaction();
       
       ssn.persist(s2);
       
       transaction.commit();
       
       System.out.println("data saved..");
       
       }catch (Exception e) {
    	   
    	   if(transaction==null) {
    		   transaction.rollback();
    	   }
    	   else {
		e.printStackTrace();
    	   }
	}
       
        
        
    }
}
