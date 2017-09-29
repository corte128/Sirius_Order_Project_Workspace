package com.sirius.service.generate;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;



@WebService(name="CartGenerateService", targetNamespace = "http://generate.service.sirius.com/cart/wsdl")
public interface GenerateInterface {

//	/**
//	 * generates the budget based on the location
//	 * @param locationId
//	 * @return BigDecimal
//	 */
//	@WebMethod(action = "generateBudget")
//	@WebResult(name = "generateBudgetReturn", targetNamespace = "http://generate.service.sirius.com/cart/wsdl")
//	@RequestWrapper(localName = "generateBudget", targetNamespace = "http://generate.service.sirius.com/cart/wsdl", className = "com.sirius.service.generate.generateBudget")
//	@ResponseWrapper(localName = "generateBudgetResponse", targetNamespace = "http://generate.service.sirius.com/cart/wsdl", className = "com.sirius.service.generate.generateBudgetResponse")
//	public BigDecimal generateBudget(
//			@WebParam(name="locationId", targetNamespace = "http://generate.service.sirius.com/cart/wsdl")
//			int locationId
//			);
	
	/**
	 * generates the cart based on the location
	 * @param locationId
	 * @return
	 */
	@WebMethod(action = "generateCart")
	@WebResult(name = "generateCartReturn", targetNamespace = "http://generate.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "generateCart", targetNamespace = "http://generate.service.sirius.com/cart/wsdl", className = "com.sirius.service.generate.generateCart")
	@ResponseWrapper(localName = "generateCartResponse", targetNamespace = "http://generate.service.sirius.com/cart/wsdl", className = "com.sirius.service.generate.generateCartResponse")
	public boolean generateCart(
			);
}
