package com.sirius.locationws.jaxws;

import java.util.List;

import javax.jws.WebService;

import com.sirius.locationws.beans.LocationBean;
import com.sirius.locationws.db.LocationServiceDAO;

@WebService(endpointInterface = "com.sirius.locationws.jaxws.Location", portName = "location", targetNamespace = "http://locationws.sirius.com/location/wsdl", serviceName = "JaxwsLocation")
public class JaxwsLocationImpl implements Location{

	@Override
	public List<LocationBean> getLocations() {
		LocationServiceDAO dao = new LocationServiceDAO();
		return dao.getLocations();
	}

}
