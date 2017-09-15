package com.sirius.order.service.mail.jaxws;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MailDAO {
	private static final Logger logger = Logger.getLogger(MailDAO.class
			.getName());
	public boolean sendMessage(String subject, String message, String toAddress){
		logger.log(Level.FINE, "inside SendMessage in the class MailDAO");
		logger.log(Level.FINE, "inside SendMessage in the class MailDAO \n toAddress Recieved : " + toAddress);
		logger.log(Level.FINE, "inside SendMessage in the class MailDAO \n subject recieved : " + subject);
		logger.log(Level.FINE, "inside SendMessage in the class MailDAO \n message recieved : " + message);
		
		MailDAOImpl mailDAOImpl = new MailDAOImpl();
		boolean response = mailDAOImpl.sendMessage(subject, message, toAddress);
		
		
		
		return response;
	
	
	
	}
}
