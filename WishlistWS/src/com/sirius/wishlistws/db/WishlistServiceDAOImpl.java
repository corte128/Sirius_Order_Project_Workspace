package com.sirius.wishlistws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.sirius.wishlistws.beans.EmployeeBean;
import com.sirius.wishlistws.beans.ProductBean;

public class WishlistServiceDAOImpl {
	
	private Connection conn = null;
	private static final ResourceBundle queries = ResourceBundle.getBundle("com.sirius.wishlistws.properties.queries");

	public WishlistServiceDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	public void addToLikeTable(int employee_id, int product_id){
		//INSERT INTO like_table ( person_id, product_id ) VALUES ( 2, 2 );
		//String sql="INSERT INTO likes_tbl (employee_id_fk, product_id_fk) VALUES (?,?)";
		String sql = queries.getString("ADD_LIKE");
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, employee_id);
			statement.setInt(2, product_id);
			statement.setInt(3, employee_id);
			statement.setDate(4, new java.sql.Date((new Date()).getTime()));
			//change the true to take in value from server to check if it exists after which it becomes false
			statement.setBoolean(5, true);
			statement.executeUpdate();
			DBConnection.closeStatement(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//completed
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(int product_id){
	    List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		String sql = queries.getString("GET_ALL_EMPLOYEES_WHO_LIKED_PRODUCT");

		try {
		    //String sql = "SELECT employee_id_pk, employee_name FROM Employee_tbl LEFT JOIN Likes_tbl ON Employee_tbl.employee_id_pk = Likes_tbl.employee_id_fk WHERE product_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, product_id);
		    ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("employee_id_pk");
				String name = rs.getString("name");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setName(name);
			}
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return employees;
	}
	
	public List<ProductBean> getAllProductsEmployeeLiked(int employee_id){
		String sql = queries.getString("GET_ALL_PRODUCTS_EMPLOYEE_LIKED");

		//String sql = "SELECT * FROM Product_tbl JOIN Likes_tbl ON Product_tbl.product_id_pk = Likes_tbl.product_id_fk JOIN Employee_tbl Employee_tbl.employee_id_pk = Likes_tbl.employee_id_fk WHERE Employee_tbl.employee_id_pk = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, employee_id);
		    ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("employee_id_pk");
				String name = rs.getString("name");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setName(name);
			}
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeFromEmployeeWishlist(int employee_id, int product_id){
		
//		String sql = "DELETE * FROM " + productTable + " JOIN " + likesTable + " ON " + productTable + ".product_id_pk = " + likesTable + ".product_id_fk" +
//		" JOIN " + empTable + " " + empTable + ".employee_id_pk = " + likesTable + ".employee_id_fk" + " WHERE " + likesTable + ".product_id_fk = " + product_id;
		String sql = queries.getString("REMOVE_FROM_WISHLIST");

		//String sql = "DELETE FROM Likes_tbl WHERE employee_id_fk = ? AND product_id_fk = ?";
		//delete from like_table where (person_id = 1 AND product_id = 1);
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, employee_id);
			statement.setInt(2, product_id);

		    ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("employee_id_pk");
				String name = rs.getString("name");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setName(name);
			}
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
}
