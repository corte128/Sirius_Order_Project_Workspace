package com.sirius.employeews.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.employeews.beans.EmployeeBean;

public class EmployeeServiceDAO {
	
	private static final Logger logger = Logger.getLogger(EmployeeServiceDAO.class
			.getName());
	
	public EmployeeBean getEmployeeByEmail(String email) {
		logger.log(Level.FINE, "Getting employee by email");
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
		logger.log(Level.FINE, "Adding Employee");
		Connection conn = null;
		boolean result = false;
		boolean result2 = false;
		try {
			conn = DBConnection.getConnection();
			EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
			result = impl.addEmployee(name, role, email, picture, location);
			EmployeeBean emp = impl.getEmployeeByEmail(email);
			if (emp != null){
				result2 = impl.addEmployeeLogin(emp.getId(), password, email);
			}
			impl.closeConnection();
			if (result && result2){
				conn.commit();
				return true;
			}
			else{
				logger.log(Level.FINE, "Error in AddEmployee: One or more queries failed.");
				conn.rollback();
			}
			return result;
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error in AddEmployee: " + e1);
			e1.printStackTrace();
			return false;
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error in AddEmployee: " + e1);
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean updateEmployee(int id, String isValid, int updaterId) {
		logger.log(Level.FINE, "Updating Employee");
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
