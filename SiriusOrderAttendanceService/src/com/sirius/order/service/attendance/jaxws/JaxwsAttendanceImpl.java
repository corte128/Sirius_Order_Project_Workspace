package com.sirius.order.service.attendance.jaxws;

import java.util.ArrayList;

import javax.jws.WebService;

import com.sirius.order.service.attendance.beans.AttendanceRecordBean;

@WebService(endpointInterface = "com.sirius.order.service.attendance.jaxws.Attendance", portName = "Attendance", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl", serviceName = "JaxwsAttednance")

public class JaxwsAttendanceImpl{
	AttendanceDAO attendanceDAO = new AttendanceDAO();
	
	public ArrayList<AttendanceRecordBean> getAttendanceRecords(String inputName, String inputEmail, String inputCity, String inputState, String inputBeginDate, String inputEndDate){
		ArrayList<AttendanceRecordBean> attendanceList = new ArrayList<AttendanceRecordBean>();
		attendanceList = attendanceDAO.getAttendanceRecords(inputName, inputEmail, inputCity, inputState, inputBeginDate, inputEndDate);
		
		return attendanceList;
	}
}
