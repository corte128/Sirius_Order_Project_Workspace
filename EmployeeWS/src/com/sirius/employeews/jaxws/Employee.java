package com.sirius.employeews.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.employeews.beans.EmployeeBean;

@WebService(name = "Employee", targetNamespace = "http://employeews.sirius.com/employee/wsdl")
public interface Employee {
	
	@WebMethod(action = "getEmployeeByEmail")
	@WebResult(name = "getEmployeeByEmailReturn", targetNamespace = "http://employeews.sirius.com/employee/wsdl")
	@RequestWrapper(localName = "getEmployeeByEmail", targetNamespace = "http://employeews.sirius.com/employee/wsdl", className = "com.sirius.ace.jaxws.GetEmployeeByEmail")
	@ResponseWrapper(localName = "getEmployeeByEmailResponse", targetNamespace = "http://employeews.sirius.com/employee/wsdl", className = "com.sirius.employeews.jaxws.GetEmployeeByEmailResponse")
	public EmployeeBean getEmployeeByEmail(
			@WebParam(name = "email", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String email);
	
	@WebMethod(action = "addEmployee")
	@WebResult(name = "addEmployeeReturn", targetNamespace = "http://employeews.sirius.com/employee/wsdl")
	@RequestWrapper(localName = "addEmployee", targetNamespace = "http://employeews.sirius.com/employee/wsdl", className = "com.sirius.employeews.jaxws.AddEmployee")
	@ResponseWrapper(localName = "addEmployeeResponse", targetNamespace = "http://employeews.sirius.com/employee/wsdl", className = "com.sirius.employeews.jaxws.AddEmployeeResponse")
	public boolean addEmployee(
			@WebParam(name = "name", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String name,
			@WebParam(name = "password", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String password,
			@WebParam(name = "role", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int role,
			@WebParam(name = "email", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String email,
			@WebParam(name = "picture", targetNamespace = "http://employeews.sirius.com/employee/wsdl") byte[] picture,
			@WebParam(name = "location", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int location);
	
	@WebMethod(action = "updateEmployee")
	@WebResult(name = "updateEmployeeReturn", targetNamespace = "http://employeews.sirius.com/employee/wsdl")
	@RequestWrapper(localName = "updateEmployee", targetNamespace = "http://employeews.sirius.com/employee/wsdl", className = "com.sirius.employeews.jaxws.UpdateEmployee")
	@ResponseWrapper(localName = "updateEmployeeResponse", targetNamespace = "http://employeews.sirius.com/employee/wsdl", className = "com.sirius.employeews.jaxws.UpdateEmployeeResponse")
	public boolean updateEmployee(
			@WebParam(name = "id", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int id,
			@WebParam(name = "isValid", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int isValid,
			@WebParam(name = "updaterId", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int updaterId);
}
