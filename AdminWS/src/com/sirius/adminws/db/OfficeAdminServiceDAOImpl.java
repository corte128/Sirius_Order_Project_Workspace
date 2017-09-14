package com.sirius.adminws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sirius.service.superadmin.database.DBConnection;

public class OfficeAdminServiceDAOImpl {

	private static final Logger logger = Logger.getLogger(OfficeAdminServiceDAOImpl.class
			.getName());
	Connection conn;
	
	public OfficeAdminServiceDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	public boolean addVisitors(String startDate, String endDate, int count, String comment){
		logger.log(Level.FINE, "Adding Visitors...");
		//get date strings into format: year month day
		//insert into visitor table
		return false;
	}
	
	public boolean addHoliday(String holidayName, String holidayDate){
		logger.log(Level.FINE, "Adding Holiday...");
		//get date string into format: year month day
		//insert into holiday table
		return false;
	}
	
	public boolean deleteHoliday(int holidayID){
		logger.log(Level.FINE, "Delete Holiday...");
		try {
			String sql = "DELETE * FROM holiday_tbl WHERE holiday_id_pk = (?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, holidayID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in deleteHoliday(): "+e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void closeConnection(){
		try {
			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
