package com.sirius.searchws.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			statement.setDate(1, (java.sql.Date) fromDate);
			statement.setDate(2, (java.sql.Date) toDate);
			statement.setInt(3, location_id);
			statement.executeUpdate();
		    ResultSet rs = statement.executeQuery(sql);
		    BudgetObject object = null;
		    
		    while(rs.next()){
		    	object = new BudgetObject();
		    	object.setId(rs.getInt("budget_id_pk"));
		    	object.setActual(rs.getBigDecimal("budget_alloted"));
		    	object.setBudget(rs.getBigDecimal("budget_recommended"));
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
		
	    Calendar cal = Calendar.getInstance();
    	Date startOfWeek = fromDate;
    	String startMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    	cal.setTime(fromDate);
    	
    	//c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    	if(reportType.equals("weekly")){
	    	cal.add(Calendar.DATE, 6);
    	}
    	if(reportType.equals("monthly")){
//	    	cal.add(Calendar.MONTH, 1);
	    	cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	}
    	
    	int length = budgetObjects.size();
    	List<BudgetObject> objectsByTimeIncrements = new ArrayList<BudgetObject>();
    	List<ActualvBudgetBean> beans = new ArrayList<ActualvBudgetBean>();
    	
    	for(int incrementer = 0; incrementer < length; incrementer++){
    		BudgetObject obj = budgetObjects.get(incrementer);
    		if(obj.getDate().compareTo(cal.getTime()) <= 0){
    			//add to objects list then in the else add all values together
    			objectsByTimeIncrements.add(obj);
    		}
    		else{
    			BigDecimal actual = new BigDecimal(0);
    			BigDecimal budget = new BigDecimal(0);
    			ActualvBudgetBean abBean = new ActualvBudgetBean();
    			for(int i = 0; i < objectsByTimeIncrements.size(); i++){
    				actual.add(objectsByTimeIncrements.get(i).getActual());
    				budget.add(objectsByTimeIncrements.get(i).getBudget());
    			}
    			abBean.setActual(actual);
    			abBean.setBudget(budget);
    			if(reportType.equals("weekly")){
	    			abBean.setTime(startOfWeek + " - " + cal.getTime());
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
