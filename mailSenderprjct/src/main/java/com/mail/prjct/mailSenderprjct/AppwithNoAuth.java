package com.mail.prjct.mailSenderprjct;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class AppwithNoAuth {

	public static void main(String[] args) {
		
		


		
		
		
		
		String host = "localhost"; // Papercut is running locally
        int port = 2026; // Papercut default port
        String from = "fundeshubham@gmail.com"; // fake sender email
        String to = "shubhamph195@gmai.com"; // fake receiver email
		
		
		Properties prop = new Properties();
		
		prop.put("mail.smtp.auth", "false");
		prop.put("mail.smtp.starttls.enable","false");
		prop.put("mail.smtp.host",host);
		prop.put("mail.smtp.port", String.valueOf(port));
		
//		Session session = Session.getInstance(prop, new Authenticator() {
//		    @Override
//		    protected PasswordAuthentication getPasswordAuthentication() {
//		        return new PasswordAuthentication(from, password);
//		    }
//		});
		
		Session session = Session.getInstance(prop);

		
		System.out.println("session created success...");
		
		try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test Mail");
            message.setText("Hello! this is shubham phunde..\n"
            		+ "All Faculties are here by informed that we have launched our new application \n"
            		+ "freeMailSender");

            
            Transport.send(message);

            System.out.println("Email sent successfully to server");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
		
	}
}
