package com.sirius.locationws.location.wsdl;

import java.util.List;

public class LocationClientDAO {
	
	public List<LocationBean> getLocations(){
		LocationProxy client = new LocationProxy();
		return client.getLocations();
	}
	
}
