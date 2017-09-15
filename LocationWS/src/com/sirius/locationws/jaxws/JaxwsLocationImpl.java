package com.sirius.locationws.jaxws;

import java.util.List;

import com.sirius.locationws.beans.LocationBean;
import com.sirius.locationws.db.LocationServiceDAO;

public class JaxwsLocationImpl implements Location{

	@Override
	public List<LocationBean> getLocations() {
		LocationServiceDAO dao = new LocationServiceDAO();
		return dao.getLocations();
	}

}
