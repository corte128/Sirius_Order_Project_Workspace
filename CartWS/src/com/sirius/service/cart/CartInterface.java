package com.sirius.service.cart;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.service.cart.bean.BudgetBean;
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
			OrderBean order,
			@WebParam(name="budget", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			BudgetBean budget,
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
	public List<OrderBean> getAllProductsInCart(
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId
			);
	
	/**
	 * updates the quantity of the product
	 * @param orderName
	 * @param quantity
	 * @return boolean
	 */
	@WebMethod(action = "UpdateProductQuantityInCart")
	@WebResult(name = "UpdateProductQuantityInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "UpdateProductQuantityInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.UpdateProductQuantityInCart")
	@ResponseWrapper(localName = "UpdateProductQuantityInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.UpdateProductQuantityInCartResponse")
	public boolean updateProductQuantityInCart(
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId,
			@WebParam(name="quantity", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int quantity,
			@WebParam(name="productId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int productId,
			@WebParam(name="updatedBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int updatedBy
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
			@WebParam(name="orderId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int orderId
			);
	
	/**
	 * saves the order
	 * @param orderName
	 * @return
	 */
	@WebMethod(action = "SaveOrder")
	@WebResult(name = "SaveOrderReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "SaveOrder", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.SaveOrder")
	@ResponseWrapper(localName = "SaveOrderResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.SaveOrderResponse")
	public boolean saveOrder(
			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String orderName,
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId,
			@WebParam(name="createdBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int createdBy
			);
	

}
