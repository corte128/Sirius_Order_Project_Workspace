package com.sirius.service.product.main;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.service.product.beans.ProductBean;

/**
 * Product interface
 * 
 * @author William Cortes
 */
@WebService(name="Product", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
public interface Product 
{
	/**
	 * Gets a product by ID
	 * @param ID
	 */
	@WebMethod(action = "getProductByID")
	@WebResult(name = "getProductByIDReturn", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
	@RequestWrapper(localName = "getProductByID", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetProductByID")
	@ResponseWrapper(localName = "getProductByIDResponse", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetProductByIDResponse")
	public ProductBean getProductByID(
			@WebParam(name="ID", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
			int ID
			);
	
	/**
	 * Gets all products by type
	 * @param type
	 */
	@WebMethod(action = "getAllProductsByType")
	@WebResult(name = "getAllProductsByTypeReturn", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
	@RequestWrapper(localName = "getAllProductsByType", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetAllProductsByType")
	@ResponseWrapper(localName = "getAllProductByTypeResponse", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetAllProductsByTypeResponse")
	public ProductBean[] getAllProductsByType(
			@WebParam(name="type", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
			String type
			);
	
	/**
	 * Gets all products by name
	 * @param name
	 */
	@WebMethod(action = "getAllProductsByName")
	@WebResult(name = "getAllProductsByNameReturn", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
	@RequestWrapper(localName = "getAllProductsByName", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetAllProductsByName")
	@ResponseWrapper(localName = "getAllProductsByNameResponse", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetAllProductsByNameResponse")
	public ProductBean[] getAllProductsByName(
			@WebParam(name="name", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
			String name
			);
	
	/**
	 * Gets all products by name and type
	 * @param name
	 * @param type
	 */
	@WebMethod(action = "getAllProductsByNameAndType")
	@WebResult(name = "getAllProductsByNameAndTypeReturn", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
	@RequestWrapper(localName = "getAllProductsByNameAndType", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetAllProductsByNameAndType")
	@ResponseWrapper(localName = "getAllProductsByNameAndTypeResponse", targetNamespace = "http://main.service.product.sirius.com/product/wsdl", 
		className = "com.sirius.service.product.main.GetAllProductsByNameAndTypeResponse")
	public ProductBean[] getAllProductsByNameAndType(
			@WebParam(name="name", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
			String name,
			@WebParam(name="type", targetNamespace = "http://main.service.product.sirius.com/product/wsdl")
			String type
			);
}