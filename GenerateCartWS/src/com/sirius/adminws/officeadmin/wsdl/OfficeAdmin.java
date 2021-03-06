//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.adminws.officeadmin.wsdl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "OfficeAdmin", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OfficeAdmin {


    /**
     * 
     * @param locationID
     * @param startDate
     * @param userID
     * @param endDate
     * @param comment
     * @param count
     * @return
     *     returns boolean
     */
    @WebMethod(action = "addVisitors")
    @WebResult(name = "addVisitorsReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
    @RequestWrapper(localName = "addVisitors", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.AddVisitors")
    @ResponseWrapper(localName = "addVisitorsResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.AddVisitorsResponse")
    @Action(input = "addVisitors", output = "http://adminws.sirius.com/officeAdmin/wsdl/OfficeAdmin/addVisitorsResponse")
    public boolean addVisitors(
        @WebParam(name = "startDate", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        String startDate,
        @WebParam(name = "endDate", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        String endDate,
        @WebParam(name = "count", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int count,
        @WebParam(name = "comment", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        String comment,
        @WebParam(name = "userID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int userID,
        @WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int locationID);

    /**
     * 
     * @param locationID
     * @param holidayName
     * @param userID
     * @param date
     * @return
     *     returns boolean
     */
    @WebMethod(action = "addHoliday")
    @WebResult(name = "addHolidayReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
    @RequestWrapper(localName = "addHoliday", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.AddHoliday")
    @ResponseWrapper(localName = "addHolidayResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.AddHolidayResponse")
    @Action(input = "addHoliday", output = "http://adminws.sirius.com/officeAdmin/wsdl/OfficeAdmin/addHolidayResponse")
    public boolean addHoliday(
        @WebParam(name = "holidayName", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        String holidayName,
        @WebParam(name = "date", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        String date,
        @WebParam(name = "userID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int userID,
        @WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int locationID);

    /**
     * 
     * @param holidayID
     * @param userID
     * @return
     *     returns boolean
     */
    @WebMethod(action = "deleteHoliday")
    @WebResult(name = "deleteHolidayReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
    @RequestWrapper(localName = "deleteHoliday", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.DeleteHoliday")
    @ResponseWrapper(localName = "deleteHolidayResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.DeleteHolidayResponse")
    @Action(input = "deleteHoliday", output = "http://adminws.sirius.com/officeAdmin/wsdl/OfficeAdmin/deleteHolidayResponse")
    public boolean deleteHoliday(
        @WebParam(name = "holidayID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int holidayID,
        @WebParam(name = "userID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int userID);

    /**
     * 
     * @param locationID
     * @return
     *     returns java.util.List<com.sirius.adminws.officeadmin.wsdl.Holiday>
     */
    @WebMethod(action = "getAllHolidays")
    @WebResult(name = "getAllHolidaysReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
    @RequestWrapper(localName = "getAllHolidays", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.GetAllHolidays")
    @ResponseWrapper(localName = "getAllHolidaysResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.GetAllHolidaysResponse")
    @Action(input = "getAllHolidays", output = "http://adminws.sirius.com/officeAdmin/wsdl/OfficeAdmin/getAllHolidaysResponse")
    public List<Holiday> getAllHolidays(
        @WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int locationID);

    /**
     * 
     * @param locationID
     * @return
     *     returns java.util.List<com.sirius.adminws.officeadmin.wsdl.EmployeeBean>
     */
    @WebMethod(action = "getUnapprovedEmployees")
    @WebResult(name = "getUnapprovedEmployeesReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
    @RequestWrapper(localName = "getUnapprovedEmployees", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.GetUnapprovedEmployees")
    @ResponseWrapper(localName = "getUnapprovedEmployeesResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.GetUnapprovedEmployeesResponse")
    @Action(input = "getUnapprovedEmployees", output = "http://adminws.sirius.com/officeAdmin/wsdl/OfficeAdmin/getUnapprovedEmployeesResponse")
    public List<EmployeeBean> getUnapprovedEmployees(
        @WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int locationID);

    /**
     * 
     * @param locationID
     * @return
     *     returns com.sirius.adminws.officeadmin.wsdl.EmployeeBean
     */
    @WebMethod(action = "getOfficeAdmin")
    @WebResult(name = "getOfficeAdminReturn", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
    @RequestWrapper(localName = "getOfficeAdmin", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.GetOfficeAdmin")
    @ResponseWrapper(localName = "getOfficeAdminResponse", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", className = "com.sirius.adminws.officeadmin.wsdl.GetOfficeAdminResponse")
    @Action(input = "getOfficeAdmin", output = "http://adminws.sirius.com/officeAdmin/wsdl/OfficeAdmin/getOfficeAdminResponse")
    public EmployeeBean getOfficeAdmin(
        @WebParam(name = "locationID", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl")
        int locationID);

}
