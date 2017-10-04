package com.sirius.order.client.action;

import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.sirius.adminws.officeadmin.wsdl.EmployeeBean;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminClientDAO;
import com.sirius.employeews.employee.wsdl.EmployeeClientDAO;
import com.sirius.mailws.mail.wsdl.MailProxyDAO;
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
			FormFile photo = registrationForm.getPhoto();	
			
			EmployeeClientDAO dao = new EmployeeClientDAO();
			boolean status = false;
			InputStream is = photo.getInputStream();
	        byte[] imageData = IOUtils.toByteArray(is);
	        imageData = Base64.encodeBase64(imageData);
			status = dao.addEmployee(name, password, 1, email, imageData, location);
			if (status) {
				OfficeAdminClientDAO officeAdminClient = new OfficeAdminClientDAO();
				EmployeeBean officeAdmin = officeAdminClient.getOfficeAdmin(location);
				MailProxyDAO mailDao = new MailProxyDAO();
				String subject = "New User Registered";
				String message = name + " has registered with your office location. Please verify their status.";

				if(officeAdmin.getEmail() != null)
				{
					mailDao.sendMessage(subject, message, officeAdmin.getEmail());
				}
				registrationForm.setName("");
	        	registrationForm.setEmail("");
				registrationForm.setPassword("");
				registrationForm.setPhoto(null);
				return mapping.findForward(SUCCESS);
			} else {
				registrationForm.setName("");
	        	registrationForm.setEmail("");
				registrationForm.setPassword("");
				registrationForm.setPhoto(null);
				return mapping.findForward(FAILURE);
			}
        } catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
}
