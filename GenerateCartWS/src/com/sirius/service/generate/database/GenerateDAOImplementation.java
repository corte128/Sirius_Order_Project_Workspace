package com.sirius.service.generate.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
					"Getting the visitors based on the paramaters: ");
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
	
	/**
	 * gets the prices, likes, and products as array lists
	 * @param conn
	 * @param locationId
	 * @return List<List<Integer>>
	 * @throws SQLException
	 */
	public static List<List<Integer>> getProductsLikesPrices (Connection conn, int locationId) throws SQLException{
		List<List<Integer>> results = new ArrayList<List<Integer>>(3);
		List<Integer> products = new ArrayList<Integer>();
		List<Integer> likes = new ArrayList<Integer>();
		List<Integer> prices = new ArrayList<Integer>();
		
		PreparedStatement statement = null;
		ResultSet resultsFromQuery = null;

		String visitorQuery = queries.getString("GET_PRODUCTS_LIKES_PRICE");

		try {
			logger.log(Level.FINE, "Preparing to execute query: ");
			logger.log(Level.FINE, "   " + visitorQuery);
			// getting budget from the table
			statement = conn.prepareStatement(visitorQuery);
			statement.setInt(1, locationId);


			logger.log(Level.FINE,
					"Getting the price, products, and likes based on the paramaters: ");
			logger.log(Level.FINE, "   Int: " + locationId);

			// executing select statement
			resultsFromQuery = statement.executeQuery();

			while(resultsFromQuery.next()){
				products.add(resultsFromQuery.getInt("product_id_pk"));
				likes.add(resultsFromQuery.getInt("likes"));
				prices.add(resultsFromQuery.getInt("product_price"));
			}
			
			results.add(products);
			results.add(likes);
			results.add(prices);

		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(resultsFromQuery);
			}
		}

		return results;
	}

	/**
	 * Clear
	 * @param conn
	 * @param id
	 * @throws SQLException 
	 */
	public static void clearCart(Connection conn) throws SQLException {
		PreparedStatement statement = null;

		String orderQuery = queries.getString("CLEAR_CART");

		try {
			logger.log(Level.FINE, "Preparing to execute query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// getting budget from the table
			statement = conn.prepareStatement(orderQuery);

			// executing select statement
			statement.executeUpdate();

		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
		}
	}
}
