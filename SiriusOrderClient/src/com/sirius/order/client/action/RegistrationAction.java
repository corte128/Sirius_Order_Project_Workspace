package com.sirius.order.client.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.sirius.order.client.form.RegistrationForm;

public class RegistrationAction extends org.apache.struts.action.Action{
	
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setContentType("text/html");
        RegistrationForm registrationForm = (RegistrationForm) form;
        try{
        	String name = registrationForm.getName();
        	String email = registrationForm.getEmail();
        	int location = registrationForm.getLocation();
			String password = registrationForm.getPassword();
			String confirm_password = registrationForm.getConfirm_password();
			FormFile photo = registrationForm.getPhoto();	
			
//			LoginClientDAO dao = new LoginClientDAO();
//			employeeID = dao.getEmployeeByCredentials(email, password);
//			if (employeeID != 0) {
//				session = request.getSession();
//				EmployeeClientDAO edao = new EmployeeClientDAO();
//				emp = edao.getEmployeeByEmail(email);
//
//				return mapping.findForward(SUCCESS);
//			} else {
//				return mapping.findForward(FAILURE);
//			}
			return mapping.findForward(FAILURE);
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
}
