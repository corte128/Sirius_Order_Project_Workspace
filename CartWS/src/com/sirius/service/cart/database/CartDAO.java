package com.sirius.service.cart.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.cart.bean.BudgetBean;
import com.sirius.service.cart.bean.OrderBean;

public class CartDAO {
	private static final Logger logger = Logger.getLogger(CartDAO.class.getName());
	
	/**
	 * sets the budget based on location
	 * @param budget
	 * @param locationId
	 * @return boolean
	 */
	public static boolean addProductToCart(OrderBean order, BudgetBean budget, int createdBy){
		Connection conn = null;
		boolean orderCompleted = false;
		boolean budgetCompleted = false;
		boolean completed = false;
		
		try{
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			//setting order
			orderCompleted = CartDAOImplementation.addProductToCart(order, createdBy, conn);
			//getting recent order id
			int orderId = CartDAOImplementation.getRecentOrderId(conn);
			budget.setOrderId(orderId);
			//setting budget
			budgetCompleted = CartDAOImplementation.addOrderToBudget(conn, budget,createdBy);
			
			conn.commit();
			
		}catch(NamingException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
			}
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Exception Found ", e);
			}
		} finally{
			if (conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL Exception ", e);
				}
			}
		}
		
		logger.log(Level.FINE,"order completed: " + orderCompleted);
		logger.log(Level.FINE,"budget completed: " + budgetCompleted);
		if(budgetCompleted && orderCompleted){
			completed = true;
		}
		
		return completed;
	}
	
	/**
	 * gets all the order and product records based on the order name
	 * @param orderName
	 * @return List<OrderBean>
	 */
	public static List<OrderBean> getAllProductsInCart(int locationId){
		Connection conn = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		
		try{
			conn = DBConnection.getConnection();
			orders = CartDAOImplementation.getAllProductsInCart(locationId, conn);
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
		
		return orders;
	}

	/**
	 * updates the product quantity
	 * @param orderName
	 * @param productId
	 * @param quantity
	 * @return boolean
	 */
	public static boolean updateProductQuantity(int locationId, int productId, int quantity, int updatedBy) {
		Connection conn = null;
		List<Integer> ids = new ArrayList<Integer>();
		boolean completed = false;
		
		try{
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			//getting ids for all the valid orders where the quantity can be changed
			ids = CartDAOImplementation.getOrderIdsInCartByLocation(locationId,conn);
			//updating the product quantity
			completed = CartDAOImplementation.updateProductQuantity(ids, productId, quantity, conn);
		
			conn.commit();
		} catch(NamingException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
			}
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Exception Found ", e);
			}
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

	
	/**
	 * removes the product from the cart
	 * @param orderId
	 * @return boolean
	 */
	public static boolean removeProductFromCart(int orderId) {
		Connection conn = null;
		boolean completed = false;
		
		try{
			conn = DBConnection.getConnection();
			//remove the product
			completed = CartDAOImplementation.removeProductFromCart(orderId,conn);
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


	/**
	 * save cart
	 * @param orderName
	 * @return boolean
	 */
	public static boolean saveOrder(String orderName, int locationId, int createdBy) {
		Connection conn = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		boolean completed = false;
		
		try{
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			//getting ids for all the valid orders where the quantity can be changed
			orders = CartDAOImplementation.getOrdersInCartByLocation(locationId,conn);
			//updating the product quantity
			completed = CartDAOImplementation.saveOrder(orders, orderName, createdBy, conn);
		
			conn.commit();
		} catch(NamingException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
			}
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Exception Found ", e);
			}
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
