package com.sirius.wishlistws.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

import com.sirius.wishlistws.beans.EmployeeBean;
import com.sirius.wishlistws.beans.ProductBean;

public class WishlistServiceDAO {


	public void addToLikeTable(int employee_id, int product_id){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		WishlistServiceDAOImpl impl = new WishlistServiceDAOImpl(conn);
		impl.addToLikeTable(employee_id, product_id);
		impl.closeConnection();
	}
	
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(int product_id){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		WishlistServiceDAOImpl impl = new WishlistServiceDAOImpl(conn);
		List<EmployeeBean> employees = impl.getAllEmployeesWhoLikedProduct(product_id);
		impl.closeConnection();
		return employees;
	}
	
	public void removeFromEmployeeWishlist(int employee_id, int product_id){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		WishlistServiceDAOImpl impl = new WishlistServiceDAOImpl(conn);
		impl.removeFromEmployeeWishlist(employee_id, product_id);
		impl.closeConnection();
	}
	
	public List<ProductBean> getAllProductsEmployeeLiked(int employee_id){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		WishlistServiceDAOImpl impl = new WishlistServiceDAOImpl(conn);
		List<ProductBean> products = impl.getAllProductsEmployeeLiked(employee_id);
		impl.closeConnection();
		return products;
	}



}
