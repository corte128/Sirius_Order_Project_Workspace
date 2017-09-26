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
	public static boolean removeProductFromCart(int orderId, int updatedBy) {
		Connection conn = null;
		boolean completed = false;
		
		try{
			conn = DBConnection.getConnection();
			//remove the product
			completed = CartDAOImplementation.removeProductFromCart(orderId, updatedBy, conn);
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
	 * Saves the cart to an order
	 * @param budget
	 * @param orderName
	 * @param locationId
	 * @param createdBy
	 * @return boolean
	 */
	public static boolean saveOrder(String orderName, BudgetBean budget, int locationId, int createdBy) {
		Connection conn = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		int latestRecord = 0;
		boolean completed = false;
		boolean removed = false;
		boolean budgetCompleted = false;
		
		try{
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			//getting all the valid orders where the quantity can be changed
			orders = CartDAOImplementation.getOrdersInCartByLocation(locationId,conn);
			//removing previous order if it exists
			removed = CartDAOImplementation.removeOrdersByOrderName(orderName,createdBy,conn);
			//updating the product quantity
			completed = CartDAOImplementation.saveOrder(orders, orderName, createdBy, conn);
			//getting the max id for the valid order
			latestRecord = CartDAOImplementation.getRecentOrderId(conn);
			//setting budget
			budgetCompleted = CartDAOImplementation.addMultipleOrdersToBudget(conn,budget,latestRecord,orders.size(),createdBy);
			
			
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
		
		if(removed)
			logger.log(Level.FINE,"UPDATE previous order");
		
		if(budgetCompleted)
			logger.log(Level.FINE,"Budget NOT completed");
		
		return completed;
	}
	
	/**
	 * Gets all of the orders based on the location and orderName
	 * @param orderName
	 * @param locationId
	 * @param createdBy
	 * @return List<OrderBean>
	 */
	public static List<OrderBean> getOrderByOrderName(String orderName, int locationId) {
		Connection conn = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		
		try{
			conn = DBConnection.getConnection();
			orders = CartDAOImplementation.getOrderByOrderName(orderName,locationId, conn);
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
	public static List<OrderBean> getAllProductsInCartByProductType(int locationId, String productType)
	{
		Connection conn = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		
		try
		{
			conn = DBConnection.getConnection();
			orders = CartDAOImplementation.getAllProductsInCartByProductType(locationId, productType, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					DBConnection.closeConnection(conn);
				}
				catch(SQLException e)
				{
					logger.log(Level.SEVERE,"SQL Exception ", e);
				}
			}
		}
		return orders;
	}
	
	/**
	 * checks if a product with the given product id is in the cart
	 * returns true if product is in cart
	 * @param locationId
	 * @param productId
	 * @return boolean
	 */
	public static int getProductQuantityInCartByProductId(int locationId, int productId)
	{
		Connection conn = null;
		int output = 0;
		
		try
		{
			conn = DBConnection.getConnection();
			output = CartDAOImplementation.getProductQuantityInCartByProductId(locationId, productId, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					DBConnection.closeConnection(conn);
				}
				catch(SQLException e)
				{
					logger.log(Level.SEVERE,"SQL Exception ", e);
				}
			}
		}
		return output;
	}
	
	
	public static List<OrderBean> getAllSavedOrders(int locationId) 
	{
		Connection conn = null;
		List<OrderBean> output = null;
		
		try
		{
			conn = DBConnection.getConnection();
			output = CartDAOImplementation.getAllSavedOrders(locationId, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					DBConnection.closeConnection(conn);
				}
				catch(SQLException e)
				{
					logger.log(Level.SEVERE,"SQL Exception ", e);
				}
			}
		}
		return output;
	}
	
}
