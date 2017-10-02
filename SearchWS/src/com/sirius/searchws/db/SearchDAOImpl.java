package com.sirius.searchws.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sirius.searchws.beans.ActualvBudgetBean;
import com.sirius.searchws.beans.BudgetObject;

public class SearchDAOImpl {

	private Connection conn = null;
	private static final ResourceBundle queries = ResourceBundle.getBundle("com.sirius.searchws.properties.queries");
	public SearchDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	public List<ActualvBudgetBean> budgetSearch(int location_id, Date fromDate, Date toDate, String reportType){
//		String sql="INSERT INTO likes_tbl (employee_id_fk, product_id_fk) VALUES (?,?)";
		String sql = queries.getString("SEARCH_WITHIN_TIMEFRAME");
		//String sql = "SELECT location_id_fk, budget_date, budget_allotted, budget_recommended FROM budget_tbl WHERE budget_date BETWEEN(? AND ?) AND location_id_fk=?";
//		WHERE created_at BETWEEN('2011-12-01', date_add('2011-12-01', INTERVAL 7 DAY));
		List<BudgetObject> budgetObjects = new ArrayList<BudgetObject>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date((fromDate).getTime()));
			statement.setDate(2, new java.sql.Date((toDate).getTime()));
			statement.setInt(3, location_id);
		    ResultSet rs = statement.executeQuery();
		    BudgetObject object = null;
		    int i = 0;
		    while(rs.next()){
		    	object = new BudgetObject();
		    	object.setId(i++);
		    	object.setActual(rs.getBigDecimal("allotted"));
		    	object.setBudget(rs.getBigDecimal("recommended"));
		    	object.setDate(rs.getDate("budget_date"));
		    	budgetObjects.add(object);
		    }
			DBConnection.closeStatement(statement);
			DBConnection.closeResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return budgetSeparationByTime(budgetObjects, fromDate, reportType);
	}
	
	private List<ActualvBudgetBean> budgetSeparationByTime(List<BudgetObject> budgetObjects, Date fromDate, String reportType){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
    	Date startOfWeek = fromDate;
    	cal.setTime(fromDate);
    	String startMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    	
    	//c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    	if(reportType.equalsIgnoreCase("weekly")){
	    	cal.add(Calendar.DATE, 6);
    	}
    	if(reportType.equalsIgnoreCase("monthly")){
//	    	cal.add(Calendar.MONTH, 1);
	    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	}
    	
    	int length = budgetObjects.size();
    	List<BudgetObject> objectsByTimeIncrements = new ArrayList<BudgetObject>();
    	List<ActualvBudgetBean> beans = new ArrayList<ActualvBudgetBean>();
    	int incrementer = 0;
    	while(incrementer < length){
    		BudgetObject obj = budgetObjects.get(incrementer);
    		if(obj.getDate().compareTo(cal.getTime()) <= 0){
    			//add to objects list then in the else add all values together
    			if(reportType.equalsIgnoreCase("weekly")){
    				objectsByTimeIncrements = new ArrayList<BudgetObject>();
    			}
    			objectsByTimeIncrements.add(obj);
    			incrementer++;
    		}
    		System.out.println(obj.getDate() + " || " + cal.getTime());
    		if(incrementer == length || obj.getDate().compareTo(cal.getTime()) > 0){
    			BigDecimal actual = new BigDecimal(0);
    			BigDecimal budget = new BigDecimal(0);
    			ActualvBudgetBean abBean = new ActualvBudgetBean();
    			for(int i = 0; i < objectsByTimeIncrements.size(); i++){
    				actual = actual.add(objectsByTimeIncrements.get(i).getActual());
    				budget = budget.add(objectsByTimeIncrements.get(i).getBudget());
    			}
    			abBean.setActual(actual);
    			abBean.setBudget(budget);
    			if(reportType.equalsIgnoreCase("weekly")){
	    			abBean.setTime( dateFormat.format(startOfWeek) + " - " + dateFormat.format(cal.getTime()));
	    			cal.add(Calendar.DATE, 1);
	    			startOfWeek = cal.getTime();
	    			cal.add(Calendar.DATE, 6);
    			}
    			else{
    				abBean.setTime(startMonth);
    				cal.add(Calendar.MONTH, 1);
			    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    				startMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    			}
    			beans.add(abBean);
    		}
    	}
		return beans;
	}
	
	public void closeConnection(){
		DBConnection.closeConnection(conn);
	}
	
}
