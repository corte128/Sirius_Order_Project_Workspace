package com.sirius.search.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sirius.search.beans.BudgetObject;

public class EmployeeAttendanceSearchServiceDAOImpl {

	private Connection conn = null;
	
	public EmployeeAttendanceSearchServiceDAOImpl(Connection conn){
		conn = this.conn;
	}
	
	public void search(int location_id, Date fromDate, Date toDate, String reportType){
//		String sql="INSERT INTO likes_tbl (employee_id_fk, product_id_fk) VALUES (?,?)";
		String sql = "SELECT budget_date, budget_allotted, budget_recommended FROM budget_tbl WHERE budget_date BETWEEN(" + fromDate + ", " + toDate + ")";
//		WHERE created_at BETWEEN('2011-12-01', date_add('2011-12-01', INTERVAL 7 DAY));
		List<BudgetObject> budgetObjects = new ArrayList<BudgetObject>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, location_id);
			statement.setDate(2, (java.sql.Date) fromDate);
			statement.setDate(3, (java.sql.Date) toDate);
			statement.executeUpdate();
		    ResultSet rs = statement.executeQuery(sql);
		    Calendar cal = Calendar.getInstance();
		    BudgetObject object = null;
		    
		    
		    while(rs.next()){
		    	object = new BudgetObject();
		    	object.setId(rs.getInt("budget_id_pk"));
		    	object.setActual(rs.getBigDecimal("budget_alloted"));
		    	object.setBudget(rs.getBigDecimal("budget_recommended"));
		    	object.setDate(rs.getDate("budget_date"));
		    	budgetObjects.add(object);
		    }
			//do logic to separate by month or by week
		    if(reportType.equals("weekly")){
		    	cal.setTime(fromDate);
		    	cal.add(Calendar.DATE, 6);
		    	int incrementer = 0;
		    	int length = budgetObjects.size();
		    	List<BudgetObject> weeklyObjects = new ArrayList<BudgetObject>();
		    	while(incrementer < length){
		    		BudgetObject obj = budgetObjects.get(incrementer);
		    		if(obj.getDate().compareTo(cal.getTime()) <= 0){
		    			//add to objects list then in the else add all values together
		    			weeklyObjects.add(obj);
		    		}
		    		else{
		    			for(int i = 0; i < weeklyObjects.size(); i++){
		    				
		    			}
		    		}
		    		incrementer++;
		    	}
		    	
		    }
		    else if(reportType.equals("monthly")){
		    	
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void temp(){
		
	}
	
}
