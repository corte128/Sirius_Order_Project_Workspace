package com.sirius.order.client.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class RegistrationForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -689527712341567617L;
	private String name;
	private String email;
	private int location;
	private String password;
	private String confirm_password;
	private FormFile photo;
	
	public RegistrationForm(){
		super();
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	    ActionErrors errors = new ActionErrors();
	    ArrayList<String> errorArray = new ArrayList<String>();
	    if (!name.matches("[a-zA-Z\\s]+") || name.length() == 0){
	    	errors.add("name_error", new ActionMessage("REGISTRATION_NAME_INVALID"));
	    	errorArray.add("REGISTRATION_NAME_INVALID");
	    }
	    if (!email.matches("^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?(siriuscom)\\.com$")){
	    	errors.add("email", new ActionMessage("REGISTRATION_EMAIL_INVALID"));
	    	errorArray.add("REGISTRATION_EMAIL_INVALID");
	    }
	    if (!password.equals(confirm_password) || confirm_password.length() == 0){
	    	errors.add("password", new ActionMessage("REGISTRATION_PASSWORDS_INVALID"));
	    	errorArray.add("REGISTRATION_PASSWORDS_INVALID");
	    }
	    confirm_password = "";
	    request.setAttribute("errorArray", errorArray);
	    return errors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public FormFile getPhoto() {
		return photo;
	}

	public void setPhoto(FormFile photo) {
		this.photo = photo;
	}
}
