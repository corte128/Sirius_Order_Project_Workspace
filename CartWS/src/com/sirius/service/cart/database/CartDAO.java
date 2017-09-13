package com.sirius.service.cart.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.cart.bean.OrderBean;

public class CartDAO {

	private static final Logger logger = Logger.getLogger(CartDAO.class.getName());
	
	/**
	 * sets the budget based on location
	 * @param budget
	 * @param locationId
	 * @return boolean
	 */
	public static boolean addProductToCart(OrderBean order, int createdBy){
		Connection conn = null;
		boolean completed = false;
		try{
			conn = DBConnection.getConnection();
			completed = CartDAOImplementation.addProductToCart(order, createdBy, conn);
		} catch(NamingException e){
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		} catch (Exception e){
			logger.log(Level.SEVERE,"Exception Found ", e);
		} finally{
			if (conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL Exception ", e);
				}
			}
		}
		
		return completed;
	}
}
