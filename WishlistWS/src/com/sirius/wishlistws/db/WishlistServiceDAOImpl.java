package com.sirius.wishlistws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sirius.wishlistws.beans.EmployeeBean;
import com.sirius.wishlistws.beans.ProductBean;

public class WishlistServiceDAOImpl {
	
	private Connection conn = null;

	public WishlistServiceDAOImpl(Connection conn){
		conn = this.conn;
	}
	
	public void addToLikeTable(int employee_id, int product_id){
		//INSERT INTO like_table ( person_id, product_id ) VALUES ( 2, 2 );
		String sql="INSERT INTO likes_tbl (employee_id_fk, product_id_fk) VALUES (?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, employee_id);
			statement.setInt(2, product_id);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//completed
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(int product_id){
	    List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		String empName = "employee_name";
		String empTable = "Employee_tbl";
		String likesTable = "Likes_tbl";
		
		try {
			
			Statement statement = conn.createStatement();
			
		    String sql = "SELECT employee_id_pk, " + empName + " FROM " + empTable + " LEFT JOIN " + likesTable + " ON " + 
		    empTable + ".employee_id_pk = " + likesTable + ".employee_id_fk WHERE product_id=" + product_id;
		    ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("employee_id_pk");
				String name = rs.getString("name");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setName(name);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return employees;
	}
	
	public List<ProductBean> getAllProductsEmployeeLiked(int employee_id){
		String empTable = "Employee_tbl";
		String likesTable = "Likes_tbl";
		String productTable = "Product_tbl";
		
		String sql = "SELECT * FROM " + productTable + " JOIN " + likesTable + " ON " + productTable + ".product_id_pk = " + likesTable + ".product_id_fk" +
		" JOIN " + empTable + " " + empTable + ".employee_id_pk = " + likesTable + ".employee_id_fk" + " WHERE " + empTable + ".employee_id_pk = " + employee_id;
		try {
			Statement statement = conn.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("employee_id_pk");
				String name = rs.getString("name");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setName(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeFromEmployeeWishlist(int employee_id, int product_id){
//		String empTable = "Employee_tbl";
		String likesTable = "Likes_tbl";
//		String productTable = "Product_tbl";
		
//		String sql = "DELETE * FROM " + productTable + " JOIN " + likesTable + " ON " + productTable + ".product_id_pk = " + likesTable + ".product_id_fk" +
//		" JOIN " + empTable + " " + empTable + ".employee_id_pk = " + likesTable + ".employee_id_fk" + " WHERE " + likesTable + ".product_id_fk = " + product_id;
		
		String sql = "DELETE FROM " + likesTable + " WHERE employee_id_fk = " + employee_id + " product_id_fk = " + product_id;
		//delete from like_table where (person_id = 1 AND product_id = 1);
		
		try {
			Statement statement = conn.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("employee_id_pk");
				String name = rs.getString("name");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setName(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
}
