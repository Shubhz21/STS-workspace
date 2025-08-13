package com.app_pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {

	
	private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username="system";
	private static final String password="admin";
	
	
	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		Connection connection = DriverManager.getConnection(url,username,password);
		
		Employee employee = new Employee(connection,scanner);
		
		int id;
		
		while(true) {
		
		System.out.println("enter task you want to perform:\n"
				+ " press 1: for fetch all data"
				+ "\n press 2: for fetch by id \n"
				+ " press 3 : for insert data\n"
				+ " press 4 : for delete data\n"
				+ "press 5 to exit\n");
		
		int task=scanner.nextInt() ;
		
		switch(task) {
		
		case 1: employee.fetchAllemp();
			
			break;
			
		case 2:
			System.out.println("enter id of employee: ");
			 id = scanner.nextInt();
			employee.fetchById(id);
			break;
		case 3:
			
			employee.insertDt();
			
			break;
			
		case 4: 
			System.out.println("enter id of employee: ");
		 id = scanner.nextInt();
		 employee.deleteById(id);
			break;
			
		case 5: 
			
			return;
		
			default:
				
				System.out.println("plz check input");
				
			break;
		
		}
		}
		
	}
}
