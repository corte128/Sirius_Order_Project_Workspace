package com.sirius.order.back.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sirius.order.back.beans.EmployeeBean;
import com.sirius.order.back.beans.ProductBean;

public class DBLogic {

	public void add(){
		//add to likes table product id and emp id
	}
	
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(Connection conn, int product_id){
	    List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		try {
			
//			SAXXMLParser sxmlp = new SAXXMLParser();
//			sxmlp.creation(conn);
			
			Statement statement = conn.createStatement();
		    String sql = "SELECT * FROM Employee_tbl";
		    ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id_pk");
				String email = rs.getString("email");
				String name = rs.getString("name");
				char[] password = rs.getString("password").toCharArray();
				byte[] image = rs.getBytes("photo");
				int location = rs.getInt("location");
				String role = rs.getString("role");
				int likes = rs.getInt("likes");
				boolean isValid = rs.getBoolean("isValid");
				
				EmployeeBean emp = new EmployeeBean();
				emp.setId(id);
				emp.setEmail(email);
				emp.setName(name);
				emp.setPassword(password);
				emp.setImageBytes(image);
				emp.setLocation(location);
				emp.setRole(role);
				emp.setLikes(likes);
				emp.setValid(isValid);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return employees;
	}
	
	public List<ProductBean> getAllProductsEmployeeLiked(Connection conn, int employee_id){
		return null;
		
	}
	
	public void removeFromEmployeeWishlist(Connection conn, int product_id){
		try {
			String sql = "DELETE FROM Employee_tbl WHERE id_pk = " + product_id;
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
}
