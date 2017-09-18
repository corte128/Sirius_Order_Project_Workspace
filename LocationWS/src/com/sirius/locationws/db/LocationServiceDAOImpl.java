package com.sirius.locationws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

import com.sirius.locationws.beans.LocationBean;


public class LocationServiceDAOImpl {
	
//	private static final Logger logger = Logger.getLogger(LocationServiceDAOImpl.class
//			.getName());
	Connection conn = null;
	
	public LocationServiceDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	public List<LocationBean> getLocations(){
		ArrayList<LocationBean> locations = new ArrayList<LocationBean>();
		try {
			System.out.println("Getting Locations");
			String sql = "SELECT * FROM location_tbl JOIN state_tbl ON state_id_fk = state_id_pk";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				LocationBean l = new LocationBean();
				l.setId(rs.getInt("location_id_pk"));
				l.setNumberOfEmployees(rs.getInt("number_of_employees"));
				l.setCity(rs.getString("location_city"));
				l.setState(rs.getString("state_abbr"));
				locations.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locations;
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
}
