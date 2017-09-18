package com.sirius.searchws.search.wsdl;

import java.util.Date;
import java.util.List;

public class SearchClientDAO {
	public static List<ActualvBudgetBean> budgetSearch(int locationId, 
			Date fromDate, 
			Date toDate,
			String reportType)
	{
		SearchProxy client = new SearchProxy();
		return client.budgetSearch(locationId, fromDate, toDate, reportType);
		
	}
}
