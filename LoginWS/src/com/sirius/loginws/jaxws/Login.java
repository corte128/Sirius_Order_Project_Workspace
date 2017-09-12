package com.sirius.loginws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

//TODO: change wsdl location
@WebService(name = "Login", targetNamespace = "http://loginws.sirius.com/login/wsdl")
public interface Login {
	
	@WebMethod(action = "getEmployeeByCredentials")
	@WebResult(name = "getEmployeeByCredentialsReturn", targetNamespace = "http://loginws.sirius.com/login/wsdl")
	@RequestWrapper(localName = "getEmployeeByCredentials", targetNamespace = "http://loginws.sirius.com/login/wsdl", className = "com.sirius.loginws.jaxws.GetEmployeeByCredentials")
	@ResponseWrapper(localName = "getEmployeeByCredentialsResponse", targetNamespace = "http://loginws.sirius.com/login/wsdl", className = "com.sirius.loginws.jaxws.GetEmployeeByCredentialsResponse")
	public int getEmployeeByCredentials(
			@WebParam(name = "email", targetNamespace = "http://loginws.sirius.com/login/wsdl") String email,
			@WebParam(name = "password", targetNamespace = "http://loginws.sirius.com/login/wsdl") String password);
}
