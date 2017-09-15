package com.sirius.order.service.mail.auth;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;



public class ConnectionAuth extends Authenticator{
	ResourceBundle messageProps = null;
	
	private static final Logger logger = Logger.getLogger(ConnectionAuth.class
			.getName());
	 @Override  
     public PasswordAuthentication getPasswordAuthentication() {  
		 messageProps = ResourceBundle.getBundle("com.sirius.order.service.mail.props.messageProperties");
		 final String from = messageProps.getString("FROM");
			logger.log(Level.FINE, "FROM email address : " + messageProps.getString("FROM"));
			final String password = messageProps.getString("PASS");
			if (password != ""){
				logger.log(Level.FINE, "a password has been recieved from the properties file");
			}
         return new PasswordAuthentication(from, password);  

     }  
}
