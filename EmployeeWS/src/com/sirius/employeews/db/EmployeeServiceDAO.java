package com.sirius.employeews.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.sirius.employeews.beans.EmployeeBean;

public class EmployeeServiceDAO {
	
	public EmployeeBean getEmployeeByEmail(String email) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
		EmployeeBean emp = impl.getEmployeeByEmail(email);
		impl.closeConnection();
		return emp;
	}
	
	public void addEmployee(EmployeeBean e) throws SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
		impl.addEmployee(e.getId(), e.getName(), e.getPassword(),
			e.getRole(), e.getEmail(), e.getPicture(), e.getLocation());
		impl.closeConnection();
	}
	
	public void updateEmployee(int id, boolean isValid) throws SQLException{
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmployeeServiceDAOImpl impl = new EmployeeServiceDAOImpl(conn);
		impl.updateEmployee(id, isValid);
		impl.closeConnection();
	}
	
}