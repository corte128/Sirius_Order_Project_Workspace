package com.sirius.wishlistws.jaxws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.wishlistws.beans.EmployeeBean;
import com.sirius.wishlistws.beans.ProductBean;

@WebService(name = "Wishlist", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl")
public interface Wishlist {
	
	@WebMethod(action = "addToLikeTable")
	@WebResult(name = "addToLikeTableReturn", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl")
	@RequestWrapper(localName = "addToLikeTable", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.addToLikeTable")
	@ResponseWrapper(localName = "addToLikeTableResponse", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.addToLikeTableResponse")
	public void addToLikeTable(
			@WebParam(name = "employee_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int employee_id,
			@WebParam(name = "product_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int product_id);
	
	@WebMethod(action = "getAllEmployeesWhoLikedProduct")
	@WebResult(name = "getAllEmployeesWhoLikedProductReturn", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl")
	@RequestWrapper(localName = "getAllEmployeesWhoLikedProduct", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.getAllEmployeesWhoLikedProduct")
	@ResponseWrapper(localName = "getAllEmployeesWhoLikedProductResponse", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.getAllEmployeesWhoLikedProductResponse")
	public List<EmployeeBean> getAllEmployeesWhoLikedProduct(
			@WebParam(name = "product_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int product_id,
			@WebParam(name = "location_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int location_id);
	
	@WebMethod(action = "getAllProductsEmployeeLiked")
	@WebResult(name = "getAllProductsEmployeeLikedReturn", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl")
	@RequestWrapper(localName = "getAllProductsEmployeeLiked", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.getAllProductsEmployeeLiked")
	@ResponseWrapper(localName = "getAllProductsEmployeeLikedResponse", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.getAllProductsEmployeeLikedResponse")
	public List<ProductBean> getAllProductsEmployeeLiked(
			@WebParam(name = "employee_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int employee_id);
	
	@WebMethod(action = "removeFromEmployeeWishlist")
	@WebResult(name = "removeFromEmployeeWishlistReturn", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl")
	@RequestWrapper(localName = "removeFromEmployeeWishlist", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.removeFromEmployeeWishlist")
	@ResponseWrapper(localName = "removeFromEmployeeWishlistResponse", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl", className = "com.sirius.wishlistws.jaxws.removeFromEmployeeWishlistResponse")
	public void removeFromEmployeeWishlist(
			@WebParam(name = "employee_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int employee_id,
			@WebParam(name = "product_id", targetNamespace = "http://wishlistws.sirius.com/wishlist/wsdl") int product_id);
	
}
