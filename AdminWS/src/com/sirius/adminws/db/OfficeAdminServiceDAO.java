package com.sirius.adminws.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.superadmin.database.DBConnection;

public class OfficeAdminServiceDAO {
	
	private static final Logger logger = Logger.getLogger(OfficeAdminServiceDAO.class
			.getName());
	
	public boolean addVisitors(String startDate, String endDate, int count,
			String comment){
		logger.log(Level.FINE, "Adding Visitors");
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			result = impl.addVisitors(startDate, endDate, count, comment);
			if (result){
				conn.commit();
			}
			else {
				conn.rollback();
			}
			impl.closeConnection();
		} catch (NamingException e) {
			logger.log(Level.FINE, "Naming Error in addVisitors(): "+e);
			e.printStackTrace();
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in addVisitors(): "+e);
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			logger.log(Level.FINE, "Error in addVisitors(): "+e);
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	public boolean addHoliday(String holidayName, String holidayDate){
		logger.log(Level.FINE, "Adding Holiday");
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			result = impl.addHoliday(holidayName, holidayDate);
			if (result){
				conn.commit();
			}
			else {
				conn.rollback();
			}
			impl.closeConnection();
		} catch (NamingException e) {
			logger.log(Level.FINE, "Naming Error in addHoliday(): "+e);
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in addHoliday(): "+e);
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			logger.log(Level.FINE, "Error in addHoliday(): "+e);
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	public boolean deleteHoliday(int holidayID){
		logger.log(Level.FINE, "Delete Holiday");
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			result = impl.deleteHoliday(holidayID);
			if (result){
				conn.commit();
			}
			else {
				conn.rollback();
			}
			impl.closeConnection();
		} catch (NamingException e) {
			logger.log(Level.FINE, "Naming Error in deleteHoliday(): "+e);
			e.printStackTrace();
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in deleteHoliday(): "+e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.log(Level.FINE, "Error in deleteHoliday(): "+e);
			e.printStackTrace();
		}
		return result;
	}
}
