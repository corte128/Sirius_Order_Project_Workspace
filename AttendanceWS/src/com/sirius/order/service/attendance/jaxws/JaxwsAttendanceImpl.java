package com.sirius.order.service.attendance.jaxws;

import java.util.ArrayList;

import javax.jws.WebService;

import com.sirius.order.service.attendance.bean.AttendanceRecordBean;

@WebService(endpointInterface = "com.sirius.order.service.attendance.jaxws.Attendance", portName = "Attendance", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl", serviceName = "JaxwsAttendance")

public class JaxwsAttendanceImpl{
	AttendanceDAO attendanceDAO = new AttendanceDAO();
	
	public ArrayList<AttendanceRecordBean> getAttendanceRecords(String inputName, String inputEmail, String locationId, String inputBeginDate, String inputEndDate){
		ArrayList<AttendanceRecordBean> attendanceList = new ArrayList<AttendanceRecordBean>();
		attendanceList = attendanceDAO.getAttendanceRecords(inputName, inputEmail, locationId, inputBeginDate, inputEndDate);
		
		return attendanceList;
	}
}
