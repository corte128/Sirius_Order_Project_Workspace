package com.sirius.order.client.form;

import org.apache.struts.action.ActionForm;

public class ActivateUserForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7150759366496571024L;
	int id;
	
	public ActivateUserForm(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
