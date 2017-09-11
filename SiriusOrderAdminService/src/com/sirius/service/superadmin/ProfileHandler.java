package com.sirius.service.superadmin;


import javax.jws.WebService;

import com.sirius.back.beans.EmployeeBean;

@WebService(endpointInterface="com.sirius.back.services.Profile",
portName="profile", targetNamespace ="http://services.back.sirius.com/profile/wsdl",
serviceName="ProfileService")
public class ProfileHandler implements Profile{

	
	public ProfileHandler(){
		
	}
	
	@Override
	public boolean CreateEmployee(EmployeeBean employee) {
		return EmployeePortalDAO.CreateEmployee(employee);
	}

	@Override
	public int getEmployeeId(String username){
		return EmployeePortalDAO.getEmployeeId(username);
	}
	
	@Override
	public EmployeeBean[] getSpecificEmployees(String role) {
		return EmployeePortalDAO.getSpecificEmployees(role);
	}

	@Override
	public EmployeeBean[] getAllEmployees() {
		return EmployeePortalDAO.getAllEmployees();
	}

	@Override
	public EmployeeBean getEmployeeById(int id) {
		return EmployeePortalDAO.getEmployeeById(id);
	}

	public EmployeeBean[] getEmployeeByProperties(String firstName, String lastName, String email, String role){
		return EmployeePortalDAO.getEmployeeByProperties(firstName, lastName, email, role);
	}
	
	@Override
	public boolean updateEmployee(EmployeeBean employee) {
		return EmployeePortalDAO.updateEmployee(employee);
	}
	
	@Override
	public boolean updateEmployeeManager(int id, int managerId) {
		return EmployeePortalDAO.updateEmployeeManager(id, managerId);
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		return EmployeePortalDAO.deleteEmployeeById(id);
	}

}
