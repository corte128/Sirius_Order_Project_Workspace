package com.sirius.loginws.jaxws;

import javax.jws.WebService;

import com.sirius.loginws.beans.EmployeeBean;

//TODO: change wsdl location
@WebService(endpointInterface = "com.sirius.loginws.jaxws.Login", portName = "employee", targetNamespace = "http://ace.sirius.com/profile/wsdl", serviceName = "JaxwsProfile")
public class JaxwsLoginImpl implements Login{

	@Override
	public EmployeeBean getEmployeeByEmail(String email) {
		EmployeeBean emp = new EmployeeBean();
		//TODO: db call to login table to check credentials
		return emp;
	}

	@Override
	public boolean addEmployee(int id, String name, char[] password,
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
	
}
