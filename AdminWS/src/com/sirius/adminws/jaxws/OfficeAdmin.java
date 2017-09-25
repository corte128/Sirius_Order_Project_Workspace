package com.sirius.adminws.jaxws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.adminws.beans.EmployeeBean;
import com.sirius.adminws.beans.Holiday;

@WebService(name = "OfficeAdmin", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
public interface OfficeAdmin {
	
	@WebMethod(action = "addVisitors")
	@WebResult(name = "addVisitorsReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "addVisitors", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddVisitors")
	@ResponseWrapper(localName = "addVisitorsResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddVisitorsResponse")
	public boolean addVisitors(
			@WebParam(name = "startDate", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") String startDate,
			@WebParam(name = "endDate", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") String endDate,
			@WebParam(name = "count", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int count,
			@WebParam(name = "comment", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") String comment,
			@WebParam(name = "userID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int userID,
			@WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int locationID);
	
	@WebMethod(action = "addHoliday")
	@WebResult(name = "addHolidayReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "addHoliday", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddHoliday")
	@ResponseWrapper(localName = "addHolidayResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddHolidayResponse")
	public boolean addHoliday(
			@WebParam(name = "holidayName", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") String holidayName,
			@WebParam(name = "date", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") String date,
			@WebParam(name = "userID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int userID,
			@WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int locationID);
	
	@WebMethod(action = "deleteHoliday")
	@WebResult(name = "deleteHolidayReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "deleteHoliday", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.DeleteHoliday")
	@ResponseWrapper(localName = "deleteHolidayResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.DeleteHolidayResponse")
	public boolean deleteHoliday(
			@WebParam(name = "holidayID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int holidayID,
			@WebParam(name = "userID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int userID);
	
	@WebMethod(action = "getAllHolidays")
	@WebResult(name = "getAllHolidaysReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "getAllHolidays", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.GetAllHolidays")
	@ResponseWrapper(localName = "getAllHolidaysResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.GetAllHolidaysResponse")
	public List<Holiday> getAllHolidays(
			@WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int locationID);
	
	@WebMethod(action = "getUnapprovedEmployees")
	@WebResult(name = "getUnapprovedEmployeesReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "getUnapprovedEmployees", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.GetUnapprovedEmployees")
	@ResponseWrapper(localName = "getUnapprovedEmployeesResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.GetUnapprovedEmployeesResponse")
	public List<EmployeeBean> getUnapprovedEmployees(
			@WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int locationID);
	
	@WebMethod(action = "getOfficeAdmin")
	@WebResult(name = "getOfficeAdminReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "getOfficeAdmin", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.GetOfficeAdmin")
	@ResponseWrapper(localName = "getOfficeAdminResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.GetOfficeAdminResponse")
	public EmployeeBean getOfficeAdmin(
			@WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl") int locationID);
}
