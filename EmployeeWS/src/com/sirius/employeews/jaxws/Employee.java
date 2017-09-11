package com.sirius.employeews.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Employee", targetNamespace = "http://ace.sirius.com/profile/wsdl")
public interface Employee {
	
	@WebMethod(action = "updateEmployee")
	@WebResult(name = "updateEmployeeReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "updateEmployee", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.UpdateEmployee")
	@ResponseWrapper(localName = "updateEmployeeResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.UpdateEmployeeResponse")
	public boolean updateEmployee(
			@WebParam(name = "id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int id,
			@WebParam(name = "isValid", targetNamespace = "http://ace.sirius.com/profile/wsdl") boolean isValid);
}
