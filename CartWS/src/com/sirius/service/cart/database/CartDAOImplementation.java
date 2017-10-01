package com.sirius.service.cart.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.cart.bean.BudgetBean;
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
		order.setOrderName("cart");

		String orderQuery = queries.getString("ADD_PRODUCT_TO_ORDER");

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
	
	/**
	 * gets the most recent order id
	 * @param conn
	 * @return int
	 * @throws SQLException
	 */
	public static int getRecentOrderId(Connection conn) throws SQLException{
		PreparedStatement statement = null;
		int id = 0;
		ResultSet result = null;
		
		String orderQuery = queries.getString("GET_RECENT_ORDER_ID");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);

			// executing creation statement
			result = statement.executeQuery();
			
			// sifting through the result set and populating the order id
			if(result.next()){
				id = result.getInt("id");
			}

			logger.log(Level.FINE,"order id acquired");
		} finally {
			if (statement != null){
				DBConnection.closePreparedStatement(statement);
			}
			if(result != null){
				DBConnection.closeResultSet(result);
			}
		}
		
		return id;
	}
	
	/**
	 * adding order to budget
	 * @param conn
	 * @param budget
	 * @param createdBy
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean addOrderToBudget(Connection conn, BudgetBean budget, int createdBy) throws SQLException{
		PreparedStatement statement = null;
		boolean completed = false;

		String budgetQuery = queries.getString("ADD_ORDER_TO_BUDGET");
		java.sql.Date dateObj = new java.sql.Date(budget.getBudgetDate().getTime());
		try {
			logger.log(Level.FINE, "Preparing to execute budget query: ");
			logger.log(Level.FINE, "   " + budgetQuery);
			// setting budget to the table
			statement = conn.prepareStatement(budgetQuery);
			statement.setInt(1, budget.getLocationId());
			statement.setInt(2, budget.getOrderId());
			statement.setDate(3, dateObj);
			statement.setBigDecimal(4, budget.getBudgetAllotted());
			statement.setBigDecimal(5, budget.getBudgetRecommended());
			statement.setInt(6, createdBy);
			

			logger.log(Level.FINE,
					"Setting the budget based on the paramaters: ");
			logger.log(Level.FINE, "   BudgetBean: " + budget);
			logger.log(Level.FINE, "   Int: " + createdBy);
			// executing creation statement
			statement.executeUpdate();

			logger.log(Level.FINE, "Product added to budget complete");
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
	 * Gets all the products 
	 * @param orderName
	 * @param conn
	 * @return List<OrderBean>
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static List<OrderBean> getAllProductsInCart(int locationId,Connection conn)throws NamingException, SQLException{
		PreparedStatement statement = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		ResultSet results = null;
		String orderQuery = queries.getString("GET_ALL_PRODUCTS_IN_CART");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1, locationId);

			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);

			// executing creation statement
			results = statement.executeQuery();
			
			// sifting through the result set and populating the orders
			while(results.next()){
				OrderBean order = new OrderBean();
				
				order.setId(results.getInt("order_id_pk"));
				order.setOrderName(results.getString("order_name"));
				order.setProductId(results.getInt("product_id_fk"));
				order.setTotalPrice(results.getBigDecimal("total_price"));
				order.setQuantity(results.getInt("quantity"));
				orders.add(order);
				logger.log(Level.FINE,"acquired order #: " + order.getId());
			}

			logger.log(Level.FINE,"products acquired");
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return orders;
	}

	/**
	 * gets all the applicable ids for the cart
	 * @param locationId
	 * @param conn
	 * @return List<Integer>
	 * @throws SQLException
	 */
	public static List<Integer> getOrderIdsInCartByLocation(int locationId,
			Connection conn) throws SQLException {
		PreparedStatement statement = null;
		List<Integer> ids = new ArrayList<Integer>();
		ResultSet results = null;
		String orderQuery = queries.getString("GET_ORDER_IDS_IN_CART_BY_LOCATION");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// getting the ids from the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1, locationId);

			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);

			// executing select statement
			results = statement.executeQuery();
			
			// sifting through the result set and populating the ids
			while(results.next()){
				int id = 0;
				id = results.getInt("order_id_pk");
				ids.add(id);
			}

			logger.log(Level.FINE,"products acquired");
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return ids;
	}
	
	/**
	 * changes the quantity on a certain project 
	 * @param orderName
	 * @param productId
	 * @param quantity
	 * @param conn
	 * @return boolean
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static boolean updateProductQuantity(List<Integer> ids, int productId, int quantity,
			Connection conn) throws NamingException, SQLException{	
		PreparedStatement statement = null;
		boolean completed = false;
		StringBuilder sbObj = new StringBuilder();
		sbObj.append(queries.getString("UPDATE_PRODUCT_QUANTITY"));
		//dynamically creating query for execution
		for(Integer id : ids){
			sbObj.append(" OR order_id_pk = "+id);
		}
		sbObj.append(");");
		try {
			logger.log(Level.FINE, "Preparing to execute quantity query: ");
			logger.log(Level.FINE, "   " + sbObj.toString());
			
			// setting budget to the table
			statement = conn.prepareStatement(sbObj.toString());
			statement.setInt(1, quantity);
			statement.setInt(2, productId);

			logger.log(Level.FINE,
					"Setting the quantity based on the paramaters: ");
			logger.log(Level.FINE, "   List<Integer>: " + ids);
			logger.log(Level.FINE, "   Int: " + productId);
			logger.log(Level.FINE, "   Int: " + quantity);
			// executing creation statement
			statement.executeUpdate();

			logger.log(Level.FINE, "Product quantity updated complete");
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
	 * removes the product from the cart
	 * @param orderId
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean removeProductFromCart(int orderId, int updatedBy, Connection conn) throws SQLException {
		PreparedStatement statement = null;
		boolean completed = false;
		
		String orderQuery = queries.getString("REMOVE_PRODUCT_FROM_CART");
		
		try {
			logger.log(Level.FINE, "Preparing to execute quantity query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1,updatedBy);
			statement.setInt(2,orderId);
			

			logger.log(Level.FINE,
					"Setting the is_valid based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + orderId);

			// executing creation statement
			statement.executeUpdate();

			logger.log(Level.FINE, "order updated complete");
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
	 * Gets all the orders that are in the cart based on location
	 * @param locationId
	 * @param conn
	 * @return List<OrderBean>
	 * @throws SQLException
	 */
	public static List<OrderBean> getOrdersInCartByLocation(List<Integer> productIdList, int locationId,
			Connection conn) throws SQLException {
		PreparedStatement statement = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		ResultSet results = null;
		StringBuilder orderQuery = new StringBuilder();
		orderQuery.append(queries.getString("GET_ORDERS_IN_CART_BY_LOCATION"));
		
		for(int productId : productIdList)
		{
			orderQuery.append(" OR product_id_fk = " + productId); 
		}
		orderQuery.append(");");
		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// getting the ids from the table
			statement = conn.prepareStatement(orderQuery.toString());
			statement.setInt(1, locationId);
			
			
			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);

			// executing select statement
			results = statement.executeQuery();
			
			// sifting through the result set and populating the ids
			while(results.next()){
				OrderBean order = new OrderBean();
				order.setId(results.getInt("order_id_pk"));
				order.setOrderName(results.getString("order_name"));
				order.setProductId(results.getInt("product_id_fk"));
				order.setQuantity(results.getInt("quantity"));
				order.setTotalPrice(results.getBigDecimal("total_price"));
				orders.add(order);
			}

			logger.log(Level.FINE,"products acquired");
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return orders;
	}
	
	/**
	 * save order with an orderName
	 * @param ids
	 * @param orderName
	 * @param conn
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean saveOrder(List<OrderBean> orders, String orderName, int createdBy,
			Connection conn) throws SQLException, NamingException {
		PreparedStatement statement = null;
		boolean completed = false;
		String orderQuery = queries.getString("SAVE_ORDER");
		
		for(OrderBean order : orders){
			try {
				logger.log(Level.FINE, "Preparing to execute order query: ");
				logger.log(Level.FINE, "   " + orderQuery);
				// setting order to the table
				statement = conn.prepareStatement(orderQuery);
				statement.setString(1, orderName);
				statement.setInt(2, order.getProductId());
				statement.setBigDecimal(3, order.getTotalPrice());
				statement.setInt(4, order.getQuantity());
				statement.setInt(5, createdBy);
				
				logger.log(Level.FINE,
						"Setting the order based on the paramaters: ");
				logger.log(Level.FINE, "   List<OrderBean>: " + orders);
				logger.log(Level.FINE, "   String: " + orderName);
				logger.log(Level.FINE, "   String: " + createdBy);
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
		}
		return completed;
	}

	/**
	 * removes the orders by the order name
	 * @param orderName
	 * @param updatedBy
	 * @param conn
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean removeOrdersByOrderName(String orderName, int updatedBy,
			Connection conn) throws SQLException {
		PreparedStatement statement = null;
		boolean completed = false;
		
		String orderQuery = queries.getString("REMOVE_PRODUCT_FROM_CART_BY_ORDER_NAME");
		
		try {
			logger.log(Level.FINE, "Preparing to execute quantity query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1,updatedBy);
			statement.setString(2,orderName);
			
			logger.log(Level.FINE,
					"Setting the is_valid based on the paramaters: ");
			logger.log(Level.FINE, "   String: " + orderName);

			// executing creation statement
			statement.executeUpdate();

			logger.log(Level.FINE, "order updated complete");
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
	 * gets all the orders based on the location and the orderName (works only for saved orders)
	 * @param orderName
	 * @param locationId
	 * @param conn
	 * @return List<OrderBean>
	 * @throws SQLException
	 */
	public static List<OrderBean> getOrderByOrderName(String orderName, int locationId,
			Connection conn) throws SQLException {
		PreparedStatement statement = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		ResultSet results = null;
		String orderQuery = queries.getString("GET_ORDER_BY_ORDER_NAME");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1, locationId);
			statement.setString(2, orderName);

			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);
			logger.log(Level.FINE, "   String: " + orderName);

			// executing creation statement
			results = statement.executeQuery();
			
			// sifting through the result set and populating the orders
			while(results.next()){
				OrderBean order = new OrderBean();
				
				order.setId(results.getInt("order_id_pk"));
				order.setOrderName(results.getString("order_name"));
				order.setProductId(results.getInt("product_id_fk"));
				order.setTotalPrice(results.getBigDecimal("total_price"));
				order.setQuantity(results.getInt("quantity"));
				orders.add(order);
				logger.log(Level.FINE,"acquired order #: " + order.getId());
			}

			logger.log(Level.FINE,"products acquired");
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return orders;
	}

	/**
	 * adds multiple budget records based on the recently posted order
	 * @param conn
	 * @param budget
	 * @param latestRecord
	 * @param size
	 * @param createdBy
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean addMultipleOrdersToBudget(Connection conn,
			BudgetBean budget, int latestRecord, int size, int createdBy) throws SQLException {
		PreparedStatement statement = null;
		boolean completed = false;

		String budgetQuery = queries.getString("ADD_ORDER_TO_BUDGET");
		java.sql.Date dateObj = new java.sql.Date(budget.getBudgetDate().getTime());
		for(int index = latestRecord; index>(latestRecord-size); index--){
			try {
				logger.log(Level.FINE, "Preparing to execute budget query: ");
				logger.log(Level.FINE, "   " + budgetQuery);
				// setting budget to the table
				statement = conn.prepareStatement(budgetQuery);
				statement.setInt(1, budget.getLocationId());
				statement.setInt(2, index);
				statement.setDate(3, dateObj);
				statement.setBigDecimal(4, budget.getBudgetAllotted());
				statement.setBigDecimal(5, budget.getBudgetRecommended());
				statement.setInt(6, createdBy);
				
	
				logger.log(Level.FINE,
						"Setting the budget based on the paramaters: ");
				logger.log(Level.FINE, "   BudgetBean: " + budget);
				logger.log(Level.FINE, "   Int: " + createdBy);
				// executing creation statement
				statement.executeUpdate();
	
				logger.log(Level.FINE, "Product added to budget complete");
				// setting completion
				completed = true;
			} finally {
				if (statement != null) {
					DBConnection.closePreparedStatement(statement);
				}
			}
		}
		return completed;
	}
	
	/**
	 * Gets all the orders that are in the cart based on location and product type
	 * @param locationId
	 * @param productType
	 * @param conn
	 * @return List<OrderBean>
	 * @throws SQLException
	 */
	public static List<OrderBean> getAllProductsInCartByProductType(int locationId, String productType, Connection conn)throws NamingException, SQLException
	{
		PreparedStatement statement = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		ResultSet results = null;
		String orderQuery = queries.getString("GET_ALL_PRODUCTS_IN_CART_BY_TYPE");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1, locationId);
			statement.setString(2, productType);
			
			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);

			// executing creation statement
			results = statement.executeQuery();
			
			// sifting through the result set and populating the orders
			while(results.next()){
				OrderBean order = new OrderBean();
				
				order.setId(results.getInt("order_id_pk"));
				order.setOrderName(results.getString("order_name"));
				order.setProductId(results.getInt("product_id_fk"));
				order.setTotalPrice(results.getBigDecimal("total_price"));
				order.setQuantity(results.getInt("quantity"));
				orders.add(order);
				logger.log(Level.FINE,"acquired order #: " + order.getId());
			}

			logger.log(Level.FINE,"products acquired");
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
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
	public static int getProductQuantityInCartByProductId(int locationId, int productId, Connection conn) throws NamingException, SQLException
	{
		PreparedStatement statement = null;
//		List<OrderBean> orders = new ArrayList<OrderBean>();
		ResultSet results = null;
		String orderQuery = queries.getString("GET_PRODUCT_IN_CART_BY_ID");

		try 
		{
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1, locationId);
			statement.setInt(2, productId);
			
			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);

			// executing creation statement
			results = statement.executeQuery();
			
			int output = 0;
			// sifting through the result set and populating the orders
			if(results.next())
			{
				output = results.getInt("quantity");
			}
			return output;
			

		}
		finally 
		{
			if (statement != null) 
			{
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null)
			{
				DBConnection.closeResultSet(results);
			}
		}
	}
	
	/**
	 * Gets all the saved orders
	 * @param locationId
	 * @param conn
	 * @return List<OrderBean>
	 * @throws SQLException
	 */
	public static List<OrderBean> getAllSavedOrders(int locationId, Connection conn) throws SQLException{
		//GET_SAVED_ORDERS
		String orderQuery = queries.getString("GET_SAVED_ORDERS");
		PreparedStatement statement = null;
		List<OrderBean> orderList = new ArrayList<OrderBean>();

		ResultSet results = null;
		
		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			try {
				statement = conn.prepareStatement(orderQuery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement.setInt(1, locationId);
			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);
			// executing creation statement
			results = statement.executeQuery();
			
			while(results.next()){
				OrderBean order = new OrderBean();
				order.setId(results.getInt("order_id_pk"));
				order.setOrderName(results.getString("order_name"));
				order.setProductId(results.getInt("product_id_fk"));
				order.setTotalPrice(results.getBigDecimal("total_price"));
				order.setQuantity(results.getInt("quantity"));
				orderList.add(order);
			}
			logger.log(Level.FINE,"products acquired");
		}
		finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return orderList;
		
	}
	
	/**
	 * gets the most recent budget by location
	 * @param locationId
	 * @param conn
	 * @return BudgetBean
	 * @throws SQLException
	 */
	public static BudgetBean getMostRecentBudgetByLocation(int locationId, Connection conn) throws SQLException
	{
		String query = queries.getString("GET_SAVED_ORDERS");
		PreparedStatement statement = null;
		BudgetBean budget = new BudgetBean();
		Date x = null;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		
		ResultSet results = null;
		
		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + query);
			//setting budget to the table
			try {
				statement = conn.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement.setInt(1, locationId);
			statement.setDate(2, x, cal);
			logger.log(Level.FINE,
					"Setting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);
			// executing creation statement
			results = statement.executeQuery();
			
			if(results.next())
			{
				budget.setId(results.getInt("budget_id_pk"));
				budget.setOrderId(results.getInt("order_id_fk"));
				budget.setLocationId(results.getInt("location_id_fk"));
				budget.setBudgetDate(results.getDate("budget_date"));
				budget.setBudgetAllotted(results.getBigDecimal("budget_allotted"));
				budget.setBudgetRecommended(results.getBigDecimal("budget_recommended"));
			}
			logger.log(Level.FINE,"products acquired");
		}
		finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return budget;
	}

	/**
	 * Get all orders by product type even if they aren't in the cart
	 * @param locationId
	 * @param productTypeId
	 * @param conn
	 * @return List<OrderBean>
	 * @throws SQLException 
	 */
	public static List<OrderBean> getAllOrdersByProdutType(int locationId,
			int productTypeId, Connection conn) throws SQLException {
		PreparedStatement statement = null;
		List<OrderBean> orders = new ArrayList<OrderBean>();
		ResultSet results = null;
		String orderQuery = queries.getString("GET_ALL_PRODUCTS_BY_TYPE");

		try {
			logger.log(Level.FINE, "Preparing to execute order query: ");
			logger.log(Level.FINE, "   " + orderQuery);
			// setting budget to the table
			statement = conn.prepareStatement(orderQuery);
			statement.setInt(1, locationId);
			statement.setInt(2, productTypeId);
			
			logger.log(Level.FINE,
					"Getting the order based on the paramaters: ");
			logger.log(Level.FINE, "   int: " + locationId);
			logger.log(Level.FINE, "   int: " + productTypeId);

			// executing creation statement
			results = statement.executeQuery();
			
			// sifting through the result set and populating the orders
			while(results.next()){
				OrderBean order = new OrderBean();
				
				order.setId(results.getInt("order_id_pk"));
				order.setOrderName(results.getString("order_name"));
				order.setProductId(results.getInt("product_id_fk"));
				order.setTotalPrice(results.getBigDecimal("total_price"));
				order.setQuantity(results.getInt("quantity"));
				
				orders.add(order);
				logger.log(Level.FINE,"acquired order #: " + order.getId());
			}

			logger.log(Level.FINE,"products acquired");
		} finally {
			if (statement != null) {
				DBConnection.closePreparedStatement(statement);
			}
			if (results != null){
				DBConnection.closeResultSet(results);
			}
		}
		
		return orders;
	}
}
