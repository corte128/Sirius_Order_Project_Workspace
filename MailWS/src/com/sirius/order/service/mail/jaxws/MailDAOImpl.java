package com.sirius.order.service.mail.jaxws;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDAOImpl {
	private static final Logger logger = Logger.getLogger(MailDAOImpl.class
			.getName());
	
	
	ResourceBundle serverProps = null;
	ResourceBundle messageProps = null;
	

	
	Properties props = new Properties();
	
	
	public boolean sendMessage(String subject, String message, String toAddress){
		
		//load the appropriate resources
	    serverProps = ResourceBundle.getBundle("com.sirius.order.service.mail.props.serverProperties");
		messageProps = ResourceBundle.getBundle("com.sirius.order.service.mail.props.messageProperties");
		
		logger.log(Level.FINE, "inside of sendMessage in MailDAOImpl");
		
		final String from = messageProps.getString("FROM");
		logger.log(Level.FINE, "FROM email address : " + messageProps.getString("FROM"));
		
		final String password = messageProps.getString("PASS");
		if (password != ""){
			logger.log(Level.FINE, "a password has been recieved from the properties file");
		}
		
		
		String host= serverProps.getString("HOST");
		String port = serverProps.getString("PORT");
		//String host = "smtp.gmail.com";
		
		
		props.put("mail.smtp.starttls.enable", "true");
	//	props.put("mail.debug", "true");
		
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", password);
	    props.put("mail.smtp.port", port);
	    props.put("mail.smtp.auth", serverProps.getString("AUTH"));
		

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage mimeMessage = new MimeMessage(session);
	
		try { 
			  mimeMessage.setFrom(new InternetAddress(messageProps.getString("FROM")));
	          mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(toAddress));
	          mimeMessage.setSubject(subject);
	          mimeMessage.setSentDate(new Date());
	          mimeMessage.setText(message);
	          
	          //Transport.send(mimeMessage);
	          Transport t = session.getTransport("smtp");
	          t.connect(from, password);
	          t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	          t.close();
		}catch (MessagingException e) {
			logger.log(Level.FINE, "Messaging exception has been encountered" + "\n" + e.getMessage() );
			
			return false;
			
			}  
	
	return true;
	}
	
	
}
