package com.sirius.search.db;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;


public class EmployeeAttendanceSearchServiceDAO {


	public void addToLikeTable(int employee_id, int product_id){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		WishlistServiceDAOImpl impl = new WishlistServiceDAOImpl(conn);
//		impl.addToLikeTable(employee_id, product_id);
//		impl.closeConnection();
	}
}
