package com.sirius.employeews.db;

import java.io.CharArrayReader;
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
	
	public void addEmployee(int id, String name, char[] password,
			String role, String email, byte[] picture, int location) throws SQLException{
		try {
			System.out.println("#####################Adding employee########################");
			conn = DBConnection.getConnection();
			String sqlQuery = "INSERT INTO Employee_tbl "+
			"(ID, Name, Role, Email, Picture, Password, Location) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setString(3, role);
			statement.setString(4, email);
			statement.setBytes(5, picture);
			statement.setCharacterStream(6, new CharArrayReader(password));
			statement.setInt(7, location);
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
	
	public EmployeeBean getEmployeeByEmail(String email) {
		EmployeeBean emp = null;
		try {
			conn = DBConnection.getConnection();
			String sqlQuery = "SELECT * FROM Employee_tbl WHERE Email = (?)";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				emp = new EmployeeBean();
				emp.setName(rs.getString("Name"));
				emp.setId(rs.getInt("ID"));
				emp.setRole(rs.getString("Role"));
				//emp.setPassword(rs.getString("Password"));
				emp.setEmail(rs.getString("Email"));
				emp.setPicture(rs.getBytes("Picture"));
			}
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error getEmployeeById() " +e1);
			e1.printStackTrace();
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error getEmployeeById() " +e1);
			e1.printStackTrace();
		} 
		return emp;
	}
	
	public boolean updateEmployee(int id, boolean isValid) throws SQLException {
		
		try {
			conn = DBConnection.getConnection();
			if (id == 0) {
				return false;
			}
			String sqlQuery = "UPDATE Employee_tbl SET Valid = (?) WHERE ID=(?)";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setBoolean(1, isValid);
			statement.setInt(2, id);
			statement.executeUpdate();
			conn.commit();
			DBConnection.closeStatement(statement);
		} catch (NamingException e1) {
			logger.log(Level.FINE, "Error update() " +e1);
			e1.printStackTrace();
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error update() " +e1);
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
}