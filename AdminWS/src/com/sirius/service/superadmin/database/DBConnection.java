package com.sirius.service.superadmin.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	private static final Logger logger = Logger.getLogger(DBConnection.class
			.getName());

	/*
	 * Before using this class make sure 1. DataSource is created inside
	 * Application Server 2. Add below resource reference(resource-ref) entry in
	 * web.xml 3. Datasource name (jdbc/EmployeeDS) should match the name you
	 * have entered while configuring the MySql to Websphere <resource-ref>
	 * <description />
	 * 
	 * <res-ref-name>jdbc/EmployeeDS</res-ref-name>
	 * <res-type>javax.sql.DataSource</res-type> <res-auth>Container</res-auth>
	 * <res-sharing-scope>Shareable</res-sharing-scope> </resource-ref>
	 */

	/**
	 * Sets up the connection to the data source
	 * 
	 * @return Connection
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws NamingException,
			SQLException, Exception {
		Context ctx = null;
		DataSource dataSource = null;
		Connection conn = null;
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("jdbc/OfficeApplicationDS");
			conn = dataSource.getConnection();
			logger.log(Level.FINE,
					"##############OPENED CONNECTION#######################");
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		return conn;
	}

	/**
	 * Closes the connection on call
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
				logger.log(Level.FINE,
						"##############CONNECTION CLOSED#######################");
			} catch (SQLException e) {
				throw e;
			}

		}
	}

	/**
	 * Closes the result set on call
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				throw e;
			}

		}
	}

	/**
	 * Closes the statement on call
	 * 
	 * @param stmt
	 */
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Closes the prepared statement on call
	 * 
	 * @param stmt
	 */
	public static void closePreparedStatement(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
