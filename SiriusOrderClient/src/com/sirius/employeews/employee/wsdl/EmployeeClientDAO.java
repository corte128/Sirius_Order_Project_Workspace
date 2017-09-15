package com.sirius.employeews.employee.wsdl;

public class EmployeeClientDAO {
	
	public EmployeeBean getEmployeeByEmail(String email){
		EmployeeProxy client = new EmployeeProxy();
		return client.getEmployeeByEmail(email);
	}
	
	public boolean addEmployee(String name, String password, int role,
			String email, byte[] picture, int location){
		EmployeeProxy client = new EmployeeProxy();
		return client.addEmployee(name, password, role, email, picture, location);
	}
	
	public boolean updateEmployee(int id, String isValid, int updaterId) {
		EmployeeProxy client = new EmployeeProxy();
		return client.updateEmployee(id, isValid, updaterId);
	}
	
}
