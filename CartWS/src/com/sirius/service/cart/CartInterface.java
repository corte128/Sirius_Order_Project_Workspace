package com.sirius.service.cart;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.service.cart.bean.OrderBean;

/**
 * Cart Services
 * Adds product to cart
 * 
 * @author Scout Martinelli and Braden Filler
 *
 */
@WebService(name="CartService", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
public interface CartInterface {
	
	/**
	 * adds the product to the order_tbl
	 * @param productId
	 * @return boolean
	 */
	@WebMethod(action = "AddProductToCart")
	@WebResult(name = "AddProductToCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "AddProductToCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.AddProductToCart")
	@ResponseWrapper(localName = "AddProductToCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.AddProductToCartResponse")
	public boolean addProductToCart(
			@WebParam(name="productId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int productId
			);
	
	/**
	 * gets all the products based on the given orderName
	 * @param orderName
	 * @return OrderBean[]
	 */
	@WebMethod(action = "GetAllProductsInCart")
	@WebResult(name = "GetAllProductsInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "GetAllProductsInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCart")
	@ResponseWrapper(localName = "GetAllProductsInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCartResponse")
	public OrderBean[] getAllProductsInCart(
			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String orderName
			);
	
//	@WebMethod(action = "UpdateCart")
//	@WebResult(name = "GetAllProductsInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
//	@RequestWrapper(localName = "GetAllProductsInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCart")
//	@ResponseWrapper(localName = "GetAllProductsInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCartResponse")
//	public OrderBean[] getAllProductsInCart(
//			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
//			String orderName
//			);
	

}
