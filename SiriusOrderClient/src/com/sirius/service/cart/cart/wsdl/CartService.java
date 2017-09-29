//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.sirius.service.cart.cart.wsdl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "CartService", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CartService {


    /**
     * 
     * @param budget
     * @param order
     * @param createdBy
     * @return
     *     returns boolean
     */
    @WebMethod(action = "addProductToCart")
    @WebResult(name = "addProductToCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "addProductToCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.AddProductToCart")
    @ResponseWrapper(localName = "addProductToCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.AddProductToCartResponse")
    @Action(input = "addProductToCart", output = "http://cart.service.sirius.com/cart/wsdl/CartService/addProductToCartResponse")
    public boolean addProductToCart(
        @WebParam(name = "order", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        OrderBean order,
        @WebParam(name = "budget", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        BudgetBean budget,
        @WebParam(name = "createdBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int createdBy);

    /**
     * 
     * @param locationId
     * @return
     *     returns java.util.List<com.sirius.service.cart.cart.wsdl.OrderBean>
     */
    @WebMethod(action = "getAllProductsInCart")
    @WebResult(name = "getAllProductsInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "getAllProductsInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetAllProductsInCart")
    @ResponseWrapper(localName = "getAllProductsInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetAllProductsInCartResponse")
    @Action(input = "getAllProductsInCart", output = "http://cart.service.sirius.com/cart/wsdl/CartService/getAllProductsInCartResponse")
    public List<OrderBean> getAllProductsInCart(
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId);

    /**
     * 
     * @param locationId
     * @param updatedBy
     * @param productId
     * @param quantity
     * @return
     *     returns boolean
     */
    @WebMethod(action = "updateProductQuantityInCart")
    @WebResult(name = "updateProductQuantityInCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "updateProductQuantityInCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.UpdateProductQuantityInCart")
    @ResponseWrapper(localName = "updateProductQuantityInCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.UpdateProductQuantityInCartResponse")
    @Action(input = "updateProductQuantityInCart", output = "http://cart.service.sirius.com/cart/wsdl/CartService/updateProductQuantityInCartResponse")
    public boolean updateProductQuantityInCart(
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId,
        @WebParam(name = "quantity", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int quantity,
        @WebParam(name = "productId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int productId,
        @WebParam(name = "updatedBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int updatedBy);

    /**
     * 
     * @param updatedBy
     * @param orderId
     * @return
     *     returns boolean
     */
    @WebMethod(action = "removeProductFromCart")
    @WebResult(name = "removeProductFromCartReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "removeProductFromCart", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.RemoveProductFromCart")
    @ResponseWrapper(localName = "removeProductFromCartResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.RemoveProductFromCartResponse")
    @Action(input = "removeProductFromCart", output = "http://cart.service.sirius.com/cart/wsdl/CartService/removeProductFromCartResponse")
    public boolean removeProductFromCart(
        @WebParam(name = "orderId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int orderId,
        @WebParam(name = "updatedBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int updatedBy);

    /**
     * 
     * @param locationId
     * @param budget
     * @param productIdList
     * @param orderName
     * @param createdBy
     * @return
     *     returns boolean
     */
    @WebMethod(action = "saveOrder")
    @WebResult(name = "saveOrderReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "saveOrder", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.SaveOrder")
    @ResponseWrapper(localName = "saveOrderResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.SaveOrderResponse")
    @Action(input = "saveOrder", output = "http://cart.service.sirius.com/cart/wsdl/CartService/saveOrderResponse")
    public boolean saveOrder(
        @WebParam(name = "productIdList", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        List<Integer> productIdList,
        @WebParam(name = "orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        String orderName,
        @WebParam(name = "budget", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        BudgetBean budget,
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId,
        @WebParam(name = "createdBy", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int createdBy);

    /**
     * 
     * @param locationId
     * @param orderName
     * @return
     *     returns java.util.List<com.sirius.service.cart.cart.wsdl.OrderBean>
     */
    @WebMethod(action = "getOrderByOrderName")
    @WebResult(name = "getOrderByOrderNameReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "getOrderByOrderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetOrderByOrderName")
    @ResponseWrapper(localName = "getOrderByOrderNameResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetOrderByOrderNameResponse")
    @Action(input = "getOrderByOrderName", output = "http://cart.service.sirius.com/cart/wsdl/CartService/getOrderByOrderNameResponse")
    public List<OrderBean> getOrderByOrderName(
        @WebParam(name = "orderName", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        String orderName,
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId);

    /**
     * 
     * @param locationId
     * @param productType
     * @return
     *     returns java.util.List<com.sirius.service.cart.cart.wsdl.OrderBean>
     */
    @WebMethod(action = "getAllProductsInCartByProductType")
    @WebResult(name = "getAllProductsInCartByProductTypeReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "getAllProductsInCartByProductType", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetAllProductsInCartByProductType")
    @ResponseWrapper(localName = "getAllProductsInCartByProductTypeResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetAllProductsInCartByProductTypeResponse")
    @Action(input = "getAllProductsInCartByProductType", output = "http://cart.service.sirius.com/cart/wsdl/CartService/getAllProductsInCartByProductTypeResponse")
    public List<OrderBean> getAllProductsInCartByProductType(
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId,
        @WebParam(name = "productType", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        String productType);

    /**
     * 
     * @param locationId
     * @param productId
     * @return
     *     returns int
     */
    @WebMethod(action = "getProductQuantityInCartByProductId")
    @WebResult(name = "getProductQuantityInCartByProductIdReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "getProductQuantityInCartByProductId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetProductQuantityInCartByProductId")
    @ResponseWrapper(localName = "getProductQuantityInCartByProductIdResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetProductQuantityInCartByProductIdResponse")
    @Action(input = "getProductQuantityInCartByProductId", output = "http://cart.service.sirius.com/cart/wsdl/CartService/getProductQuantityInCartByProductIdResponse")
    public int getProductQuantityInCartByProductId(
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId,
        @WebParam(name = "productId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int productId);

    /**
     * 
     * @param locationId
     * @return
     *     returns java.util.List<com.sirius.service.cart.cart.wsdl.OrderBean>
     */
    @WebMethod(action = "getAllSavedOrders")
    @WebResult(name = "getAllSavedOrdersReturn", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
    @RequestWrapper(localName = "getAllSavedOrders", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetAllSavedOrders")
    @ResponseWrapper(localName = "getAllSavedOrdersResponse", targetNamespace = "http://cart.service.sirius.com/cart/wsdl", className = "com.sirius.service.cart.cart.wsdl.GetAllSavedOrdersResponse")
    @Action(input = "getAllSavedOrders", output = "http://cart.service.sirius.com/cart/wsdl/CartService/getAllSavedOrdersResponse")
    public List<OrderBean> getAllSavedOrders(
        @WebParam(name = "locationId", targetNamespace = "http://cart.service.sirius.com/cart/wsdl")
        int locationId);

}
