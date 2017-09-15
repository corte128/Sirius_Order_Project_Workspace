package com.sirius.order.client.form;

import org.apache.struts.action.ActionForm;

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
