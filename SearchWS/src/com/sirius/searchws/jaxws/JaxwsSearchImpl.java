package com.sirius.searchws.jaxws;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.sirius.searchws.beans.ActualvBudgetBean;
import com.sirius.searchws.db.SearchDAO;

@WebService(endpointInterface = "com.sirius.searchws.jaxws.Search", portName = "search", targetNamespace = "http://searchws.sirius.com/search/wsdl", serviceName = "JaxwsSearch")
public class JaxwsSearchImpl implements Search{

	@Override
	public List<ActualvBudgetBean> budgetSearch(int location_id, Date fromDate,
			Date toDate, String reportType) {
		SearchDAO dao = new SearchDAO();
		return dao.budgetSearch(location_id, fromDate, toDate, reportType);
	}

}
