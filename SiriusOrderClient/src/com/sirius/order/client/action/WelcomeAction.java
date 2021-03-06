package com.sirius.order.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bouncycastle.util.Strings;

import com.sirius.product.service.main.product.wsdl.*;
import com.sirius.wishlistws.wishlist.wsdl.EmployeeBean;
import com.sirius.wishlistws.wishlist.wsdl.EmployeeLikeBean;
import com.sirius.wishlistws.wishlist.wsdl.WishlistDAO;

public class WelcomeAction extends Action {
	private static final Logger logger = Logger.getLogger(WelcomeAction.class.getName());
	private static final ResourceBundle sessionVariables = ResourceBundle
			.getBundle("com.sirius.order.client.properties.sessionVariables");
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
	
    //redirects based on if the user is logged in or not
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		int userId = 0;
		
		if(request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID")) != null)
		{	
			userId = (Integer) request.getSession().getAttribute(sessionVariables.getString("ACTIVE_USER_ID"));
		}
		
		
		
		if( userId > 0)
		{
			if(request.getParameter("action").equals("productSearch"))
			{
//				List<com.sirius.product.service.main.product.wsdl.ProductBean> products = ProductSearchDAO.getAllProductsByType(Integer.parseInt(request.getParameter("type")));
//				List<com.sirius.wishlistws.wishlist.wsdl.ProductBean> wishlistProducts = new ArrayList<com.sirius.wishlistws.wishlist.wsdl.ProductBean>();
//				for(com.sirius.product.service.main.product.wsdl.ProductBean product : products)
//				{
//					com.sirius.wishlistws.wishlist.wsdl.ProductBean wishlistProduct = new com.sirius.wishlistws.wishlist.wsdl.ProductBean();
//					wishlistProduct.setId(product.getId());
//					wishlistProduct.setName(product.getName());
//					wishlistProduct.setDetails(product.getDetails());
//					wishlistProduct.setPrice(product.getPrice());
//					wishlistProduct.setType(1);
//					wishlistProducts.add(wishlistProduct);
//				}
//				request.setAttribute("Products", products);
//				
//				int location_id = (Integer) request.getSession().getAttribute("activeUserLocation");
//				List<EmployeeLikeBean> likers = WishlistDAO.getAllEmployeesWhoLikedProducts(wishlistProducts, location_id);
//				
//				for(EmployeeLikeBean liker : likers)
//				{
//					int productId = liker.getProductId();
//					List<String> likesForProduct = (List<String>)request.getAttribute("LikesForProduct:" + productId);
//					if(likesForProduct == null)
//					{
//						likesForProduct = new ArrayList<String>();
//						likesForProduct.add(liker.getName());
//						request.setAttribute("LikesForProduct:" + productId, likesForProduct);
//					}
//					else
//					{
//						likesForProduct.add(liker.getName());
//					}
//				}
				List<ProductBean> products = ProductSearchDAO.getAllProductsByType(Integer.parseInt(request.getParameter("type")));
				request.setAttribute("Products", products);
				//Map<Integer, List<EmployeeBean>> productsWithLikes = new HashMap<Integer, List<EmployeeBean>>();
				int id = 0;
				int location_id = (Integer) request.getSession().getAttribute("activeUserLocation");
				for(ProductBean bean : products)
				{
					id = bean.getId();
					List<EmployeeBean> emps = WishlistDAO.getAllEmployeesWhoLikedProduct(id, location_id);
					List<String> empNames = new ArrayList<String>();
					for(EmployeeBean emp : emps)
					{
						empNames.add(emp.getName());
					}
					request.setAttribute("LikesForProduct:" + id,  empNames);
				}
			}
			return mapping.findForward(SUCCESS);
		}
		else
		{	
			return mapping.findForward(FAILURE);
		}
	}
}
