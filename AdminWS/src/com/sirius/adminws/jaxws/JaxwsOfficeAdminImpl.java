package com.sirius.adminws.jaxws;

import java.util.List;

import javax.jws.WebService;

import com.sirius.adminws.beans.Holiday;
import com.sirius.adminws.db.OfficeAdminServiceDAO;

@WebService(endpointInterface = "com.sirius.adminws.jaxws.OfficeAdmin", portName = "officeAdmin", targetNamespace = "http://adminws.sirius.com/officeAdmin/wsdl", serviceName = "JaxwsOfficeAdmin")
public class JaxwsOfficeAdminImpl implements OfficeAdmin {

	@Override
	public boolean addVisitors(String startDate, String endDate, int count,
			String comment, int userID, int locationID) {
		OfficeAdminServiceDAO dao = new OfficeAdminServiceDAO();
		return dao.addVisitors(startDate, endDate, count, comment, userID, locationID);
	}

	@Override
	public boolean deleteHoliday(int holidayID, int userID) {
		OfficeAdminServiceDAO dao = new OfficeAdminServiceDAO();
		return dao.deleteHoliday(holidayID, userID);
	}

	@Override
	public boolean addHoliday(String holidayName, String date, int userID, int locationID) {
		OfficeAdminServiceDAO dao = new OfficeAdminServiceDAO();
		return dao.addHoliday(holidayName, date, userID, locationID);
	}

	@Override
	public List<Holiday> getAllHolidays(int locationID) {
		OfficeAdminServiceDAO dao = new OfficeAdminServiceDAO();
		return dao.getAllHolidays(locationID);
	}

}
