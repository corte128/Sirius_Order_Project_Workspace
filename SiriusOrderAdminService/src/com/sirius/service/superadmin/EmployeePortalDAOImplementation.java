package com.sirius.service.superadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.back.beans.ContactBean;
import com.sirius.back.beans.EmployeeBean;
import com.sirius.back.database.DBConnection;

public class EmployeePortalDAOImplementation {
	private static final Logger logger = Logger.getLogger(ProfileHandler.class.getName());
	private static final ResourceBundle queries = ResourceBundle.getBundle("com.sirius.back.properties.queries");
	
	public EmployeePortalDAOImplementation(){
		
	}
	
	/**
	 * adds employee to the database
	 * @param conn
	 * @param employee
	 * @return int 
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static int CreateEmployee(Connection conn, EmployeeBean employee) throws NamingException, SQLException {
		int employeeId = 0;
		PreparedStatement statement = null;
		PreparedStatement idPrepareStatement = null;
		ResultSet ids = null;
		
		String employeeQuery = queries.getString("ADD_EMPLOYEE_PREPARED_QUERY");
		String employeeIdQuery = queries.getString("GET_EMPLOYEE_ID_PREPARED_QUERY");
		
		try {
			logger.log(Level.FINE,"Preparing the employee statment for execution, adding employee");
			//adding employee to the table
			statement = conn.prepareStatement(employeeQuery);
			statement.setString(1, employee.getUsername());
			statement.setString(2, employee.getPassword());
			statement.setInt(3, employee.getAge());
			statement.setString(4, employee.getFirstName());
			statement.setString(5, employee.getLastName());
			statement.setString(6, employee.getRole());
			statement.setInt(7, employee.getManagerId());
			statement.setBytes(8, employee.getPicture());
			
			logger.log(Level.FINE,"Adding new employee");
			statement.executeUpdate();
			
			//getting ID of employee that was just added
			logger.log(Level.FINE,"Getting ID for the contact table");
			idPrepareStatement = conn.prepareStatement(employeeIdQuery);
			idPrepareStatement.setString(1, employee.getUsername());
			ids = idPrepareStatement.executeQuery();
			if(ids.next())
				employeeId =  ids.getInt("Id_PK");
			
			logger.log(Level.FINE,"Add complete");
		}finally{
			if(ids != null){
				DBConnection.closeResultSet(ids);
			}
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
			if(idPrepareStatement != null){
				DBConnection.closeStatement(idPrepareStatement);
			}
		}
		
		return employeeId;
	}

	/**
	 * adds contact to the database
	 * @param conn
	 * @param employee
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean CreateContact(Connection conn, EmployeeBean employee, int id) throws SQLException{
		PreparedStatement statement = null;
		boolean operationCompleted = false;

		String contactQuery = queries.getString("ADD_CONTACT_PREPARED_QUERY");
		
		try {
			//adding contact to the table
			logger.log(Level.FINE,"Preparing the contact statement for execution");
			statement = conn.prepareStatement(contactQuery);
			statement.setString(1, employee.getContact().getPhone());
			statement.setString(2, employee.getContact().getEmail());
			statement.setString(3, employee.getContact().getType());
			statement.setInt(4, id);
			
			logger.log(Level.FINE,"Adding new contact");
			statement.executeUpdate();
			operationCompleted = true;
		}finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		
		return operationCompleted;
	}
	
	/**
	 * gets employee by id
	 * @param conn
	 * @param username
	 * @return EmployeeBean
	 * @throws SQLException
	 */
	public static int getEmployeeId(Connection conn, String username) throws SQLException{
		PreparedStatement statement = null;
		ResultSet results = null;
		int id=0;
		
		String getIdQuery = queries.getString("GET_EMPLOYEE_ID_PREPARED_QUERY");
		
		try {
			logger.log(Level.FINE,"Preparing the employee statment for execution");
			//adding employee to the table
			statement = conn.prepareStatement(getIdQuery);
			statement.setString(1, username);
			
			logger.log(Level.FINE,"Adding new employee");
			results = statement.executeQuery();
			if(results.next())
				id = results.getInt("Id_PK");
			results.close();
			statement.close();
			
			
		} finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
			
		}
		
		return id;
	}
	
	/**
	 * gets employees based on the defined role
	 * @param conn
	 * @param role
	 * @return EmployeeBean[]
	 * @throws SQLException
	 */
	public static EmployeeBean[] getSpecificEmployees(Connection conn, String role) throws SQLException {
		PreparedStatement statement = null;
		ResultSet results = null;
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		
		String getAllSpecificQuery = queries.getString("GET_ALL_EMPLOYEES_BY_ROLE_PREPARED_QUERY");
		
		try {
			logger.log(Level.FINE,"Preparing the employee statment for execution");
			//adding employee to the table
			statement = conn.prepareStatement(getAllSpecificQuery);
			statement.setString(1, role);
			
			logger.log(Level.FINE,"Adding new employee");
			results = statement.executeQuery();
			while(results.next()){
				EmployeeBean employee = new EmployeeBean();
				ContactBean contact = new ContactBean();
				
				contact.setPhone(results.getString("Phone"));
				contact.setEmail(results.getString("Email"));
				contact.setType(results.getString("Type"));
				
				employee.setId(results.getInt("Id_PK"));
				employee.setManagerId(results.getInt("ManagerId"));
				employee.setAge(results.getInt("Age"));
				employee.setUsername(results.getString("username"));
				employee.setPassword(results.getString("password"));
				employee.setFirstName(results.getString("FirstName"));
				employee.setLastName(results.getString("LastName"));
				employee.setRole(results.getString("Role"));
				employee.setPicture(results.getBytes("Picture"));
				employee.setContact(contact);
				employees.add(employee);
				
			}
			results.close();
			statement.close();
			
		} catch (SQLException e) {
			throw e;
		} finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
	
		EmployeeBean employeeArray[] = employees.toArray(new EmployeeBean[employees.size()]);
		return employeeArray;
	}

	/**
	 * gets all of the employees from the table
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static EmployeeBean[] getAllEmployees(Connection conn) throws SQLException {
		PreparedStatement statement = null;
		ResultSet results = null;
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		
		String getAllEmployeesQuery = queries.getString("GET_ALL_EMPLOYEES_PREPARED_QUERY");
		
		try {
			logger.log(Level.FINE,"Preparing the employee statment for execution");
			//adding employee to the table
			statement = conn.prepareStatement(getAllEmployeesQuery);
			
			logger.log(Level.FINE,"Getting all employees");
			results = statement.executeQuery();
			
			while(results.next()){
				EmployeeBean employee = new EmployeeBean();
				ContactBean contact = new ContactBean();
				contact.setPhone(results.getString("Phone"));
				contact.setEmail(results.getString("Email"));
				contact.setType(results.getString("Type"));
				
				employee.setId(results.getInt("Id_PK"));
				employee.setManagerId(results.getInt("ManagerId"));
				employee.setAge(results.getInt("Age"));
				employee.setUsername(results.getString("username"));
				employee.setPassword(results.getString("password"));
				employee.setFirstName(results.getString("FirstName"));
				employee.setLastName(results.getString("LastName"));
				employee.setRole(results.getString("Role"));
				employee.setPicture(results.getBytes("Picture"));
				employee.setContact(contact);
				employees.add(employee);
				
			}
		}finally{
			if(results != null){
				DBConnection.closeResultSet(results);
			}
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		
		EmployeeBean employeeArray[] = employees.toArray(new EmployeeBean[employees.size()]);
		return employeeArray;
	}

	/**
	 * gets the employee bean based on the id 
	 * @param conn
	 * @param id
	 * @return EmployeeBean
	 * @throws SQLException
	 */
	public static EmployeeBean getEmployeeById(Connection conn, int id) throws SQLException {
		PreparedStatement statement = null;
		ResultSet results = null;
		EmployeeBean employee = new EmployeeBean();
		ContactBean contact = new ContactBean();
		
		String getAllEmployeesQuery = queries.getString("GET_EMPLOYEES_BY_ID_PREPARED_QUERY");
		
		try {
			logger.log(Level.FINE,"Preparing the employee statment for execution");
			//adding employee to the table
			statement = conn.prepareStatement(getAllEmployeesQuery);
			statement.setInt(1, id);
			
			logger.log(Level.FINE,"Getting employee by ID");
			results = statement.executeQuery();
				
			
			if(results.next()){
				contact.setPhone(results.getString("Phone"));
				contact.setEmail(results.getString("Email"));
				contact.setType(results.getString("Type"));
				
				employee.setId(results.getInt("Id_PK"));
				employee.setManagerId(results.getInt("ManagerId"));
				employee.setAge(results.getInt("Age"));
				employee.setUsername(results.getString("username"));
				employee.setPassword(results.getString("password"));
				employee.setFirstName(results.getString("FirstName"));
				employee.setLastName(results.getString("LastName"));
				employee.setRole(results.getString("Role"));
				employee.setPicture(results.getBytes("Picture"));
				employee.setContact(contact);
			}
			results.close();
			statement.close();
			
		} finally{
			if(results != null){
				DBConnection.closeResultSet(results);
			}
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		return employee;
	}

	/**
	 * Gets the employee based on the specific properties defined
	 * @param conn
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param role
	 * @return EmployeeBean[]
	 * @throws SQLException
	 */
	public static EmployeeBean[] getEmployeeByProperties(Connection conn, String firstName, String lastName, String email, String role) throws SQLException{
		PreparedStatement statement = null;
		ResultSet results = null;
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		
		String getEmployeesQuery = queries.getString("GET_ALL_EMPLOYEES_PREPARED_QUERY");
		
		//forming query
		if(firstName != null && !firstName.equals("")){
			getEmployeesQuery += " WHERE firstname LIKE '%"+firstName+"%'";
		}
		if(lastName != null && !lastName.equals("")){
			if(getEmployeesQuery.contains("LIKE"))
				getEmployeesQuery += " AND lastname LIKE '%"+lastName+"%'";
			else
				getEmployeesQuery += " WHERE lastname LIKE '%"+lastName+"%'";
		}
		if(email != null && !email.equals("")){
			if(getEmployeesQuery.contains("LIKE"))
				getEmployeesQuery += " AND email LIKE '%"+email+"%'";
			else
				getEmployeesQuery += " WHERE email LIKE '%"+email+"%'";
		}
		if(role != null && !role.equals("")){
			if(getEmployeesQuery.contains("LIKE"))
				getEmployeesQuery += " AND role LIKE '%"+role+"%'";
			else
				getEmployeesQuery += " WHERE role LIKE '%"+role+"%'";
		}
		
		if(getEmployeesQuery.contains("LIKE"))
			getEmployeesQuery += " AND role <> 'HR'";
		else
			getEmployeesQuery += " WHERE role <> 'HR'";
	
		//query
		logger.log(Level.FINE, getEmployeesQuery);
		
		try {
			logger.log(Level.FINE,"Preparing the employee statment for execution");
			//adding employee to the table
			statement = conn.prepareStatement(getEmployeesQuery);
			
			logger.log(Level.FINE,"Getting all employees");
			results = statement.executeQuery();
			
			while(results.next()){
				EmployeeBean employee = new EmployeeBean();
				ContactBean contact = new ContactBean();
				contact.setPhone(results.getString("Phone"));
				contact.setEmail(results.getString("Email"));
				contact.setType(results.getString("Type"));
				
				employee.setId(results.getInt("Id_PK"));
				employee.setManagerId(results.getInt("ManagerId"));
				employee.setAge(results.getInt("Age"));
				employee.setUsername(results.getString("username"));
				employee.setPassword(results.getString("password"));
				employee.setFirstName(results.getString("FirstName"));
				employee.setLastName(results.getString("LastName"));
				employee.setRole(results.getString("Role"));
				employee.setPicture(results.getBytes("picture"));
				employee.setContact(contact);
				employees.add(employee);
				
			}
		}  finally{
			if(results != null){
				DBConnection.closeResultSet(results);
			}
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		EmployeeBean employeeArray[] = employees.toArray(new EmployeeBean[employees.size()]);
		return employeeArray;
	}
	
	/**
	 * updates the employee
	 * @param conn
	 * @param employee
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean updateEmployee(Connection conn, EmployeeBean employee) throws SQLException {
		boolean operationCompleted = false;
		PreparedStatement statement = null;
		ContactBean contact = employee.getContact();
		
		String employeeQuery = queries.getString("UPDATE_EMPLOYEE_PREPARED_QUERY");
		
		try {
			//Preparing for update
			logger.log(Level.FINE,"Preparing the employee statment for update");
			//adding employee to the table
			statement = conn.prepareStatement(employeeQuery);
			statement.setInt(1, employee.getAge());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getRole());
			statement.setBytes(5, employee.getPicture());
			statement.setInt(6, employee.getManagerId());
			statement.setString(7, contact.getPhone());
			statement.setString(8, contact.getEmail());
			statement.setString(9, contact.getType());
			statement.setInt(10, employee.getId());
			
			logger.log(Level.FINE,"Updating employee");
			statement.executeUpdate();
	
			operationCompleted = true;
		} finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		
		return operationCompleted;
	}
	
	/**
	 * updates the employee manager
	 * @param conn
	 * @param id
	 * @param managerId
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean updateEmployeeManager(Connection conn, int id, int managerId) throws SQLException {
		boolean operationCompleted = false;
		PreparedStatement statement = null;
		
		String employeeQuery = queries.getString("UPDATE_EMPLOYEE_PREPARED_QUERY");;
		
		try {
			EmployeeBean employee = getEmployeeById(conn,id);
			
			//Preparing for update
			logger.log(Level.FINE,"Preparing the employee statment for update");
			//adding employee to the table
			statement = conn.prepareStatement(employeeQuery);
			statement.setInt(1, employee.getAge());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getRole());
			statement.setBytes(5, employee.getPicture());
			statement.setInt(6, managerId);
			statement.setString(7, employee.getContact().getPhone());
			statement.setString(8, employee.getContact().getEmail());
			statement.setString(9, employee.getContact().getType());
			statement.setInt(10, employee.getId());
			
			logger.log(Level.FINE,"Updating employee");
			statement.executeUpdate();
			
			operationCompleted = true;
		} finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		
		return operationCompleted;
	}

	/**
	 * deletes employee based on the ID
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static boolean deleteEmployeeById(Connection conn, int id) throws SQLException {
		boolean operationCompleted = false;
		PreparedStatement statement = null;
		
		String employeeQuery = queries.getString("DELETE_EMPLOYEE_PREPARED_QUERY");
		
		try {

			//Preparing for delete
			logger.log(Level.FINE,"Preparing the employee statment for delete");
			//adding employee to the table
			statement = conn.prepareStatement(employeeQuery);
			statement.setInt(1, id);

			logger.log(Level.FINE,"Deleting employee");
			statement.executeUpdate();
			
			operationCompleted=true;
			
		} finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		
		return operationCompleted;
	}

	
	/**
	 * deletes contact record based on employee id
	 * @param conn
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean deleteContactByEmployeeId(Connection conn, int id) throws SQLException {
		boolean operationCompleted = false;
		PreparedStatement statement = null;
		
		String employeeQuery = queries.getString("DELETE_CONTACT_PREPARED_QUERY");
		
		try {
			//prepareing for update
			logger.log(Level.FINE,"Preparing the contact statment for delete");
			//adding employee to the table
			statement = conn.prepareStatement(employeeQuery);
			statement.setInt(1, id);

			logger.log(Level.FINE,"Deleting contact");
			statement.executeUpdate();
			
			operationCompleted=true;
			
		} finally{
			if(statement != null){
				DBConnection.closeStatement(statement);
			}
		}
		
		return operationCompleted;
	}
	
}