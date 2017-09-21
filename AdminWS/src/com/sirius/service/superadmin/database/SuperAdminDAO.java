package com.sirius.service.superadmin.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.adminws.beans.OfficeBean;


public class SuperAdminDAO {
	private static final Logger logger = Logger.getLogger(SuperAdminDAO.class.getName());
	
	/**
	 * sets the budget based on location
	 * @param budget
	 * @param locationId
	 * @return boolean
	 */
	public static boolean setBudgetByLocation(BigDecimal budget, int locationId){
		Connection conn = null;
		boolean completed = false;
		try{
			conn = DBConnection.getConnection();
			completed = SuperAdminDAOImplementation.setBudgetByLocation(budget, locationId, conn);
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
	 * gets the budget based on location
	 * @param locationId
	 * @return BigDecimal
	 */
	public static BigDecimal getBudgetByLocation(int locationId){
		Connection conn = null;
		BigDecimal budget = null;
		try{
			conn = DBConnection.getConnection();
			budget = SuperAdminDAOImplementation.getBudgetByLocation(locationId, conn);
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
		
		return budget;
	}

	/**
	 * adds the location by getting the stated Id and setting the stateId and city
	 * @param city
	 * @param state
	 * @return boolean
	 */
	public static boolean addLocation(String city, String state, int creatorId) {
		Connection conn = null;
		boolean completed = false;
		
		try{
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			int stateId = SuperAdminDAOImplementation.getStateId(state, conn);
			completed = SuperAdminDAOImplementation.addLocation(city, stateId, creatorId, conn);
			
			conn.commit();
			completed = true;
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
	 * Assigns the admin to a certain location
	 * @param locationId
	 * @param adminId
	 * @return boolean
	 */
	public static boolean assignAdmin(int locationId, int adminId, int updaterId) {
		Connection conn = null;
		boolean completed = false;
		try{
			conn = DBConnection.getConnection();
			completed = SuperAdminDAOImplementation.assignAdmin(locationId, adminId, updaterId, conn);
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
	 * Gets all the offices 
	 * @return List<OfficeBean>
	 */
	public static List<OfficeBean> getOffices() {
		Connection conn = null;
		List<OfficeBean> offices = new ArrayList<OfficeBean>();
		
		try{
			conn = DBConnection.getConnection();
			offices = SuperAdminDAOImplementation.getOffices(conn);
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
		return offices;
	}

	/**
	 * Gets the employee id based on the name 
	 * @param name
	 * @return int
	 */
	public static int getEmployeeIdByName(String name) {
		Connection conn = null;
		int id = 0;
		
		try{
			conn = DBConnection.getConnection();
			id = SuperAdminDAOImplementation.getEmployeeIdByName(name,conn);
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
		return id;
	}

	/**
	 * Gets the office admin names
	 * @return List<String>
	 */
	public static List<String> getOfficeAdminNames() {
		Connection conn = null;
		List<String> names = new ArrayList<String>();
		
		try{
			conn = DBConnection.getConnection();
			names = SuperAdminDAOImplementation.getOfficeAdminNames(conn);
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
		return names;
	}
}
