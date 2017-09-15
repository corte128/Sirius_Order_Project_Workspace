package com.sirius.order.client.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.employeews.employee.wsdl.EmployeeBean;
import com.sirius.employeews.employee.wsdl.EmployeeClientDAO;
import com.sirius.loginws.login.wsdl.LoginClientDAO;
import com.sirius.order.client.form.LoginForm;

public class LoginAction extends org.apache.struts.action.Action {
	
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setContentType("text/html");
        LoginForm loginForm = (LoginForm) form;
        HttpSession session = null;
        int employeeID = 0;
        EmployeeBean emp = null;
        try{
        	String email = loginForm.getEmail();
			String password = loginForm.getPassword();
			LoginClientDAO dao = new LoginClientDAO();
			employeeID = dao.getEmployeeByCredentials(email, password);
			if (employeeID != 0) {
				session = request.getSession();
				EmployeeClientDAO edao = new EmployeeClientDAO();
				emp = edao.getEmployeeByEmail(email);
				session.setAttribute("activeUserName", emp.getName());
				session.setAttribute("activeEmployeeId", employeeID);
				

				//System.out.println("---------------SUCCESS------------");
				return mapping.findForward(SUCCESS);
			} else {
				return mapping.findForward(FAILURE);
			}
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
}
