package com.sirius.order.service.mail.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(name = "JaxwsMail", targetNamespace = "http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl")
public interface JaxwsMail {
	
	
	@WebMethod(action = "sendMessage")
	@WebResult(name = "sendMessageReturn", targetNamespace = "http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl")
	@RequestWrapper(localName = "sendMessage", targetNamespace = "http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", className = "com.sirius.order.service.mail.jaxws.SendMessage")
	@ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", className = "com.sirius.order.service.mail.jaxws.SendMessageResponse")
	public boolean sendMessage(
			@WebParam(name = "subject", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String subject,
			@WebParam(name = "message", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String message,
			@WebParam(name = "toAddress", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String toAddress);
	
	
}
