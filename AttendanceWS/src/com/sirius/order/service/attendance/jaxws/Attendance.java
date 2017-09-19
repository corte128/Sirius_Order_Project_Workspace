package com.sirius.order.service.attendance.jaxws;

import java.util.ArrayList;

import javax.jws.WebMethod;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.order.service.attendance.beans.AttendanceRecordBean;

@WebService(name = "attendance", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl")
public interface Attendance {
	@WebMethod(action = "getAttendanceRecords")
	@WebResult(name = "getAttendanceRecordsReturn", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl")
	@RequestWrapper(localName = "getAttendanceRecords", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl", className = "com.sirius.order.service.attendance.jaxws.GetAttendanceRecords")
	@ResponseWrapper(localName = "getAttendanceResponse", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl", className = "com.sirius.order.service.attendance.jaxws.GetAttendanceRecordsResponse")
	public ArrayList<AttendanceRecordBean> getAttendanceRecords(
			@WebParam(name = "name", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String name,
			@WebParam(name = "email", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String email,
			@WebParam(name = "locationId", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String locationId,
			@WebParam(name = "beginDate", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String beginDate,
			@WebParam(name = "endDate", targetNamespace = "http://jaxws.attendance.service.order.sirius.com/jaxws/attendance/wsdl") String endDate);

}
