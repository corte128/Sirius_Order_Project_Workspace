package com.sirius.attendancews.attendance.wsdl;

import java.util.ArrayList;

public class AttendanceProxyDAO {
	AttendanceProxy proxy = new AttendanceProxy();
	ArrayList<AttendanceRecordBean> attendanceRecords = new ArrayList<AttendanceRecordBean>();
	public ArrayList<AttendanceRecordBean> getAttendanceRecords(String name, String email, String location, String beginDate, String endDate){
		attendanceRecords = (ArrayList<AttendanceRecordBean>) proxy.getAttendanceRecords(name, email, location, beginDate, endDate);
		return attendanceRecords;
	}
} 
