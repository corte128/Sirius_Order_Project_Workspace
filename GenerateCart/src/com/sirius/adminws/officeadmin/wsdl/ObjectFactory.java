//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.adminws.officeadmin.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sirius.adminws.officeadmin.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteHoliday_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "deleteHoliday");
    private final static QName _GetOfficeAdmin_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "getOfficeAdmin");
    private final static QName _DeleteHolidayResponse_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "deleteHolidayResponse");
    private final static QName _GetOfficeAdminResponse_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "getOfficeAdminResponse");
    private final static QName _GetUnapprovedEmployees_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "getUnapprovedEmployees");
    private final static QName _AddHolidayResponse_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "addHolidayResponse");
    private final static QName _AddVisitors_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "addVisitors");
    private final static QName _GetAllHolidays_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "getAllHolidays");
    private final static QName _AddHoliday_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "addHoliday");
    private final static QName _AddVisitorsResponse_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "addVisitorsResponse");
    private final static QName _GetAllHolidaysResponse_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "getAllHolidaysResponse");
    private final static QName _GetUnapprovedEmployeesResponse_QNAME = new QName("http://adminws.sirius.com/officeAdmin/wsdl", "getUnapprovedEmployeesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sirius.adminws.officeadmin.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUnapprovedEmployeesResponse }
     * 
     */
    public GetUnapprovedEmployeesResponse createGetUnapprovedEmployeesResponse() {
        return new GetUnapprovedEmployeesResponse();
    }

    /**
     * Create an instance of {@link GetAllHolidays }
     * 
     */
    public GetAllHolidays createGetAllHolidays() {
        return new GetAllHolidays();
    }

    /**
     * Create an instance of {@link AddVisitors }
     * 
     */
    public AddVisitors createAddVisitors() {
        return new AddVisitors();
    }

    /**
     * Create an instance of {@link GetAllHolidaysResponse }
     * 
     */
    public GetAllHolidaysResponse createGetAllHolidaysResponse() {
        return new GetAllHolidaysResponse();
    }

    /**
     * Create an instance of {@link AddVisitorsResponse }
     * 
     */
    public AddVisitorsResponse createAddVisitorsResponse() {
        return new AddVisitorsResponse();
    }

    /**
     * Create an instance of {@link AddHoliday }
     * 
     */
    public AddHoliday createAddHoliday() {
        return new AddHoliday();
    }

    /**
     * Create an instance of {@link AddHolidayResponse }
     * 
     */
    public AddHolidayResponse createAddHolidayResponse() {
        return new AddHolidayResponse();
    }

    /**
     * Create an instance of {@link GetOfficeAdmin }
     * 
     */
    public GetOfficeAdmin createGetOfficeAdmin() {
        return new GetOfficeAdmin();
    }

    /**
     * Create an instance of {@link DeleteHoliday }
     * 
     */
    public DeleteHoliday createDeleteHoliday() {
        return new DeleteHoliday();
    }

    /**
     * Create an instance of {@link GetOfficeAdminResponse }
     * 
     */
    public GetOfficeAdminResponse createGetOfficeAdminResponse() {
        return new GetOfficeAdminResponse();
    }

    /**
     * Create an instance of {@link DeleteHolidayResponse }
     * 
     */
    public DeleteHolidayResponse createDeleteHolidayResponse() {
        return new DeleteHolidayResponse();
    }

    /**
     * Create an instance of {@link GetUnapprovedEmployees }
     * 
     */
    public GetUnapprovedEmployees createGetUnapprovedEmployees() {
        return new GetUnapprovedEmployees();
    }

    /**
     * Create an instance of {@link Holiday }
     * 
     */
    public Holiday createHoliday() {
        return new Holiday();
    }

    /**
     * Create an instance of {@link EmployeeBean }
     * 
     */
    public EmployeeBean createEmployeeBean() {
        return new EmployeeBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteHoliday }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "deleteHoliday")
    public JAXBElement<DeleteHoliday> createDeleteHoliday(DeleteHoliday value) {
        return new JAXBElement<DeleteHoliday>(_DeleteHoliday_QNAME, DeleteHoliday.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOfficeAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "getOfficeAdmin")
    public JAXBElement<GetOfficeAdmin> createGetOfficeAdmin(GetOfficeAdmin value) {
        return new JAXBElement<GetOfficeAdmin>(_GetOfficeAdmin_QNAME, GetOfficeAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteHolidayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "deleteHolidayResponse")
    public JAXBElement<DeleteHolidayResponse> createDeleteHolidayResponse(DeleteHolidayResponse value) {
        return new JAXBElement<DeleteHolidayResponse>(_DeleteHolidayResponse_QNAME, DeleteHolidayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOfficeAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "getOfficeAdminResponse")
    public JAXBElement<GetOfficeAdminResponse> createGetOfficeAdminResponse(GetOfficeAdminResponse value) {
        return new JAXBElement<GetOfficeAdminResponse>(_GetOfficeAdminResponse_QNAME, GetOfficeAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUnapprovedEmployees }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "getUnapprovedEmployees")
    public JAXBElement<GetUnapprovedEmployees> createGetUnapprovedEmployees(GetUnapprovedEmployees value) {
        return new JAXBElement<GetUnapprovedEmployees>(_GetUnapprovedEmployees_QNAME, GetUnapprovedEmployees.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddHolidayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "addHolidayResponse")
    public JAXBElement<AddHolidayResponse> createAddHolidayResponse(AddHolidayResponse value) {
        return new JAXBElement<AddHolidayResponse>(_AddHolidayResponse_QNAME, AddHolidayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddVisitors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "addVisitors")
    public JAXBElement<AddVisitors> createAddVisitors(AddVisitors value) {
        return new JAXBElement<AddVisitors>(_AddVisitors_QNAME, AddVisitors.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllHolidays }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "getAllHolidays")
    public JAXBElement<GetAllHolidays> createGetAllHolidays(GetAllHolidays value) {
        return new JAXBElement<GetAllHolidays>(_GetAllHolidays_QNAME, GetAllHolidays.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddHoliday }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "addHoliday")
    public JAXBElement<AddHoliday> createAddHoliday(AddHoliday value) {
        return new JAXBElement<AddHoliday>(_AddHoliday_QNAME, AddHoliday.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddVisitorsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "addVisitorsResponse")
    public JAXBElement<AddVisitorsResponse> createAddVisitorsResponse(AddVisitorsResponse value) {
        return new JAXBElement<AddVisitorsResponse>(_AddVisitorsResponse_QNAME, AddVisitorsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllHolidaysResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "getAllHolidaysResponse")
    public JAXBElement<GetAllHolidaysResponse> createGetAllHolidaysResponse(GetAllHolidaysResponse value) {
        return new JAXBElement<GetAllHolidaysResponse>(_GetAllHolidaysResponse_QNAME, GetAllHolidaysResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUnapprovedEmployeesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://adminws.sirius.com/officeAdmin/wsdl", name = "getUnapprovedEmployeesResponse")
    public JAXBElement<GetUnapprovedEmployeesResponse> createGetUnapprovedEmployeesResponse(GetUnapprovedEmployeesResponse value) {
        return new JAXBElement<GetUnapprovedEmployeesResponse>(_GetUnapprovedEmployeesResponse_QNAME, GetUnapprovedEmployeesResponse.class, null, value);
    }

}
