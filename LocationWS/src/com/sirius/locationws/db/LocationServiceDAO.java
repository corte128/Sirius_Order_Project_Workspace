package com.sirius.locationws.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.locationws.beans.LocationBean;

public class LocationServiceDAO {
	
	private static final Logger logger = Logger.getLogger(LocationServiceDAO.class
			.getName());
	
	public List<LocationBean> getLocations() {
		logger.log(Level.FINE, "Getting all locations");
		Connection conn = null;
		List<LocationBean> locations = null;
		try {
			conn = DBConnection.getConnection();
			LocationServiceDAOImpl impl = new LocationServiceDAOImpl(conn);
			locations = impl.getLocations();
			impl.closeConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locations;
	}

	public String getLocationStringByLocationId(int locationId) {
		logger.log(Level.FINE, "Getting location string by Id");
		Connection conn = null;
		String location = "";
		try {
			conn = DBConnection.getConnection();
			LocationServiceDAOImpl impl = new LocationServiceDAOImpl(conn);
			location = impl.getLocationStringByLocationId(locationId);
			impl.closeConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}
	
}
