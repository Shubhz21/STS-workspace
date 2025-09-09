package com.testmail.prjct.try_with_resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;



public class DbCon {
	
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static String username="system";
	private static String pwd = "admin";
	
	Connection con =null;
	
	PreparedStatement preparedStatement=null;
	
	
	public static void main(String[] args) {
		
		
		
		
		try (Connection con = DriverManager.getConnection(url,username,pwd)){
			
			if(con!=null) {
				System.out.println("connection success...");
				
				final String from = "fundeshubham@gmail.com";
				
				final String password = "aeih dmyd dhli tmnd";
				
				
				String to = "shubhamph195@gmail.com";
				
				Properties prop = new Properties();
				
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable","true");
				prop.put("mail.smtp.host","smtp.gmail.com");
				prop.put("mail.smtp.port", "587");
				
				Session session = Session.getInstance(prop, new Authenticator() {
				    @Override
				    protected PasswordAuthentication getPasswordAuthentication() {
				        return new PasswordAuthentication(from, password);
				    }
				});

				
				System.out.println("session created success");
				
				try {
		            
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress(from));
		            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		            message.setSubject("Testing mail");
		            message.setText("DB Connections Established");

		           
		            Transport.send(message);

		            System.out.println("Email sent successfully!");

		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
		    
				
				
			}
			
			
			
		}catch (Exception e) {
			
			System.out.println("Error in connection with db..\n");
			e.printStackTrace();
			
		}
		
	}

}
