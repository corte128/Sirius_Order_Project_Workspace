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
	 * @param order
	 * @param createdBy
	 * @return boolean
	 */
	@WebMethod(action = "addProductToCart")
	@WebResult(name = "addProductToCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "addProductToCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.AddProductToCart")
	@ResponseWrapper(localName = "addProductToCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.AddProductToCartResponse")
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
	@WebMethod(action = "getAllProductsInCart")
	@WebResult(name = "getAllProductsInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "getAllProductsInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCart")
	@ResponseWrapper(localName = "getAllProductsInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCartResponse")
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
	@WebMethod(action = "updateProductQuantityInCart")
	@WebResult(name = "updateProductQuantityInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "updateProductQuantityInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.UpdateProductQuantityInCart")
	@ResponseWrapper(localName = "updateProductQuantityInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.UpdateProductQuantityInCartResponse")
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
	@WebMethod(action = "removeProductFromCart")
	@WebResult(name = "removeProductFromCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "removeProductFromCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.RemoveProductFromCart")
	@ResponseWrapper(localName = "removeProductFromCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.RemoveProductFromCartResponse")
	public boolean removeProductFromCart(
			@WebParam(name="orderId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int orderId,
			@WebParam(name="updatedBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int updatedBy
			);
	
	/**
	 * Saves the order based on the ordername
	 * @param orderName
	 * @param budget
	 * @param locationId
	 * @param createdBy
	 * @return boolean
	 */
	@WebMethod(action = "saveOrder")
	@WebResult(name = "saveOrderReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "saveOrder", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.SaveOrder")
	@ResponseWrapper(localName = "saveOrderResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.SaveOrderResponse")
	public boolean saveOrder(
			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String orderName,
			@WebParam(name="budget", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			BudgetBean budget,
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId,
			@WebParam(name="createdBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int createdBy
			);
	
	/**
	 * Get the order based on the order name 
	 * @param orderName
	 * @param locationId
	 * @return List<OrderBean>
	 */
	@WebMethod(action = "getOrderByOrderName")
	@WebResult(name = "getOrderByOrderNameReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "getOrderByOrderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetOrderByOrderName")
	@ResponseWrapper(localName = "getOrderByOrderNameResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetOrderByOrderNameResponse")
	public List<OrderBean> getOrderByOrderName(
			@WebParam(name="orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String orderName,
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId
			);
	
	/**
	 * gets all the products based on the given orderName
	 * @param locationId
	 * @param productType
	 * @return OrderBean[]
	 */
	@WebMethod(action = "getAllProductsInCartByProductType")
	@WebResult(name = "getAllProductsInCartByProductTypeReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "getAllProductsInCartByProductType", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCartByProductType")
	@ResponseWrapper(localName = "getAllProductsInCartByProductTypeResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetAllProductsInCartByProductTypeResponse")
	public List<OrderBean> getAllProductsInCartByProductType(
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId,
			@WebParam(name="productType", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			String productType
			);
	
	/**
	 * checks if a product with the given product id is in the cart
	 * returns true if product is in cart
	 * @param locationId
	 * @param productId
	 * @return boolean
	 */
	@WebMethod(action = "getProductQuantityInCartByProductId")
	@WebResult(name = "getProductQuantityInCartByProductIdReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
	@RequestWrapper(localName = "getProductQuantityInCartByProductId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetProductQuantityInCartByProductId")
	@ResponseWrapper(localName = "getProductQuantityInCartByProductIdResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.GetProductQuantityInCartByProductIdResponse")
	public int getProductQuantityInCartByProductId(
			@WebParam(name="locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int locationId,
			@WebParam(name="productId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
			int productID
			);
}
