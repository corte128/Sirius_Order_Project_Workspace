package com.sirius.employeews.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.sirius.employeews.beans.EmployeeBean;

public class EmployeeServiceDAO {
	
	public EmployeeBean getEmployeeByEmail(String email) {
		Connection conn = null;
		EmployeeBean emp = null;
		try {
			conn = DBConnection.getConnection();
			EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
			emp = impl.getEmployeeByEmail(email);
			impl.closeConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public boolean addEmployee(String name, String password, int role,
			String email, byte[] picture, int location){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
			impl.addEmployee(name, role, email, picture, location);
			EmployeeBean emp = impl.getEmployeeByEmail(email);
			impl.addEmployeeLogin(emp.getId(), password, email);
			impl.closeConnection();
			return true;
		} catch (NamingException e1) {
			e1.printStackTrace();
			return false;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployee(int id, int isValid, int updaterId) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
			result = impl.updateEmployee(id, isValid, updaterId);
			impl.closeConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
}
