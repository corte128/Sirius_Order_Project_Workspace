//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.mailws.mail.wsdl;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "JaxwsMail", targetNamespace = "http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", wsdlLocation = "WEB-INF/wsdl/JaxwsMail.wsdl")
public class JaxwsMail_Service
    extends Service
{

    private final static URL JAXWSMAIL_WSDL_LOCATION;
    private final static WebServiceException JAXWSMAIL_EXCEPTION;
    private final static QName JAXWSMAIL_QNAME = new QName("http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", "JaxwsMail");

    static {
            JAXWSMAIL_WSDL_LOCATION = com.sirius.mailws.mail.wsdl.JaxwsMail_Service.class.getResource("/WEB-INF/wsdl/JaxwsMail.wsdl");
        WebServiceException e = null;
        if (JAXWSMAIL_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/JaxwsMail.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        JAXWSMAIL_EXCEPTION = e;
    }

    public JaxwsMail_Service() {
        super(__getWsdlLocation(), JAXWSMAIL_QNAME);
    }

    public JaxwsMail_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), JAXWSMAIL_QNAME, features);
    }

    public JaxwsMail_Service(URL wsdlLocation) {
        super(wsdlLocation, JAXWSMAIL_QNAME);
    }

    public JaxwsMail_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, JAXWSMAIL_QNAME, features);
    }

    public JaxwsMail_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public JaxwsMail_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns JaxwsMail
     */
    @WebEndpoint(name = "JaxwsMail")
    public JaxwsMail getJaxwsMail() {
        return super.getPort(new QName("http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", "JaxwsMail"), JaxwsMail.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns JaxwsMail
     */
    @WebEndpoint(name = "JaxwsMail")
    public JaxwsMail getJaxwsMail(WebServiceFeature... features) {
        return super.getPort(new QName("http://jaxws.mail.service.order.sirius.com/jaxws/JaxwsMail/wsdl", "JaxwsMail"), JaxwsMail.class, features);
    }

    private static URL __getWsdlLocation() {
        if (JAXWSMAIL_EXCEPTION!= null) {
            throw JAXWSMAIL_EXCEPTION;
        }
        return JAXWSMAIL_WSDL_LOCATION;
    }

}