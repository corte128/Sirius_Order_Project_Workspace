package com.sirius.order.client.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.sirius.loginws.login.wsdl.LoginClientDAO;

public class LoginForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3099626862091984579L;
	private String email;
	private String password;
	
	public LoginForm(){
		super();
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	    ActionErrors errors = new ActionErrors();
	    int employeeID = 0;
	    LoginClientDAO dao = new LoginClientDAO();
		employeeID = dao.getEmployeeByCredentials(email, password);
	    // CREDENTIALS ERROR
	    if (employeeID == 0) {
	        errors.add("password", new ActionMessage("LOGIN_CREDENTIALS"));
	    }
	    return errors;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
