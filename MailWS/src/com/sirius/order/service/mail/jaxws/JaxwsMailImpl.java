package com.sirius.order.service.mail.jaxws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;


@WebService(endpointInterface = "com.sirius.order.service.mail.jaxws.JaxwsMail", portName = "JaxwsMail", targetNamespace = "http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", serviceName = "JaxwsMail")

public class JaxwsMailImpl {
	private static final Logger logger = Logger.getLogger(MailDAO.class
			.getName());
	public boolean sendMessage(String subject, String message, String toAddress){
		logger.log(Level.FINE, "inside SendMessage in the class JaxwsMailImpl");
		logger.log(Level.FINE, "inside SendMessage in the class JaxwsMailImpl \n toAddress Recieved : " + toAddress);
		logger.log(Level.FINE, "inside SendMessage in the class JaxwsMailImpl \n subject recieved : " + subject);
		logger.log(Level.FINE, "inside SendMessage in the class JaxwsMailImpl \n message recieved : " + message);
		if (!toAddress.matches("^[A-z0-9](\\.?[A-z0-9_-]){0,}@[A-z0-9-]+\\.([A-z]{1,6}\\.)?[A-z]{2,6}$")){
			return false;
		}else{
			MailDAO mailDAO = new MailDAO();
			boolean response = mailDAO.sendMessage(subject, message, toAddress);
			
			
			
			return response;
			
		}
		
		
	
	
	}
}
