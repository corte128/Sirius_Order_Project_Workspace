package com.sirius.service.superadmin;

import java.math.BigDecimal;

import javax.jws.WebService;

@WebService(endpointInterface="com.sirius.service.superadmin.SuperAdminInterface",
portName="profile", targetNamespace ="http://services.back.sirius.com/profile/wsdl",
serviceName="ProfileService")
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
	public boolean addLocation(String city, String state) {
		 return SuperAdminDAO.addLocation(city, state);
	}

	@Override
	public boolean assignAdmin(int locationId, int adminId) {
		return SuperAdminDAO.assignAdmin(locationId, adminId);
	}

}
