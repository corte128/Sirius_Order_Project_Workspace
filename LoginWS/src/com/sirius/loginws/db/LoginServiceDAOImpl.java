package com.sirius.loginws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class LoginServiceDAOImpl {
	
	private static final Logger logger = Logger.getLogger(LoginServiceDAOImpl.class
			.getName());
	
	private Connection conn = null;
	
	public LoginServiceDAOImpl(Connection c){
		conn = c;
	}
	
	public int getEmployeeByCredentials(String email, String password){
		int result = 0;
		try{
			logger.log(Level.FINE, "Looking for employee in database...");
			String sqlQuery = "SELECT employee_id_fk FROM login_tbl WHERE username_pk = (?) AND login_password = (?) AND is_valid = (?)";
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, "accepted");
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				result = rs.getInt("employee_id_fk");
			}
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		} catch (SQLException e1) {
			logger.log(Level.FINE, "Error getEmployeeByCredentials() " +e1);
			e1.printStackTrace();
		} 
		return result;	
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
}
