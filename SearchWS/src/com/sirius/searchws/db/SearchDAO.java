package com.sirius.searchws.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import com.sirius.searchws.beans.ActualvBudgetBean;


public class SearchDAO {


	public List<ActualvBudgetBean> budgetSearch(int location_id, Date fromDate, Date toDate, String reportType){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SearchDAOImpl impl = new SearchDAOImpl(conn);
		List<ActualvBudgetBean> results = impl.budgetSearch(location_id, fromDate, toDate, reportType);
		impl.closeConnection();
		return results;
	}
}
