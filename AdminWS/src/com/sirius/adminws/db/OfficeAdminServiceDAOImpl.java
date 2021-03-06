package com.sirius.adminws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sirius.adminws.beans.EmployeeBean;
import com.sirius.adminws.beans.Holiday;
import com.sirius.service.superadmin.database.DBConnection;

public class OfficeAdminServiceDAOImpl {

	private static final Logger logger = Logger.getLogger(OfficeAdminServiceDAOImpl.class
			.getName());
	Connection conn;
	
	public OfficeAdminServiceDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	public boolean addVisitors(String startDate, String endDate, int count, 
			String comment, int userID, int locationID){
		logger.log(Level.FINE, "Adding Visitors...");
		//insert into visitor table
		String sql = "INSERT INTO visitor_tbl (start_date, end_date, number_of_visitors, comments, created_by, created_date, location_id_fk, is_valid)" + 
		" Values (?, ?, ?, ?, ?, (SELECT CURDATE()), ?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, startDate);
			stmt.setString(2, endDate);
			stmt.setInt(3, count);
			stmt.setString(4, comment);
			stmt.setInt(5, userID);
			stmt.setInt(6, locationID);
			stmt.setInt(7, 1);
			stmt.executeUpdate();
			DBConnection.closePreparedStatement(stmt);
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error addVisitors(): " + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addHoliday(String holidayName, String holidayDate, int userID, int locationID){
		logger.log(Level.FINE, "Adding Holiday...");
		//insert into holiday table
		String sql = "INSERT INTO holiday_tbl (holiday_date, holiday_name, created_by, created_date, location_id_fk, is_valid)"+
		" VALUES (?, ?, ?, (SELECT CURDATE()), ?, true)";
		String[] dateParts = holidayDate.split("/");
		String day = dateParts[1];
		String year = dateParts[2];
		String month = dateParts[0];
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, year+"-"+month+"-"+day);
			stmt.setString(2, holidayName);
			stmt.setInt(3, userID);
			stmt.setInt(4, locationID);
			stmt.executeUpdate();
			DBConnection.closePreparedStatement(stmt);
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error addHoliday(): " + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteHoliday(int holidayID, int userID){
		logger.log(Level.FINE, "Deleting Holiday...");
		try {
			String sql = "UPDATE holiday_tbl SET is_valid = false, updated_by = (?), updated_date = (SELECT CURDATE()) WHERE holiday_id_pk = (?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, holidayID);
			stmt.executeUpdate();
			DBConnection.closePreparedStatement(stmt);
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in deleteHoliday(): "+e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<Holiday> getAllHolidays(int locationID) {
		logger.log(Level.FINE, "Deleting Holiday...");
		String sql = "SELECT * FROM holiday_tbl WHERE location_id_fk = (?)";
		ArrayList<Holiday> holidays = new ArrayList<Holiday>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, locationID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				if (rs.getBoolean("is_valid")){
					Holiday newHoliday = new Holiday();
					newHoliday.setDate(rs.getString("holiday_date"));
					newHoliday.setId(rs.getInt("holiday_id_pk"));
					//get holiday day of week
					String[] date = newHoliday.getDate().split("-");
					Calendar c = Calendar.getInstance();
					int year = Integer.parseInt(date[0]);
					int month = Integer.parseInt(date[1]) - 1;
					int day = Integer.parseInt(date[2]);
					c.set(year, month, day);
					newHoliday.setDayOfWeek(c.getTime().getDay());
					newHoliday.setHolidayName(rs.getString("holiday_name"));
					holidays.add(newHoliday);
				}
			}
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(stmt);
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in getAllHolidays(): "+e);
			e.printStackTrace();
		}
		return holidays;
	}
	
	public List<EmployeeBean> getUnapprovedEmployees(int locationID){
		logger.log(Level.FINE, "Getting Unapproved Employees...");
		String sql = "SELECT * FROM employee_tbl JOIN login_tbl ON"+ 
		" employee_id_pk = employee_id_fk WHERE location_id_fk = (?) AND is_valid = (?)";
		ArrayList<EmployeeBean> emps = new ArrayList<EmployeeBean>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, locationID);
			stmt.setString(2, "pending");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				EmployeeBean e = new EmployeeBean();
				e.setEmail(rs.getString("employee_email"));
				e.setName(rs.getString("employee_name"));
				e.setId(rs.getInt("employee_id_pk"));
				emps.add(e);
			}
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(stmt);
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in getAllHolidays(): "+e);
			e.printStackTrace();
		}
		return emps;
	}
	
	public EmployeeBean getOfficeAdmin(int locationID){
		logger.log(Level.FINE, "Getting Office Admin...");
		String sql = "SELECT * FROM employee_tbl WHERE "+ 
		" location_id_fk = (?) AND employee_type_id_fk = (?)";
		EmployeeBean officeAdmin = new EmployeeBean();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, locationID);
			stmt.setInt(2, 2);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				officeAdmin.setEmail(rs.getString("employee_email"));
				officeAdmin.setName(rs.getString("employee_name"));
				officeAdmin.setId(rs.getInt("employee_id_pk"));
			}
			DBConnection.closeResultSet(rs);
			DBConnection.closePreparedStatement(stmt);
		} catch (SQLException e) {
			logger.log(Level.FINE, "SQL Error in getOfficeAdmin(): "+e);
			e.printStackTrace();
		}
		return officeAdmin;
	}
	
	public void closeConnection(){
		try {
			DBConnection.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
