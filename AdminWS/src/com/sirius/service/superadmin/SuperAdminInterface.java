package com.sirius.service.superadmin;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.adminws.beans.OfficeBean;

/**
 * SuperAdmin interface
 * 
 * @author Scout Martinelli
 */
@WebService(name="SuperAdmin", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
public interface SuperAdminInterface {

	/**
	 * Sets the budget for a certain location
	 * @param budget
	 * @param locationId
	 */
	@WebMethod(action = "setBudgetByLocation")
	@WebResult(name = "setBudgetByLocationReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "setBudgetByLocation", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.back.services.setBudgetByLocation")
	@ResponseWrapper(localName = "setBudgetByLocationResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.back.services.setBudgetByLocationResponse")
	public boolean setBudgetByLocation(
			@WebParam(name="budget", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			BigDecimal budget,
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			int locationId
			);
	
	/**
	 * Gets the budget for a certain location
	 * @param budget
	 * @param locationId
	 */
	@WebMethod(action = "getBudgetByLocation")
	@WebResult(name = "getBudgetByLocationReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "getBudgetByLocation", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getBudgetByLocation")
	@ResponseWrapper(localName = "getBudgetByLocationResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getBudgetByLocationResponse")
	public BigDecimal getBudgetByLocation(
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			int locationId
			);
	
	/**
	 * Gets the budget for a certain location
	 * @param budget
	 * @param locationId
	 * @param creatorId
	 */
	@WebMethod(action = "addLocation")
	@WebResult(name = "addLocationReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "addLocation", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.addLocation")
	@ResponseWrapper(localName = "addLocationResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.addLocationResponse")
	public boolean addLocation(
			@WebParam(name="city", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			String city,
			@WebParam(name="state", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			String state,
			@WebParam(name="creatorId", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			int creatorId
			);	

	/**
	 * Assigns the admin to a location
	 * @param locationId
	 * @param adminId
	 * @param updaterId
	 */
	@WebMethod(action = "assignAdmin")
	@WebResult(name = "assignAdminReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "assignAdmin", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.assignAdmin")
	@ResponseWrapper(localName = "assignAdminResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.assignAdminResponse")
	public boolean assignAdmin(
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			int locationId,
			@WebParam(name="adminId", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			int adminId,
			@WebParam(name="updaterId", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			int updaterId
			);
	
	/**
	 * Gets all the offices for the super admin page
	 * @return List<OfficeBean>
	 */
	@WebMethod(action = "getOffices")
	@WebResult(name = "getOfficesReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "getOffices", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getOffices")
	@ResponseWrapper(localName = "getOfficesResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getOfficesResponse")
	public List<OfficeBean> getOffices();
	
	/**
	 * gets the user id by name
	 * @param name
	 * @return int
	 */
	@WebMethod(action = "getEmployeeIdByName")
	@WebResult(name = "getEmployeeIdByNameReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "getEmployeeIdByName", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getEmployeeIdByName")
	@ResponseWrapper(localName = "getEmployeeIdByNameResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getEmployeeIdByNameResponse")
	public int getEmployeeIdByName(
			@WebParam(name="name", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
			String name
			);
	
	/**
	 * gets the user id by name
	 * @return List<String>
	 */
	@WebMethod(action = "getOfficeAdminNames")
	@WebResult(name = "getOfficeAdminNamesReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "getOfficeAdminNames", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getOfficeAdminNames")
	@ResponseWrapper(localName = "getOfficeAdminNamesResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.getOfficeAdminNamesResponse")
	public List<String> getOfficeAdminNames();
}
