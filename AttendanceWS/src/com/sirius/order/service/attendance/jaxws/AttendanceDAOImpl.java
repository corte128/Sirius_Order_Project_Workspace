package com.sirius.order.service.attendance.jaxws;

import com.sirius.order.service.attendance.bean.*;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceDAOImpl{
	private static final Logger logger = Logger.getLogger(AttendanceDAOImpl.class
			.getName());
	ResourceBundle resourceBundle = null;
	
	
	public ArrayList<AttendanceRecordBean> getAttendanceRecords(Connection conn, String inputName, String inputEmail, String locationId, String inputBeginDate, String inputEndDate){
		resourceBundle = ResourceBundle.getBundle("com.sirius.order.service.attendance.properties.queries");
		ArrayList<AttendanceRecordBean> attendanceList = new ArrayList<AttendanceRecordBean>();
		String sqlQuery = resourceBundle.getString("ATTENDANCE_QUERY");
		
		logger.log(Level.FINE, sqlQuery);
		ResultSet results = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, inputName);
			statement.setString(2, inputEmail);
			statement.setString(3, locationId);
			statement.setString(4, inputBeginDate);
			statement.setString(5, inputEndDate);
			
			 results = statement.executeQuery();
			
			while(results.next()){
				AttendanceRecordBean recordBean = new AttendanceRecordBean();
				
				String empName = results.getString("employee_name");
				String empEmail = results.getString("employee_email");
				String state = results.getString("state_name");
				String city = results.getString("location_city");
				String date = results.getString("attendance_date");
				
				recordBean.setAttendantName(empName);
				recordBean.setAttendantEmail(empEmail);
				recordBean.setAttendantDate(date);
				recordBean.setAttendantLocation(city + ", " + state);
			
				attendanceList.add(recordBean);
				
				
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, e.getMessage());
		}finally{
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(results);
		}
		
		
		return attendanceList;
	}
}
