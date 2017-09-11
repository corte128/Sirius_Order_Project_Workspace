package com.sirius.employeews.jaxws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.sirius.employeews.jaxws.Employee", portName = "employee", targetNamespace = "http://ace.sirius.com/profile/wsdl", serviceName = "JaxwsProfile")
public class JaxwsEmployeeImpl implements Employee{

	@Override
	public boolean updateEmployee(int id, boolean isValid) {
		//db call to update employee
		return true;
	}
	
}
