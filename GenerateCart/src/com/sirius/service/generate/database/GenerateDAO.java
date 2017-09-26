package com.sirius.service.generate.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.adminws.officeadmin.wsdl.Holiday;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminProxy;
import com.sirius.order.service.attendance.jaxws.jaxws.attendance.wsdl.AttendanceProxy;
import com.sirius.order.service.attendance.jaxws.jaxws.attendance.wsdl.AttendanceRecordBean;

public class GenerateDAO {
	private static final Logger logger = Logger.getLogger(GenerateDAO.class.getName());
	
	public static BigDecimal generateBudget(int locationId){
		BigDecimal recommendedBudget = null;
		int numberOfEmployees = 0;
		int numberOfHolidays = 0;
		Date beginningOfWeek = new Date();
		Date endOfWeek = new Date();
		Connection conn = null;
		
		//service access variables
		AttendanceProxy attendance = new AttendanceProxy();
		OfficeAdminProxy officeAdmin = new OfficeAdminProxy();
		
		try{
			Calendar cal = getStartDate();
			//getting the budget week
			beginningOfWeek = cal.getTime();
			cal.add(Calendar.DATE, +6);
			endOfWeek = cal.getTime();
			
			//pushes back to the previous week for attendance
			cal.add(Calendar.DATE, -13);
			String beginDate = cal.getTime().toString();
			cal.add(Calendar.DATE, +5);
			String endDate = cal.getTime().toString();
			
			//gets the all the attendance from the previous week
			List<AttendanceRecordBean> attendanceResults = attendance.getAttendanceRecords("%", "%", "%", beginDate, endDate);
			numberOfEmployees = attendanceResults.size();
			
			//gets the holidays and checks if the holidays are with in the date range
			List<Holiday> holidays = officeAdmin.getAllHolidays(locationId);
			for(Holiday day : holidays){
				if(day.getDate().compareTo(endOfWeek.toString()) > 0){
					
				}
				else if(day.getDate().compareTo(beginningOfWeek.toString()) < 0){
					
				}
				else{
					numberOfHolidays++;
				}
			}
			
			//gets the visitors for the week
			numberOfEmployees += GenerateDAOImplementation.getNumberOfVisitors(conn, locationId, beginningOfWeek.toString(), endOfWeek.toString());
			
			//checks for holidays
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
		
			conn.commit();
			
		}catch(NamingException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
			}
		} catch (Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,"SQL Exception Found: Couldn't roll back", e1);
			} finally{
				logger.log(Level.SEVERE,"Exception Found ", e);
			}
		} finally{
			if (conn != null){
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL Exception ", e);
				}
			}
		}
		

		return recommendedBudget;
	}
	
	/**
	 * gets the start date that will determine when the budget is generated
	 * @return Calendar
	 */
	private static Calendar getStartDate(){
		Calendar cal = Calendar.getInstance();
		
		//gets the Monday date of the week since that is the start
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
			cal.add(Calendar.DATE, -1);
		}
		
		return cal;
	}
}
