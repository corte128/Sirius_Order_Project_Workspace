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
	 * adds an order record to the table
	 * @param orderName
	 * @param createdBy
	 * @return boolean
	 */
	@WebMethod(action = "AddProductToCart")
	@WebResult(name = "AddProductToCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "AddProductToCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.AddProductToCart")
	@ResponseWrapper(localName = "AddProductToCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.AddProductToCartResponse")
	public boolean addProductToCart(
			@WebParam(name="order", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			OrderBean orderName,
			@WebParam(name="createdBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int createdBy
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
	
	/**
	 * updates the quantity of the product
	 * @param orderName
	 * @param quantity
	 * @return boolean
	 */
	@WebMethod(action = "UpdateProductQuantity")
	@WebResult(name = "UpdateProductQuantityReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "UpdateProductQuantity", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.UpdateProductQuantity")
	@ResponseWrapper(localName = "UpdateProductQuantityResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.UpdateProductQuantityResponse")
	public boolean updateProductQuantity(
			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String orderName,
			@WebParam(name="quantity", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int quantity
			);
	
	/**
	 * updates the quantity of the product
	 * @param orderName
	 * @param quantity
	 * @return boolean
	 */
	@WebMethod(action = "RemoveProductFromCart")
	@WebResult(name = "RemoveProductFromCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "RemoveProductFromCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.RemoveProductFromCart")
	@ResponseWrapper(localName = "RemoveProductFromCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.RemoveProductFromCartResponse")
	public boolean removeProductFromCart(
			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String orderName,
			@WebParam(name="productId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int productId
			);
	
	@WebMethod(action = "SaveCart")
	@WebResult(name = "SaveCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "SaveCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.SaveCart")
	@ResponseWrapper(localName = "SaveCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.SaveCartResponse")
	public boolean saveCart();
	

}
