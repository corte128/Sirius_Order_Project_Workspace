package com.sirius.service.superadmin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.superadmin.database.DBConnection;

public class SuperAdminDAOImplementation {
	private static final Logger logger = Logger
			.getLogger(SuperAdminDAOImplementation.class.getName());
	private static final ResourceBundle queries = ResourceBundle
			.getBundle("com.sirius.service.properties.queries");

	/**
	 * Setting budget based on the office specified
	 * 
	 * @param budget
	 * @param locationId
	 * @param conn
	 * @return boolean
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static boolean setBudgetByLocation(BigDecimal budget,
			int locationId, Connection conn) throws NamingException,
			SQLException {
		PreparedStatement statement = null;
		boolean completed = false;

		String budgetQuery = queries.getString("SET_BUDGET_BY_LOCATION");

		try {
			logger.log(Level.FINE, "Preparing to execute budget query: ");
			logger.log(Level.FINE, "   " + budgetQuery);
			// setting budget to the table
			statement = conn.prepareStatement(budgetQuery);
			statement.setBigDecimal(1, budget);
			statement.setInt(2, locationId);

			logger.log(Level.FINE,
					"Setting the budget based on the paramaters: ");
			logger.log(Level.FINE, "   BigDecimal:" + budget);
			logger.log(Level.FINE, "   Int:" + locationId);
			// executing update statement
			statement.executeUpdate();

			logger.log(Level.FINE, "Budget set complete");
			// setting completion
			completed = true;
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
		}
		return completed;
	}

	/**
	 * Gets the budget based on the location
	 * 
	 * @param locationId
	 * @param conn
	 * @return BigDecimal
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static BigDecimal getBudgetByLocation(int locationId, Connection conn)
			throws NamingException, SQLException {
		PreparedStatement statement = null;
		BigDecimal budget = null;
		ResultSet result = null;

		String budgetQuery = queries.getString("GET_BUDGET_BY_LOCATION");

		try {
			logger.log(Level.FINE, "Preparing to execute budget query: ");
			logger.log(Level.FINE, "   " + budgetQuery);
			// getting budget from the table
			statement = conn.prepareStatement(budgetQuery);
			statement.setInt(1, locationId);

			logger.log(Level.FINE,
					"Getting the budget based on the paramaters: ");
			logger.log(Level.FINE, "   Int:" + locationId);
			// executing select statement
			result = statement.executeQuery();

			if (result.next()) {
				budget = result.getBigDecimal(1);
			} else {
				logger.log(Level.FINE,
						"No results aquired... does that location have a budget?");
			}

		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (result != null){
				DBConnection.closeResultSet(result);
			}
		}
		return budget;
	}

	/**
	 * Adds the location to the database
	 * @param city
	 * @param stateId
	 * @param conn
	 * @return boolean
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static boolean addLocation(String city, int stateId, Connection conn)
			throws NamingException, SQLException {
		PreparedStatement statement = null;
		boolean completed = false;

		String locationQuery = queries.getString("ADD_LOCATION");

		try {
			logger.log(Level.FINE, "Preparing to execute location query: ");
			logger.log(Level.FINE, "   " + locationQuery);
			// adding location to the table
			statement = conn.prepareStatement(locationQuery);
			statement.setString(1,city);
			statement.setInt(2,stateId);
			statement.setInt(3,0);

			logger.log(Level.FINE,
					"Adding the location based on the paramaters: ");
			logger.log(Level.FINE, "   String:" + city);
			logger.log(Level.FINE, "   Int:" + stateId);
			// executing select statement
			statement.executeUpdate();

			// execute complete
			completed = true;
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
		}
		return completed;
	}
	
	/**
	 * Gets the states Id based on the state abbreviation
	 * @param state
	 * @param conn
	 * @return int
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static int getStateId(String state, Connection conn) throws NamingException, SQLException{
		PreparedStatement statement = null;		
		int stateId = 0;
		ResultSet result = null;

		String stateQuery = queries.getString("GET_STATE_ID");

		try {
			logger.log(Level.FINE, "Preparing to execute state query: ");
			logger.log(Level.FINE, "   " + stateQuery);
			// selecting state id from table
			statement = conn.prepareStatement(stateQuery);
			statement.setString(1, state);

			logger.log(Level.FINE,
					"Getting the stateId based on the paramaters: ");
			logger.log(Level.FINE, "   String:" + state);
			// executing select statement
			result = statement.executeQuery();

			if (result.next()) {
				stateId = result.getInt(1);
			} else {
				logger.log(Level.FINE,
						"No results aquired... does that state abbreviation exist?");
			}

		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (result != null){
				DBConnection.closeResultSet(result);
			}
		}
		return stateId;
	}
	
	/**
	 * Assigns the admin to a certain location
	 * @param locationId
	 * @param adminId
	 * @param conn
	 * @return boolean
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static boolean assignAdmin(int locationId, int adminId, Connection conn) throws NamingException, SQLException{
		PreparedStatement statement = null;
		boolean completed = false;

		String employeeQuery = queries.getString("ASSIGN_ADMIN");

		try {
			logger.log(Level.FINE, "Preparing to execute employee query: ");
			logger.log(Level.FINE, "   " + employeeQuery);
			// updating admin in the table
			statement = conn.prepareStatement(employeeQuery);
			statement.setInt(1,locationId);
			statement.setInt(2,adminId);

			logger.log(Level.FINE,
					"Updating the admin based on the paramaters: ");
			logger.log(Level.FINE, "   Int:" + locationId);
			logger.log(Level.FINE, "   Int:" + adminId);
			// executing select statement
			statement.executeUpdate();

			// execute complete
			completed = true;
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
		}
		return completed;
	}
}
