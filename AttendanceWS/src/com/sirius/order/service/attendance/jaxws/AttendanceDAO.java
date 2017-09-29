package com.sirius.order.service.attendance.jaxws;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.order.service.attendance.bean.AttendanceRecordBean;

public class AttendanceDAO {
	AttendanceDAOImpl impl = new AttendanceDAOImpl();
	private static final Logger logger = Logger.getLogger(AttendanceDAO.class
			.getName());
	
	
 	public ArrayList<AttendanceRecordBean> getAttendanceRecords(String inputName, String inputEmail, String locationId, String inputBeginDate, String inputEndDate){
 		ArrayList<AttendanceRecordBean> attendanceList = new ArrayList<AttendanceRecordBean>();
 		
 		Connection conn = null;
 		
 		try {
			conn = DBConnection.getConnection();
			attendanceList = impl.getAttendanceRecords(conn, inputName, inputEmail, locationId, inputBeginDate, inputEndDate);
		} catch (NamingException e) {
			logger.log(Level.FINE, "naming exception");
			logger.log(Level.FINE, e.getMessage());
		} catch (SQLException e) {
			logger.log(Level.FINE, "sql exception");
			logger.log(Level.FINE, e.getMessage());
		}finally{
			DBConnection.closeConnection(conn);
		}
		return attendanceList;
 	}
}
