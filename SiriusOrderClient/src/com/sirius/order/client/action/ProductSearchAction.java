package com.sirius.order.client.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.employeews.employee.wsdl.EmployeeClientDAO;
import com.sirius.loginws.login.wsdl.LoginClientDAO;
import com.sirius.order.client.form.LoginForm;
import com.sirius.order.client.form.ProductSearchForm;
import com.sirius.searchws.search.wsdl.ProductSearchDAO;

public class ProductSearchAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setContentType("text/html");
        ProductSearchForm productSearchForm = (ProductSearchForm) form;
        try{
			ProductSearchDAO dao = new ProductSearchDAO();
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		return null;
    }
    
}
