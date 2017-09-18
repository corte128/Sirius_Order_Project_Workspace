package com.sirius.locationws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	/* Before using this class make sure 
	 * 1. DataSource is created inside Application Server
	 * 2. Add below resource reference(resource-ref) entry in web.xml
	 * 3. Datasource name (jdbc/EmployeeDS) should match the name you have entered while configuring the MySql to Websphere
	 * <resource-ref>
		<description />

		<res-ref-name>jdbc/OfficeApplicationDS</res-ref-name>
		<res-type>javax.sql.DataSource</res-type>
		<res-auth>Container</res-auth>
		<res-sharing-scope>Shareable</res-sharing-scope>
	</resource-ref>
	 */

	public static Connection getConnection() throws NamingException,
			SQLException {
		Context ctx = null;
		DataSource dataSource = null;
		Connection conn = null;
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("jdbc/OfficeApplicationDS");
			conn = dataSource.getConnection();
		} catch (NamingException e) {
			
			throw e;

		} catch (SQLException e) {
			
			throw e;
		}

		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}
	}
	
	public static void closeResultSet(ResultSet rs)
	{
		if (rs != null)
		{
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public static void closeStatement(Statement stmt)
	{
		if (stmt != null)
		{
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static void closePreparedStatement(PreparedStatement stmt)
	{
		if (stmt != null)
		{
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
