package com.sirius.order.client.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sirius.product.service.main.product.wsdl.ProductBean;
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;
import com.sirius.service.cart.cart.wsdl.BudgetBean;
import com.sirius.service.cart.cart.wsdl.CartServiceDAO;
import com.sirius.service.cart.cart.wsdl.OrderBean;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//created by logged in user
		//current budget
		//order id
		String action = request.getParameter("action");
    	int userId = (Integer) request.getSession().getAttribute("activeUserID");
		if(action.equals("removeFromCart")){
	    	int orderId = Integer.parseInt(request.getParameter("orderId"));
			CartServiceDAO.removeProductFromCart(orderId, userId);
		}
		else{
			String quantity = request.getParameter("quantity");
	    	int productId = Integer.parseInt(request.getParameter("productID"));
			OrderBean orderBean = new OrderBean();
			orderBean.setOrderName("cart");
			orderBean.setProductId(productId);
			ProductBean bean = ProductSearchDAO.getProductByID(productId);
			int count = 0;
			int locationId = 0;
			if (quantity.equals("")){
				quantity = "1";
			}
			int quantityInt = Integer.parseInt(quantity);
			//=========================
	    	//activeUserLocation
			locationId = (Integer) request.getSession().getAttribute("activeUserLocation");
			count = CartServiceDAO.getProductQuantityInCartByProductId(locationId, productId);
			if(count == 0){
				orderBean.setQuantity(quantityInt);
				orderBean.setTotalPrice(bean.getPrice().multiply(new BigDecimal(orderBean.getQuantity())));
			}
			else{
				orderBean.setQuantity(++count);
				orderBean.setTotalPrice(bean.getPrice().multiply(new BigDecimal(orderBean.getQuantity())));
			}
			//============================
//			CartServiceDAO.updateProductQuantityInCart(locationId, quantity, productId, updatedBy)
			BudgetBean budget = new BudgetBean();
			budget.setBudgetAllotted(new BigDecimal(1000));
			budget.setBudgetRecommended(new BigDecimal(800));
			budget.setLocationId(1);
			
			GregorianCalendar gCal = new GregorianCalendar();
			gCal.setTime(new Date());

			XMLGregorianCalendar calendar = null;
			try {
				calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			budget.setBudgetDate(calendar);
			
			if(count > 0){
				CartServiceDAO.updateProductQuantityInCart(locationId, orderBean.getQuantity(), productId, userId);
			}
			else{
				CartServiceDAO.addProductToCart(orderBean, budget, userId);
			}
//			CartServiceDAO.updateProductQuantityInCart(locationId, quantity, productId, updatedBy)
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//created by logged in user
		//current budget
		//order id

		
//		CartServiceDAO.getOrderByProduct(id);
//		CartServiceDAO.updateProductQuantityInCart(locationId, quantity, productId, updatedBy)
//		CartServiceDAO.addProductToCart(order, budget, createdBy)

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsps/productSearch.jsp");
		dispatcher.forward(request, response);
	}

}
