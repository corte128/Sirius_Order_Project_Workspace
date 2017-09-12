package com.sirius.wishlistws.jaxws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.wishlistws.beans.EmployeeBean;

@WebService(name = "Employee", targetNamespace = "http://ace.sirius.com/profile/wsdl")
public interface Wishlist {
	
	@WebMethod(action = "addToLikeTable")
	@WebResult(name = "addToLikeTableReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "addToLikeTable", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.addToLikeTable")
	@ResponseWrapper(localName = "addToLikeTableResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.addToLikeTableResponse")
	public void addToLikeTable(
			@WebParam(name = "employee_id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int employee_id,
			@WebParam(name = "product_id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int product_id);
	
//	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(int product_id)
	@WebMethod(action = "getAllEmployeesWhoLikedProduct")
	@WebResult(name = "getAllEmployeesWhoLikedProductReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "getAllEmployeesWhoLikedProduct", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.getAllEmployeesWhoLikedProduct")
	@ResponseWrapper(localName = "getAllEmployeesWhoLikedProductResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.getAllEmployeesWhoLikedProductResponse")
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(
			@WebParam(name = "product_id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int product_id);
	
	//	public List<ProductBean> getAllProductsEmployeeLiked(int employee_id){
	@WebMethod(action = "getAllProductsEmployeeLiked")
	@WebResult(name = "getAllProductsEmployeeLikedReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "getAllProductsEmployeeLiked", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.getAllProductsEmployeeLiked")
	@ResponseWrapper(localName = "getAllProductsEmployeeLikedResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.getAllProductsEmployeeLikedResponse")
	public void getAllProductsEmployeeLiked(
			@WebParam(name = "employee_id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int employee_id);
	
	//	public void removeFromEmployeeWishlist(int employee_id, int product_id){
	@WebMethod(action = "removeFromEmployeeWishlist")
	@WebResult(name = "removeFromEmployeeWishlistReturn", targetNamespace = "http://ace.sirius.com/profile/wsdl")
	@RequestWrapper(localName = "removeFromEmployeeWishlist", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.removeFromEmployeeWishlist")
	@ResponseWrapper(localName = "removeFromEmployeeWishlistResponse", targetNamespace = "http://ace.sirius.com/profile/wsdl", className = "com.sirius.ace.jaxws.removeFromEmployeeWishlistResponse")
	public void getAllProductsEmployeeLiked(
			@WebParam(name = "employee_id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int employee_id,
			@WebParam(name = "product_id", targetNamespace = "http://ace.sirius.com/profile/wsdl") int product_id);
	
}
