//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.service.superadmin.superadmin.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sirius.service.superadmin.superadmin.wsdl package. 
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

    private final static QName _GetBudgetByLocationResponse_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "GetBudgetByLocationResponse");
    private final static QName _AddLocationResponse_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "AddLocationResponse");
    private final static QName _GetBudgetByLocation_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "GetBudgetByLocation");
    private final static QName _AddLocation_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "AddLocation");
    private final static QName _SetBudgetByLocationResponse_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "SetBudgetByLocationResponse");
    private final static QName _SetBudgetByLocation_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "SetBudgetByLocation");
    private final static QName _AssignAdminResponse_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "AssignAdminResponse");
    private final static QName _AssignAdmin_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "AssignAdmin");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sirius.service.superadmin.superadmin.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssignAdmin }
     * 
     */
    public AssignAdmin createAssignAdmin() {
        return new AssignAdmin();
    }

    /**
     * Create an instance of {@link AssignAdminResponse }
     * 
     */
    public AssignAdminResponse createAssignAdminResponse() {
        return new AssignAdminResponse();
    }

    /**
     * Create an instance of {@link SetBudgetByLocation }
     * 
     */
    public SetBudgetByLocation createSetBudgetByLocation() {
        return new SetBudgetByLocation();
    }

    /**
     * Create an instance of {@link SetBudgetByLocationResponse }
     * 
     */
    public SetBudgetByLocationResponse createSetBudgetByLocationResponse() {
        return new SetBudgetByLocationResponse();
    }

    /**
     * Create an instance of {@link AddLocation }
     * 
     */
    public AddLocation createAddLocation() {
        return new AddLocation();
    }

    /**
     * Create an instance of {@link GetBudgetByLocation }
     * 
     */
    public GetBudgetByLocation createGetBudgetByLocation() {
        return new GetBudgetByLocation();
    }

    /**
     * Create an instance of {@link AddLocationResponse }
     * 
     */
    public AddLocationResponse createAddLocationResponse() {
        return new AddLocationResponse();
    }

    /**
     * Create an instance of {@link GetBudgetByLocationResponse }
     * 
     */
    public GetBudgetByLocationResponse createGetBudgetByLocationResponse() {
        return new GetBudgetByLocationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBudgetByLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "GetBudgetByLocationResponse")
    public JAXBElement<GetBudgetByLocationResponse> createGetBudgetByLocationResponse(GetBudgetByLocationResponse value) {
        return new JAXBElement<GetBudgetByLocationResponse>(_GetBudgetByLocationResponse_QNAME, GetBudgetByLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "AddLocationResponse")
    public JAXBElement<AddLocationResponse> createAddLocationResponse(AddLocationResponse value) {
        return new JAXBElement<AddLocationResponse>(_AddLocationResponse_QNAME, AddLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBudgetByLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "GetBudgetByLocation")
    public JAXBElement<GetBudgetByLocation> createGetBudgetByLocation(GetBudgetByLocation value) {
        return new JAXBElement<GetBudgetByLocation>(_GetBudgetByLocation_QNAME, GetBudgetByLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "AddLocation")
    public JAXBElement<AddLocation> createAddLocation(AddLocation value) {
        return new JAXBElement<AddLocation>(_AddLocation_QNAME, AddLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetBudgetByLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "SetBudgetByLocationResponse")
    public JAXBElement<SetBudgetByLocationResponse> createSetBudgetByLocationResponse(SetBudgetByLocationResponse value) {
        return new JAXBElement<SetBudgetByLocationResponse>(_SetBudgetByLocationResponse_QNAME, SetBudgetByLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetBudgetByLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "SetBudgetByLocation")
    public JAXBElement<SetBudgetByLocation> createSetBudgetByLocation(SetBudgetByLocation value) {
        return new JAXBElement<SetBudgetByLocation>(_SetBudgetByLocation_QNAME, SetBudgetByLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "AssignAdminResponse")
    public JAXBElement<AssignAdminResponse> createAssignAdminResponse(AssignAdminResponse value) {
        return new JAXBElement<AssignAdminResponse>(_AssignAdminResponse_QNAME, AssignAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://superadmin.service.sirius.com/superadmin/wsdl", name = "AssignAdmin")
    public JAXBElement<AssignAdmin> createAssignAdmin(AssignAdmin value) {
        return new JAXBElement<AssignAdmin>(_AssignAdmin_QNAME, AssignAdmin.class, null, value);
    }

}
