package com.sirius.service.generate.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateDAOImplementation {
	private static final Logger logger = Logger
			.getLogger(GenerateDAOImplementation.class.getName());
	private static final ResourceBundle queries = ResourceBundle
			.getBundle("com.sirius.service.properties.queries");
	
	public static int getNumberOfVisitors(Connection conn, int locationId, String startDate, String endDate) throws SQLException{
		int numberOfVisitors = 0;
		PreparedStatement statement = null;
		ResultSet results = null;

		String visitorQuery = queries.getString("GET_VISTORS_BY_DATE_RANGE");

		try {
			logger.log(Level.FINE, "Preparing to execute visitor query: ");
			logger.log(Level.FINE, "   " + visitorQuery);
			// getting budget from the table
			statement = conn.prepareStatement(visitorQuery);
			statement.setInt(1, locationId);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
			statement.setString(4, startDate);
			statement.setString(5, endDate);

			logger.log(Level.FINE,
					"Getting the budget based on the paramaters: ");
			logger.log(Level.FINE, "   Int: " + locationId);
			logger.log(Level.FINE, "   String: " + startDate);
			logger.log(Level.FINE, "   String: " + endDate);
			// executing select statement
			results = statement.executeQuery();

			while(results.next()){
				numberOfVisitors += results.getInt("number_of_visitors");
			}

		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return numberOfVisitors;
	}
}
