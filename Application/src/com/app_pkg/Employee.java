package com.app_pkg;
import java.sql.*;
import java.util.*;
public class Employee {
	
	private Connection connection;
	
	private Scanner scanner;
	
	private ResultSet resultSet;
	
	private PreparedStatement preparedStatement;
	
	public Employee(Connection connection,Scanner scanner){
		
		this.connection = connection;
		this.scanner = scanner;
		
		
	}
	
	public void fetchAllemp() {
		
		try {
			
			 preparedStatement = connection.prepareStatement("select * from employee");
			
			 resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
			System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)+" | "+resultSet.getString(3)+" | ");
			
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}
	
	
	public void fetchById(int id) {
		
		try {
			
			 preparedStatement = connection.prepareStatement("select * from employee where id = ?");
			
			preparedStatement.setInt(1,id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				System.out.println(resultSet.getString(1)+" | "+resultSet.getString(2)+" | "+resultSet.getString(3)+" | ");
			}
			
				
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void insertDt() {
		
		try {
			
			System.out.println("enter values id,email,name..");
		
		int id=scanner.nextInt(); String email=scanner.next(),name=scanner.next();
		
		 preparedStatement = connection.prepareStatement("insert into employee (id,email,name) values ( ? ,? ,? )");
		 
		 
		 preparedStatement.setInt(1,id);
		 preparedStatement.setString(2,email);
		 preparedStatement.setString(3,name);
		 
		 int effect = preparedStatement.executeUpdate();
		 
		 System.out.println("rows affected: "+effect);
		 
		}catch(SQLException se) {
			
		}
	}
	
	
	public void deleteById(int id) {
		
		try {
		
		preparedStatement = connection.prepareStatement("delete  employee where id = ?");
		
		preparedStatement.setInt(1,id);
		
		int effect = preparedStatement.executeUpdate();
		
		System.out.println("employees/rows deleted: "+effect);
		
		
	}catch(SQLException ex) {
		ex.printStackTrace();
	}
	
	}
	
	
	
}
