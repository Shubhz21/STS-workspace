package com.project.hiber;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import com.project.hiber.entities.Student;
import com.project.hiber.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StudentService {
	
	
	private SessionFactory sessionFactory=HibernateUtil.getsessionFactory();
	
	
	public void saveStudent() {
		
		Student student = new Student();
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("enter all details of student: name,clg,phone,about,fname.. ");
		
	
	       String name = s.next();
	       String clg = s.next();
	       String  phone = s.next();
	       String about =s.next();
	       String fname =s.next();
		
		try(Session session = sessionFactory.openSession()) {
			
			Transaction beginTransaction = session.beginTransaction();
			
			student.setName(name);
			student.setPhone(phone);
			
			student.setAbout(about);
			student.setCollege(clg);
			student.setFatherName(fname);
			
			session.persist(student);
			
			beginTransaction.commit();
			
			System.out.println("updated data: "+" | "+student.getName()+" | "+student.getCollege()+" | "+student.getAbout()+" | "+student.getFatherName()+" | "+student.getPhone());
			
		}catch (Exception e) {
			
			e.printStackTrace();
		
		}
		

	}
	
	
	public Student getById(long studentId) {
		
		try (Session session=sessionFactory.openSession()){
			
		Student student =	session.get(Student.class,studentId);
			
		System.out.println(" | "+student.getName()+" | "+student.getCollege()+" | "+student.getAbout());
		return student;
		
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	
	public Student updateStudent(long studentId,Student student) {
		
		try (Session session = sessionFactory.openSession()){
			
			
			Transaction beginTransaction = session.beginTransaction();
			
			
			Student oldStudent = session.get(Student.class, studentId);
			
			if(oldStudent!=null) {
				
				oldStudent.setName(student.getName());
				oldStudent.setCollege(student.getCollege());
				
			}
			
			 oldStudent=session.merge(oldStudent);
			
			beginTransaction.commit();
			
			return oldStudent;
			
		}
	}
	
	
	public void deleteStudent(long studentId) {
		
		try (Session session = sessionFactory.openSession()){
			
			Transaction beginTransaction = session.beginTransaction();
			
			Student student=session.get(Student.class, studentId);
			
			if(student!=null) {
				session.remove(student);
				
				beginTransaction.commit();
				System.out.println("student deleted :"+student.getStudentId());
			}
			
		}
		
	}
	
	
	//HQL[JPA]-->native query
	
	//get all student using hql
	
	public List<Student> getAllStudentsHQL(){
		
		
		
		try (Session session = sessionFactory.openSession()){
			
			String getHQL="From Student";
			Query<Student> query = session.createQuery(getHQL,Student.class);
			
			
			List<Student> studentList = query.list();
			
			for(Student student : studentList) {
				
				System.out.println( "| "+student.getName()+" | "+student.getCollege()+" | "+student.getAbout()+" | "+student.getFatherName()+" | "+student.getPhone());
				
			}
			
			return query.list();
			
		}
		
		
		
	}
	
	//get student by name
	
	
	public Student getStudentByNameHQL(String name) {
		try (Session session = sessionFactory.openSession()){
			
			String getByName="from student where name= :studentName";
			Query<Student> query =session.createQuery(getByName,Student.class);
			
			query.setParameter("studentName", name);
			
			return query.uniqueResult();
		}
	}
	
	 //criteria api :
	
	
	public List<Student> getStudentsByCriteria(String about){
		
		try (Session session = sessionFactory.openSession()){
			
			HibernateCriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
			
			CriteriaQuery<Student> query=criteriaBuilder.createQuery(Student.class);
			
			Root <Student> root = query.from(Student.class);
			
			query
			.select(root)
			.where(
					criteriaBuilder.equal(root.get("about"),about));
			
			Query<Student> query2=session.createQuery(query);
			
			List<Student> st = query2.getResultList();
			
			for(Student student : st ) {
				
				System.out.println( "| "+student.getName()+" | "+student.getCollege()+" | "+student.getAbout()+" | "+student.getFatherName()+" | "+student.getPhone());
				
			}
			
			
			return query2.getResultList();
			
			
		}
		
	}
	
	public List<Student> getStudentWithPagination(int pageNo, int pageSize){
		
		try (Session session = sessionFactory.openSession()){
			
			String pQuery="from student";
			
			Query<Student>query = session.createQuery(pQuery,Student.class);
			
			query.setFirstResult((pageNo-1)*pageSize);
			
			query.setMaxResults(pageSize);
			
			return query.list();
			
		}
		
	}
	
	
	
	
}
	
	
	
