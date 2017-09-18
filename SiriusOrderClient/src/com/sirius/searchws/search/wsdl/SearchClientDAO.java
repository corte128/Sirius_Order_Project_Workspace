package com.sirius.searchws.search.wsdl;

import java.util.Date;
import java.util.List;

public class SearchClientDAO {
	public static List<ActualvBudgetBean> search(int locationId, 
			Date fromDate, 
			Date toDate,
			String reportType)
	{
		SearchProxy client = new SearchProxy();
		return client.search(locationId, fromDate, toDate, reportType);
		
	}
}
