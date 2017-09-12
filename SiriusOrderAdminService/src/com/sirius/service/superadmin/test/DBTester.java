package com.sirius.service.superadmin.test;

import com.sirius.service.superadmin.SuperAdminImplementation;

public class DBTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SuperAdminImplementation supObj = new SuperAdminImplementation();
		
		//testing the set budget
//		BigDecimal budget = BigDecimal.valueOf(1000);
//		supObj.setBudgetByLocation(budget, 1);
		
		//testing the get budget
//		supObj.getBudgetByLocation(1);
		
		//testing the adding location
		supObj.addLocation("Omaha", "NE");
		
		//testing the assigning admin
//		supObj.assignAdmin(1, 1);

	}

}
