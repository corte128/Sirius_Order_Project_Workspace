package com.sirius.service.superadmin;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import com.sirius.adminws.beans.OfficeBean;
import com.sirius.service.superadmin.database.SuperAdminDAO;

@WebService(endpointInterface="com.sirius.service.superadmin.SuperAdminInterface",
portName="superadmin", targetNamespace ="http://superadmin.service.sirius.com/superadmin/wsdl",
serviceName="SuperAdminService")
public class SuperAdminImplementation implements SuperAdminInterface {

	@Override
	public boolean setBudgetByLocation(BigDecimal budget, int locationId) {
		return SuperAdminDAO.setBudgetByLocation(budget,locationId);
	}

	@Override
	public BigDecimal getBudgetByLocation(int locationId) {
		return SuperAdminDAO.getBudgetByLocation(locationId);
	}

	@Override
	public boolean addLocation(String city, String state, int creatorId) {
		 return SuperAdminDAO.addLocation(city, state, creatorId);
	}

	@Override
	public boolean assignAdmin(int locationId, int adminId, int updateId) {
		return SuperAdminDAO.assignAdmin(locationId, adminId, updateId);
	}

	@Override
	public List<OfficeBean> getOffices() {
		return SuperAdminDAO.getOffices();
	}

	@Override
	public int getEmployeeIdByName(String name) {
		return SuperAdminDAO.getEmployeeIdByName(name);
	}

	@Override
	public List<String> getOfficeAdminNames() {
		return SuperAdminDAO.getOfficeAdminNames();
	}
}
