package com.sirius.loctionws.location.wsdl;

import java.util.List;

import com.sirius.loctionws.location.wsdl.LocationBean;

public class LocationClientDao {
	LocationProxy proxy = new LocationProxy();
	
	public List<LocationBean> getLocations() {
		return proxy.getLocations();
	}
}
