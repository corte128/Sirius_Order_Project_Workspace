package com.sirius.employeews.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.employeews.beans.EmployeeBean;

@WebService(name = "Employee", targetNamespace = "http://ace.sirius.com/profile/wsdl")
public interface Employee {
	
	@WebMethod(action = "getEmployeeByEmail")
	@WebResult(name = "getEmployeeByEmailReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "getEmployeeByEmail", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.GetEmployeeByEmail")
	@ResponseWrapper(localName = "getEmployeeByIdResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.GetEmployeeByEmailResponse")
	public EmployeeBean getEmployeeByEmail(
			@WebParam(name = "email", targetNamespace = "http://ace.sirius.com/profile/wsdl") String id);
	
	@WebMethod(action = "addEmployee")
	@WebResult(name = "addEmployeeReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "addEmployee", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.AddEmployee")
	@ResponseWrapper(localName = "addEmployeeResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.AddEmployeeResponse")
	public boolean addEmployee(
			@WebParam(name = "id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int id,
			@WebParam(name = "name", targetNamespace = "http://ace.sirius.com/profile/wsdl") String name,
			@WebParam(name = "password", targetNamespace = "http://ace.sirius.com/profile/wsdl") String password,
			@WebParam(name = "role", targetNamespace = "http://ace.sirius.com/profile/wsdl") String role,
			@WebParam(name = "email", targetNamespace = "http://ace.sirius.com/profile/wsdl") String email,
			@WebParam(name = "picture", targetNamespace = "http://ace.sirius.com/profile/wsdl") byte[] picture,
			@WebParam(name = "location", targetNamespace = "http://ace.sirius.com/profile/wsdl") int location);
	
	@WebMethod(action = "updateEmployee")
	@WebResult(name = "updateEmployeeReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "updateEmployee", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.UpdateEmployee")
	@ResponseWrapper(localName = "updateEmployeeResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.UpdateEmployeeResponse")
	public boolean updateEmployee(
			@WebParam(name = "id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int id,
			@WebParam(name = "isValid", targetNamespace = "http://ace.sirius.com/profile/wsdl") int isValid);
}
