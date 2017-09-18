package com.sirius.order.client.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class VisitorsForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5279121743428014439L;

	private String from_date;
	private String to_date;
	private int count;
	private String comment;
	
	public VisitorsForm(){
		super();
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	    ActionErrors errors = new ActionErrors();
	    if (count < 1){
	    	errors.add("count", new ActionMessage("registration.name.invalid"));
	    }
	    return errors;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
