//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.loctionws.location.wsdl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Location", targetNamespace = "http://locationws.sirius.com/location/wsdl")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Location {


    /**
     * 
     * @return
     *     returns java.util.List<com.sirius.loctionws.location.wsdl.LocationBean>
     */
    @WebMethod(action = "getLocations")
    @WebResult(name = "getLocationsReturn", targetNamespace = "http://locationws.sirius.com/location/wsdl")
    @RequestWrapper(localName = "getLocations", targetNamespace = "http://locationws.sirius.com/location/wsdl", className = "com.sirius.loctionws.location.wsdl.GetLocations")
    @ResponseWrapper(localName = "getLocationsResponse", targetNamespace = "http://locationws.sirius.com/location/wsdl", className = "com.sirius.loctionws.location.wsdl.GetLocationsResponse")
    @Action(input = "getLocations", output = "http://locationws.sirius.com/location/wsdl/Location/getLocationsResponse")
    public List<LocationBean> getLocations();

}
