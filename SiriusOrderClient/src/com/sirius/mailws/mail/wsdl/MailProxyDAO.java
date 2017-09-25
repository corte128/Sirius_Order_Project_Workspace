package com.sirius.mailws.mail.wsdl;

public class MailProxyDAO {
	JaxwsMailProxy proxy = new JaxwsMailProxy();
	public boolean sendMessage(String subject, String message, String toAddress){
		return proxy.sendMessage(subject, message, toAddress);
	}
}
