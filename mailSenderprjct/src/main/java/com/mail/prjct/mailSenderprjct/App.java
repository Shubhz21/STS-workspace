package com.mail.prjct.mailSenderprjct;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    
    {
    	
final String from = "fundeshubham@gmail.com";
		
final String password = "aeih dmyd dhli tmnd";
		
		
		String to = "shubhamph195@gmail.com";
		//  String to ="shubhamfunde2222@gmail.com";
		
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
            message.setSubject("Test mail of java Mail_Sender_Application...About Class Attendence");
            message.setText("Hello, Ms.Priya salve\n\n"
            		+ "As per the guideines and rules you are informed that to attend the classes on time \n"
            		+ "as per schedule..\n"
            		+ "its has been seen that your attendence score is very less and its below the average\n"
            		+ "kindly take a note of that , otherwise strict action will be taken.\n"
            		+ "Regards,\n\n"
            		+ "Head Department\n"
            		+ "(Bhelwandi, Shrigonda)");

           
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
		
		
    }

