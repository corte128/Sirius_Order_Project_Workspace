package com.sirius.employeews.jaxws;

import javax.jws.WebService;

import com.sirius.employeews.beans.EmployeeBean;

@WebService(endpointInterface = "com.sirius.employeews.jaxws.Employee", portName = "employee", targetNamespace = "http://ace.sirius.com/profile/wsdl", serviceName = "JaxwsProfile")
public class JaxwsEmployeeImpl implements Employee{

	@Override
	public EmployeeBean getEmployeeByEmail(String email) {
		EmployeeBean emp = new EmployeeBean();
		//TODO: db call to login table to check credentials
		return emp;
	}

	@Override
	public boolean addEmployee(int id, String name, String password,
			String role, String email, byte[] picture, int location) {
		EmployeeBean emp = new EmployeeBean();
		emp.setId(id);
		emp.setName(name);
		emp.setPassword(password);
		emp.setRole(role);
		emp.setEmail(email);
		emp.setPicture(picture);
		emp.setLocation(location);
		//TODO: db call to add employee to login table
		//TODO: db call to add employee to employee table
		return true;
	}
	
	@Override
	public boolean updateEmployee(int id, int isValid) {
		//db call to update employee
		return true;
	}
	
}
