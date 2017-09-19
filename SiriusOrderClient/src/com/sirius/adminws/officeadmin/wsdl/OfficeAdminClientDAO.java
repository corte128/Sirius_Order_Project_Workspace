package com.sirius.adminws.officeadmin.wsdl;

import java.util.List;

public class OfficeAdminClientDAO {
	
	public boolean addVisitors(String startDate, String endDate, int count,
			String comment, int userID, int locationID){
		OfficeAdminProxy client = new OfficeAdminProxy();
		return client.addVisitors(startDate, endDate, count, comment, userID, locationID);
	}
	
	public boolean addHoliday(String holidayName, String holidayDate, int userID, int locationID){
		OfficeAdminProxy client = new OfficeAdminProxy();
		return client.addHoliday(holidayName, holidayDate, userID, locationID);
	}
	
	public boolean deleteHoliday(int holidayID, int userID){
		OfficeAdminProxy client = new OfficeAdminProxy();
		return client.deleteHoliday(holidayID, userID);
	}
	
	public List<Holiday> getAllHolidays(int locationID){
		OfficeAdminProxy client = new OfficeAdminProxy();
		return client.getAllHolidays(locationID);
	}
	
	public List<EmployeeBean> getUnapprovedEmployees(int locationID){
		OfficeAdminProxy client = new OfficeAdminProxy();
		return client.getUnapprovedEmployees(locationID);
	}
	
}
