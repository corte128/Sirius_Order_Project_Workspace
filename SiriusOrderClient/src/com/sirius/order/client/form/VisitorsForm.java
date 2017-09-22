package com.sirius.order.client.form;

import java.util.ArrayList;
import java.util.Calendar;

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
	    ArrayList<String> errorArray = new ArrayList<String>();
	    String[] from = from_date.split("-");
	    String[] to = to_date.split("-");
	    Calendar current_Date = Calendar.getInstance();
	    Calendar to_Date = Calendar.getInstance();
	    to_Date.set(Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]));
	    Calendar from_Date = Calendar.getInstance();
	    from_Date.set(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]));
	    if(from_Date.after(to_Date)){
	    	//error start date after end date
	    	errorArray.add("VISITORS_DATE_ORDER_ERROR");
	    }
	    if(from_Date.getTimeInMillis() - current_Date.getTimeInMillis() < 604800000){
	    	//date must be at least 7 days in the future
	    	errorArray.add("VISITORS_FUTURE_DATE_ERROR");
	    }
	    if (count < 1){
	    	//must have a number greater than 0
	    	errorArray.add("VISITORS_COUNT_ERROR");
	    }
	    request.setAttribute("errorArray", errorArray);
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
