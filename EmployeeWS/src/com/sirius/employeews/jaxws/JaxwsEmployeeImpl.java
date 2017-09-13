package com.sirius.employeews.jaxws;

import javax.jws.WebService;

import com.sirius.employeews.beans.EmployeeBean;
import com.sirius.employeews.db.EmployeeServiceDAO;

@WebService(endpointInterface = "com.sirius.employeews.jaxws.Employee", portName = "employee", targetNamespace = "http://employeews.sirius.com/employee/wsdl", serviceName = "JaxwsEmployee")
public class JaxwsEmployeeImpl implements Employee{
	
	@Override
	public boolean updateEmployee(int id, int isValid, int updaterId) {
		EmployeeServiceDAO dao = new EmployeeServiceDAO();
		return dao.updateEmployee(id, isValid, updaterId);
	}
	
	@Override
	public EmployeeBean getEmployeeByEmail(String email) {
		EmployeeServiceDAO dao = new EmployeeServiceDAO();
		return dao.getEmployeeByEmail(email);
	}

	@Override
	public boolean addEmployee(String name, String password, int role,
			String email, byte[] picture, int location) {
		EmployeeServiceDAO dao = new EmployeeServiceDAO();
		return dao.addEmployee(name, password, role, email, picture, location);
	}
	
}
