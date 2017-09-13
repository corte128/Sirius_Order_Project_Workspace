package com.sirius.employeews.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.employeews.beans.EmployeeBean;

public class EmployeeServiceDAOImpl {
	
	private static final Logger logger = Logger.getLogger(EmployeeServiceDAOImpl.class
			.getName());
	
	private Connection conn = null;
	
	public EmployeeServiceDAOImpl(Connection c){
		conn = c;
	}
	
	public void addEmployee(String name, int role, 
			String email, byte[] picture, int location) throws SQLException{
		try {
			conn = DBConnection.getConnection();
			String sqlQuery = "INSERT INTO employee_tbl "+
			"(employee_name, employee_type_id_fk, employee_email, employee_picture, location_id_fk, created_by, created_date) " +
			"VALUES (?, ?, ?, ?, ?, 1, (SELECT CURDATE()))";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, name);
			statement.setInt(2, role);
			statement.setString(3, email);
			statement.setBytes(4, picture);
			statement.setInt(5, location);
			statement.executeUpdate();
			conn.commit();
			DBConnection.closeStatement(statement);
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error addEmployee() " +e1);
			e1.printStackTrace();
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error addEmployee() " +e1);
			e1.printStackTrace();
		} 
	}
	
	public boolean addEmployeeLogin(int id, String password, String email){
		try {
			conn = DBConnection.getConnection();
			String sqlQuery = "INSERT INTO login_tbl (username_pk, login_password, is_valid, employee_id_fk, created_by, created_date)" + 
			"VALUES (?, ?, ?, ?, 1, (SELECT CURDATE()))";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, "pending");
			statement.setInt(4, id);
			statement.executeUpdate();
			conn.commit();
			DBConnection.closeStatement(statement);
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error addEmployeeLogin() " +e1);
			e1.printStackTrace();
			return false;
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error addEmployeeLogin() " +e1);
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	public EmployeeBean getEmployeeByEmail(String email) throws SQLException {
		EmployeeBean emp = null;
		try {
			conn = DBConnection.getConnection();
			String sqlQuery = "SELECT employee_id_pk FROM employee_tbl WHERE employee_email = (?)";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				emp = new EmployeeBean();
				emp.setId(rs.getInt("employee_id_pk"));
				emp.setEmail(email);
				emp.setLocation(rs.getInt("location_id_fk"));
				emp.setName(rs.getString("employee_name"));
				emp.setPicture(rs.getBytes("employee_picture"));
				emp.setRole(rs.getInt("employee_type_id_fk"));
			}
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error getEmployeeByEmail() " +e1);
			conn.rollback();
			e1.printStackTrace();
		} catch (SQLException e1) {
			conn.rollback();
			logger.log(Level.FINE, "Error getEmployeeByEmail() " +e1);
			e1.printStackTrace();
		} 
		return emp;
	}
	
	public boolean updateEmployee(int id, int isValid, int updaterId) throws SQLException {
		
		try {
			conn = DBConnection.getConnection();
			if (id == 0) {
				return false;
			}
			String sqlQuery = "UPDATE login_tbl SET is_valid = (?), updated_by = (?), updated_date = (SELECT CURDATE()) WHERE username_pk = (?)";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1, isValid);
			statement.setInt(2, updaterId);
			statement.setInt(3, id);
			statement.executeUpdate();
			
			if (isValid == 1){
				String sql = "UPDATE employee_tbl SET is_employee = true WHERE employee_id_pk = (?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				statement.executeUpdate();
			}
			
			conn.commit();
			DBConnection.closeStatement(statement);
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error updateEmployee() " +e1);
			conn.rollback();
			e1.printStackTrace();
			return false;
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error updateEmployee() " +e1);
			conn.rollback();
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
}
