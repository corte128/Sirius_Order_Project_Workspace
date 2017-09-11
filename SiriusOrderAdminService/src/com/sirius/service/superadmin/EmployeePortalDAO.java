package com.sirius.service.superadmin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.back.beans.EmployeeBean;
import com.sirius.back.database.DBConnection;

public class EmployeePortalDAO {
	private static final Logger logger = Logger.getLogger(ProfileHandler.class.getName());
	
	EmployeePortalDAO(){
		
	}
	
	/**
	 * creates an employee and a contact
	 * @param employee
	 * @return true if complete, false if incomplete
	 */
	public static boolean CreateEmployee(EmployeeBean employee) {
		boolean result = false;
		int id = 0;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in createEmployee");
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			logger.log(Level.FINE,"Adding employee");
			id = EmployeePortalDAOImplementation.CreateEmployee(conn,employee);
		
			logger.log(Level.FINE,"Adding contact");
			result = EmployeePortalDAOImplementation.CreateContact(conn, employee, id);
			conn.commit();
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		return result;
	}
	
	/**
	 * gets employee by id
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public static int getEmployeeId(String username){
		int result = 0;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in getEmployeeId");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting employees in getEmployeeId");
			result = EmployeePortalDAOImplementation.getEmployeeId(conn, username);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return result;
	}

	/**
	 * get employee based on there role
	 * @param role
	 * @return EmployeeBean[]
	 */
	public static EmployeeBean[] getSpecificEmployees(String role) {
		EmployeeBean results[] = null;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in createEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting employees in specific employees");
			results = EmployeePortalDAOImplementation.getSpecificEmployees(conn, role);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return results;
	}

	/**
	 * gets all employees 
	 * @return EmployeeBean[]
	 */
	public static EmployeeBean[] getAllEmployees() {
		EmployeeBean results[] = null;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in allEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting all employees in allEmployees");
			results = EmployeePortalDAOImplementation.getAllEmployees(conn);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return results;
	}

	/**
	 * gets a specific employee by id
	 * @param id
	 * @return EmployeeBean
	 */
	public static EmployeeBean getEmployeeById(int id) {
		EmployeeBean result = null;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in allEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting all employees in allEmployees");
			result = EmployeePortalDAOImplementation.getEmployeeById(conn,id);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param role
	 * @return
	 */
	public static EmployeeBean[] getEmployeeByProperties(String firstName, String lastName, String email, String role){
		EmployeeBean results[] = null;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in allEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting all employees in allEmployees");
			results = EmployeePortalDAOImplementation.getEmployeeByProperties(conn,firstName,lastName,email,role);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Updates an employees information
	 * @param employee
	 * @return boolean
	 */
	public static boolean updateEmployee(EmployeeBean employee) {
		boolean result = false;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in allEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting all employees in allEmployees");
			result = EmployeePortalDAOImplementation.updateEmployee(conn, employee);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Updates an employees manager who they are assigned to
	 * @param id
	 * @param managerId
	 * @return boolean
	 */
	public static boolean updateEmployeeManager(int id, int managerId) {
		boolean result = false;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in allEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"getting all employees in allEmployees");
			result = EmployeePortalDAOImplementation.updateEmployeeManager(conn,id,managerId);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return result;
	}

	/**
	 * deletes an employee and its contact based on id
	 * @param id
	 * @return boolean
	 */
	public static boolean deleteEmployeeById(int id) {
		Boolean result = false;
		Connection conn = null;
		
		try {
			logger.log(Level.FINE,"Establishing a connection in allEmployee");
			conn = DBConnection.getConnection();
		
			logger.log(Level.FINE,"deleting contact");
			result = EmployeePortalDAOImplementation.deleteContactByEmployeeId(conn, id);
			
			logger.log(Level.FINE,"deleting employee");
			if(result != false)
				result = EmployeePortalDAOImplementation.deleteEmployeeById(conn, id);
		} catch (NamingException e) {
			logger.log(Level.SEVERE, "Naming Exception found!", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception found!", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception found!", e);
		}finally{
			if(conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception found!", e);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Exception found!", e);
				}
			}
		}
		
		return result;
	}
	
}