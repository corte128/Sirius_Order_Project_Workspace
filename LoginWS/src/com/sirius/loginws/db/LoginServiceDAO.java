package com.sirius.loginws.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public class LoginServiceDAO {
	
	public int getEmployeeByCredentials(String email, String password){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LoginServiceDAOImpl impl = new LoginServiceDAOImpl(conn);
		int result = impl.getEmployeeByCredentials(email, password);
		impl.closeConnection();
		return result;
	}	
}
