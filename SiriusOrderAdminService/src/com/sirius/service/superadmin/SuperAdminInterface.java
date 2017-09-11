package com.sirius.service.superadmin;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.service.bean.LocationBean;

/**
 * SuperAdmin interface
 * 
 * @author Scout Martinelli
 */
@WebService(name="SuperAdmin", targetNamespace = "http://superadmin.service.sirius.com/admin/wsdl")
public interface SuperAdminInterface {

	/**
	 * Sets the budget for a certain location
	 * @param budget
	 * @param locationId
	 */
	@WebMethod(action = "SetBudgetByLocation")
	@WebResult(name = "SetBudgetByLocationReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "SetBudgetByLocation", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.SetBudgetByLocation")
	@ResponseWrapper(localName = "SetBudgetByLocationResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.SetBudgetByLocationResponse")
	public void setBudgetByLocation(
			@WebParam(name="budget", targetNamespace = "http://superadmin.service.sirius.com/profile/wsdl")
			BigDecimal budget,
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/profile/wsdl")
			int locationId
			);
	
	/**
	 * Gets the budget for a certain location
	 * @param budget
	 * @param locationId
	 */
	@WebMethod(action = "GetBudgetByLocation")
	@WebResult(name = "GetBudgetByLocationReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "GetBudgetByLocation", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.GetBudgetByLocation")
	@ResponseWrapper(localName = "GetBudgetByLocationResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.GetBudgetByLocationResponse")
	public BigDecimal getBudgetByLocation(
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/profile/wsdl")
			int locationId
			);
	
	/**
	 * Gets the budget for a certain location
	 * @param budget
	 * @param locationId
	 */
	@WebMethod(action = "AddLocation")
	@WebResult(name = "AddLocationReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "AddLocation", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.AddLocation")
	@ResponseWrapper(localName = "AddLocationResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.AddLocation")
	public void addLocation(
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/profile/wsdl")
			LocationBean location
			);	

	/**
	 * Assigns the admin to a location
	 * @param locationId
	 * @param adminId
	 */
	@WebMethod(action = "AssignAdmin")
	@WebResult(name = "AssignAdminReturn", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl")
	@RequestWrapper(localName = "AssignAdmin", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.AssignAdmin")
	@ResponseWrapper(localName = "AssignAdminResponse", targetNamespace = "http://superadmin.service.sirius.com/superadmin/wsdl", className = "com.sirius.service.superadmin.AssignAdmin")
	public void assignAdmin(
			@WebParam(name="locationId", targetNamespace = "http://superadmin.service.sirius.com/profile/wsdl")
			int locationId,
			@WebParam(name="adminId", targetNamespace = "http://superadmin.service.sirius.com/profile/wsdl")
			int adminId
			);
}
