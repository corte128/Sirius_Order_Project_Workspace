package com.sirius.searchws.search.wsdl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class SearchClientDAO {
	public static List<ActualvBudgetBean> budgetSearch(int locationId, 
			Date fromDate, 
			Date toDate,
			String reportType)
	{
		GregorianCalendar f = new GregorianCalendar();
		GregorianCalendar t = new GregorianCalendar();
		
		f.setTime(fromDate);
		t.setTime(toDate);
		
		XMLGregorianCalendar gFromDate = null;
		XMLGregorianCalendar gToDate = null;
		try
		{
			gFromDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(f);
			gToDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(t);
		} 
		catch (DatatypeConfigurationException e) 
		{
			e.printStackTrace();
		}
		
		SearchProxy client = new SearchProxy();
		return client.budgetSearch(locationId, gFromDate, gToDate, reportType);
		
	}
}
