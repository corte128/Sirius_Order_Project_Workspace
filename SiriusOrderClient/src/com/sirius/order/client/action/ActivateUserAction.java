package com.sirius.order.client.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.employeews.employee.wsdl.EmployeeClientDAO;
import com.sirius.order.client.form.ActivateUserForm;

public class ActivateUserAction extends org.apache.struts.action.Action{
	
	private final static String SUCCESS = "success";
	private boolean approve;
	private boolean reject;
	
	public void setApprove(boolean approve){
		this.approve = approve;
	}
	
	public void setReject(boolean reject){
		this.reject = reject;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setContentType("text/html");
        ActivateUserForm activateForm = (ActivateUserForm) form;
        HttpSession session = request.getSession();
        try{
        	int id = activateForm.getId();
			EmployeeClientDAO dao = new EmployeeClientDAO();
			if (approve) {
				dao.updateEmployee(id, "accepted", (Integer) session.getAttribute("activeUserID")); 
			} 
			else if (reject){
				dao.updateEmployee(id, "declined", (Integer) session.getAttribute("activeUserID")); 
			}
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
        return mapping.findForward(SUCCESS);
    }
}
