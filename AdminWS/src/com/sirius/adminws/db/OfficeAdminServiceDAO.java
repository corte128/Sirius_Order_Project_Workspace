package com.sirius.adminws.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.adminws.beans.Holiday;
import com.sirius.service.superadmin.database.DBConnection;

public class OfficeAdminServiceDAO {
	
	private static final Logger logger = Logger.getLogger(OfficeAdminServiceDAO.class
			.getName());
	
	public boolean addVisitors(String startDate, String endDate, int count,
			String comment, int userID, int locationID){
		logger.log(Level.FINE, "Adding Visitors");
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			result = impl.addVisitors(startDate, endDate, count, comment, userID, locationID);
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
	
	public boolean addHoliday(String holidayName, String holidayDate, int userID, int locationID){
		logger.log(Level.FINE, "Adding Holiday");
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			result = impl.addHoliday(holidayName, holidayDate, userID, locationID);
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
	
	public boolean deleteHoliday(int holidayID, int userID){
		logger.log(Level.FINE, "Delete Holiday");
		Connection conn = null;
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			result = impl.deleteHoliday(holidayID, userID);
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
	
	public List<Holiday> getAllHolidays(int locationID){
		logger.log(Level.FINE, "Getting All Holidays for location: " + locationID);
		Connection conn = null;
		ArrayList<Holiday> holidays = null;
		try {
			conn = DBConnection.getConnection();
			OfficeAdminServiceDAOImpl impl = new OfficeAdminServiceDAOImpl(conn);
			holidays = impl.getAllHolidays(locationID);
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
		return holidays;
	}
}
