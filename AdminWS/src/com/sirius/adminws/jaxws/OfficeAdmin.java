package com.sirius.adminws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "OfficeAdmin", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
public interface OfficeAdmin {
	
	@WebMethod(action = "addVisitors")
	@WebResult(name = "addVisitorsReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "addVisitors", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddVisitors")
	@ResponseWrapper(localName = "addVisitorsResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddVisitorsResponse")
	public boolean addVisitors(
			@WebParam(name = "startDate", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String startDate,
			@WebParam(name = "endDate", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String endDate,
			@WebParam(name = "count", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int count,
			@WebParam(name = "comment", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String comment);
	
	@WebMethod(action = "addHoliday")
	@WebResult(name = "addHolidayReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "addHoliday", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddHoliday")
	@ResponseWrapper(localName = "addHolidayResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.AddHolidayResponse")
	public boolean addHoliday(
			@WebParam(name = "holidayName", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String holidayName,
			@WebParam(name = "date", targetNamespace = "http://employeews.sirius.com/employee/wsdl") String date);
	
	@WebMethod(action = "deleteHoliday")
	@WebResult(name = "deleteHolidayReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
	@RequestWrapper(localName = "deleteHoliday", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.DeleteHoliday")
	@ResponseWrapper(localName = "deleteHolidayResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.jaxws.DeleteHolidayResponse")
	public boolean deleteHoliday(
			@WebParam(name = "holidayID", targetNamespace = "http://employeews.sirius.com/employee/wsdl") int holidayID);
	
	//TODO add get all holidays by location id
}
