package com.sirius.order.client.action;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.product.service.main.product.wsdl.*;

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
				List<ProductBean> products = ProductSearchDAO.getAllProductsByType(Integer.parseInt(request.getParameter("type")));
				request.setAttribute("Products", products);
			}
			return mapping.findForward(SUCCESS);
		}
		else
		{	
			return mapping.findForward(FAILURE);
		}
	}
}
