package com.sirius.order.client.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.order.client.form.ProductSearchForm;
import com.sirius.product.service.main.product.wsdl.ProductBean;
import com.sirius.product.service.main.product.wsdl.ProductSearchDAO;

public class ProductSearchAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	response.setContentType("text/html");
        ProductSearchForm productSearchForm = (ProductSearchForm) form;
        try{
			ProductSearchDAO dao = new ProductSearchDAO();
			String search = productSearchForm.getSearch();
			String category = productSearchForm.getCategory();
			List<ProductBean> objects = dao.getAllProductsByNameAndType(search, category);
			request.setAttribute("Products", objects);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("jsps/productSearch.jsp");
    		dispatcher.forward(request, response);
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		return null;
    }
    
}
