package mypkg;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;


import jakarta.mail.Session;

public class MailSentApp {
	
	public static void main(String[] args) {
		
		final String from = "fundeshubham@gmail.com";
		
		final String password = "Shubham@1998";
		
		
		String to = "shubhamph195@gmail.com";
		
		Properties prop = new Properties();
		
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		Session ssn = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(from, password);
		    }
		});

		
		System.out.println("session created success");
	}

}
