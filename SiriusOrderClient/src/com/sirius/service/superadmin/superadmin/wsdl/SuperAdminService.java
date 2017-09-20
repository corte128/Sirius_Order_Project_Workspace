//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.service.superadmin.superadmin.wsdl;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "SuperAdminService", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", wsdlLocation = "WEB-INF/wsdl/SuperAdminService.wsdl")
public class SuperAdminService
    extends Service
{

    private final static URL SUPERADMINSERVICE_WSDL_LOCATION;
    private final static WebServiceException SUPERADMINSERVICE_EXCEPTION;
    private final static QName SUPERADMINSERVICE_QNAME = new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "SuperAdminService");

    static {
            SUPERADMINSERVICE_WSDL_LOCATION = com.sirius.service.superadmin.superadmin.wsdl.SuperAdminService.class.getResource("/WEB-INF/wsdl/SuperAdminService.wsdl");
        WebServiceException e = null;
        if (SUPERADMINSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/SuperAdminService.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        SUPERADMINSERVICE_EXCEPTION = e;
    }

    public SuperAdminService() {
        super(__getWsdlLocation(), SUPERADMINSERVICE_QNAME);
    }

    public SuperAdminService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SUPERADMINSERVICE_QNAME, features);
    }

    public SuperAdminService(URL wsdlLocation) {
        super(wsdlLocation, SUPERADMINSERVICE_QNAME);
    }

    public SuperAdminService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SUPERADMINSERVICE_QNAME, features);
    }

    public SuperAdminService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SuperAdminService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SuperAdmin
     */
    @WebEndpoint(name = "superadmin")
    public SuperAdmin getSuperadmin() {
        return super.getPort(new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "superadmin"), SuperAdmin.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SuperAdmin
     */
    @WebEndpoint(name = "superadmin")
    public SuperAdmin getSuperadmin(WebServiceFeature... features) {
        return super.getPort(new QName("http://superadmin.service.sirius.com/superadmin/wsdl", "superadmin"), SuperAdmin.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SUPERADMINSERVICE_EXCEPTION!= null) {
            throw SUPERADMINSERVICE_EXCEPTION;
        }
        return SUPERADMINSERVICE_WSDL_LOCATION;
    }

}