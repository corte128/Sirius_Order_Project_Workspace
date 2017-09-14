package com.sirius.service.cart.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.cart.bean.OrderBean;

public class CartDAOImplementation {
	private static final Logger logger = Logger.getLogger(CartDAO.class.getName());
	private static final ResourceBundle queries = ResourceBundle
			.getBundle("com.sirius.service.properties.queries");
	
	/**
	 * adding product to the cart 
	 * @param order
	 * @param createdBy
	 * @param conn
	 * @return boolean
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static boolean addProductToCart(OrderBean order, int createdBy, Connection conn) throws NamingException, SQLException {
		PreparedStatement statement = null;
		boolean completed = false;

		String orderQuery = queries.getString("ADD_PRODUCT_TO_CART");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setString(1, order.getOrderName());
			statement.setInt(2, order.getProductId());
			statement.setBigDecimal(3, order.getTotalPrice());
			statement.setInt(4, order.getQuantity());
			statement.setInt(5, createdBy);
			

			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   OrderBean: " + order);
			logger.log(Level.FINE, "   Int: " + createdBy);
			// executing creation statement
			statement.executeUpdate();

			logger.log(Level.FINE, "Product added to order complete");
			// setting completion
			completed = true;
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
		}
		return completed;
	}
}
