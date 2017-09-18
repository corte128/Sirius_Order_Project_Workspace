package com.sirius.searchws.jaxws;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.sirius.searchws.beans.ActualvBudgetBean;

@WebService(name = "Search", targetNamespace = "http://searchws.sirius.com/search/wsdl")
public interface Search {
	
	@WebMethod(action = "budgetSearch")
	@WebResult(name = "budgetSearchReturn", targetNamespace = "http://searchws.sirius.com/search/wsdl")
	@RequestWrapper(localName = "budgetSearch", targetNamespace = "http://searchws.sirius.com/search/wsdl", className = "com.sirius.searchws.jaxws.BudgetSearch")
	@ResponseWrapper(localName = "budgetSearchResponse", targetNamespace = "http://searchws.sirius.com/search/wsdl", className = "com.sirius.searchws.jaxws.BudgetSearchResponse")
	public List<ActualvBudgetBean> budgetSearch(
			@WebParam(name = "location", targetNamespace = "http://searchws.sirius.com/search/wsdl") int location_id,
			@WebParam(name = "from", targetNamespace = "http://searchws.sirius.com/search/wsdl") Date fromDate,
			@WebParam(name = "to", targetNamespace = "http://searchws.sirius.com/search/wsdl") Date toDate,
			@WebParam(name = "report", targetNamespace = "http://searchws.sirius.com/search/wsdl") String reportType);
	
}
