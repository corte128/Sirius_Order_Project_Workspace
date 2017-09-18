package com.sirius.locationws.jaxws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.locationws.beans.LocationBean;

@WebService(name = "Location", targetNamespace = "http://locationws.sirius.com/location/wsdl")
public interface Location {
	
	@WebMethod(action = "getLocations")
	@WebResult(name = "getLocationsReturn", targetNamespace = "http://locationws.sirius.com/location/wsdl")
	@RequestWrapper(localName = "getLocations", targetNamespace = "http://locationws.sirius.com/location/wsdl", className = "com.sirius.locationws.jaxws.GetLocations")
	@ResponseWrapper(localName = "getLocationsResponse", targetNamespace = "http://locationws.sirius.com/location/wsdl", className = "com.sirius.locationws.jaxws.GetLocationsResponse")
	public List<LocationBean> getLocations();
	
	
}
