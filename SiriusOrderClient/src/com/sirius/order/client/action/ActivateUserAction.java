package com.sirius.order.client.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.adminws.officeadmin.wsdl.EmployeeBean;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminClientDAO;
import com.sirius.employeews.employee.wsdl.EmployeeClientDAO;
import com.sirius.order.client.form.ActivateUserForm;

public class ActivateUserAction extends org.apache.struts.action.Action{
	
	private final static String SUCCESS = "success";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setContentType("text/html");
        ActivateUserForm activateForm = (ActivateUserForm) form;
        int id = activateForm.getId();
    	String pressed = activateForm.getPressed();
        HttpSession session = request.getSession();
        try{
			EmployeeClientDAO dao = new EmployeeClientDAO();
			if (pressed.equals("approved")) {
				dao.updateEmployee(id, "accepted", (Integer) session.getAttribute("activeUserID")); 
			} 
			else if (pressed.equals("rejected")){
				dao.updateEmployee(id, "declined", (Integer) session.getAttribute("activeUserID")); 
			}
			OfficeAdminClientDAO client = new OfficeAdminClientDAO();
			List<EmployeeBean> employees = client.getUnapprovedEmployees((Integer) session.getAttribute("activeUserLocation"));
			request.setAttribute("employees", employees);
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
        return mapping.findForward(SUCCESS);
    }
}
