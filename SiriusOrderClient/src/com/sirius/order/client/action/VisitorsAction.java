package com.sirius.order.client.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sirius.adminws.officeadmin.wsdl.OfficeAdminClientDAO;
import com.sirius.order.client.form.VisitorsForm;

public class VisitorsAction extends org.apache.struts.action.Action{
	
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setContentType("text/html");
    	HttpSession session = request.getSession();
        VisitorsForm visitorsForm = (VisitorsForm) form;
        try{
        	String toDate = visitorsForm.getTo_date();
        	String fromDate = visitorsForm.getFrom_date();
        	String comment = visitorsForm.getComment();
        	int count = visitorsForm.getCount();
        	int userID = (Integer) session.getAttribute("activeUserID");
        	int locationID = (Integer) session.getAttribute("activeUserLocation");
        	boolean status = false;
			OfficeAdminClientDAO dao = new OfficeAdminClientDAO();
			status = dao.addVisitors(fromDate, toDate, count, comment, userID, locationID);
			if (status) {
				count = 0;
				comment = "";
				request.setAttribute("visitor_success", "true");
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
