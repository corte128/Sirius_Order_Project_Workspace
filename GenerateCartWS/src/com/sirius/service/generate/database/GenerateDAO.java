package com.sirius.service.generate.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.adminws.officeadmin.wsdl.Holiday;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminProxy;
import com.sirius.order.service.attendance.jaxws.jaxws.attendance.wsdl.AttendanceProxy;
import com.sirius.order.service.attendance.jaxws.jaxws.attendance.wsdl.AttendanceRecordBean;
import com.sirius.service.generate.beans.KnapsackBean;

//import com.sirius.service.beans.OrderBean;

public class GenerateDAO {
	private static final Logger logger = Logger.getLogger(GenerateDAO.class
			.getName());

	public static BigDecimal generateBudget(int locationId) {
		BigDecimal recommendedBudget = null;
		int numberOfEmployees = 0;
		int numberOfHolidays = 0;
		Date beginningOfWeek = new Date();
		Date endOfWeek = new Date();
		Connection conn = null;

		// service access variables
		AttendanceProxy attendance = new AttendanceProxy();
		OfficeAdminProxy officeAdmin = new OfficeAdminProxy();

		try {
			Calendar cal = getStartDate();
			// getting the budget week
			beginningOfWeek = cal.getTime();
			cal.add(Calendar.DATE, +6);
			endOfWeek = cal.getTime();

			// pushes back to the previous week for attendance
			cal.add(Calendar.DATE, -13);
			String beginDate = cal.getTime().toString();
			cal.add(Calendar.DATE, +5);
			String endDate = cal.getTime().toString();

			// gets the all the attendance from the previous week
			List<AttendanceRecordBean> attendanceResults = attendance
					.getAttendanceRecords("%", "%", "%", beginDate, endDate);
			numberOfEmployees = attendanceResults.size();

			// gets the holidays and checks if the holidays are with in the date
			// range
			List<Holiday> holidays = officeAdmin.getAllHolidays(locationId);
			for (Holiday day : holidays) {
				if (day.getDate().compareTo(endOfWeek.toString()) > 0) {

				} else if (day.getDate().compareTo(beginningOfWeek.toString()) < 0) {

				} else {
					numberOfHolidays++;
				}
			}
			conn = DBConnection.getConnection();
			// gets the visitors for the week
			numberOfEmployees += GenerateDAOImplementation.getNumberOfVisitors(
					conn, locationId, beginningOfWeek.toString(),
					endOfWeek.toString());

			recommendedBudget = new BigDecimal(numberOfEmployees * 2
					* (5 - numberOfHolidays) + 100);

		} catch (NamingException e) {
			logger.log(Level.SEVERE,
					"Naming Exception Found: Incorrect naming", e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception Found: Incorrect properties", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception Found ", e);
		}

		return recommendedBudget;
	}

	/**
	 * 
	 * @return boolean
	 */
	public static boolean generateCart() {
		boolean temp = false;

		return temp;
	}

	/**
	 * gets the start date that will determine when the budget is generated
	 * 
	 * @return Calendar
	 */
	private static Calendar getStartDate() {
		Calendar cal = Calendar.getInstance();

		// gets the Monday date of the week since that is the start
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			cal.add(Calendar.DATE, -1);
		}

		return cal;
	}

	/**
	 * Takes in the budget, prices, likes, and how many products there are gets
	 * the optimized amount and returns the positions of the ids for the product
	 * ids
	 * 
	 * @param budget
	 * @param prices
	 * @param likes
	 * @param productAmount
	 * @return List<Integer>
	 */
	private static List<Integer> optimizationOfProductIds(int budget,
			int price[], int likes[], int productAmount) {

		int xCor = 0, yCor = 0;
		int total = 0;
		int lowCost = 0;
		boolean completed = false;

		List<ArrayList<KnapsackBean>> grid = null;
		Set<Integer> optimalProductIds = null;
		List<Integer> productIdsToCart = new ArrayList<Integer>();

		while (!completed) {
			grid = new ArrayList<ArrayList<KnapsackBean>>();
			optimalProductIds = new HashSet<Integer>();

			grid = ZeroToOneKnapsack(budget, price, likes, productAmount);

			KnapsackBean optimalBean = grid.get(productAmount).get(budget);

			// gets the ids based on the last element added to the grid
			// the last element added will always have the best value
			while (xCor != -1 && yCor != -1 && optimalBean.getWeight() != 0
					&& optimalBean.getProductId() != -1) {
				optimalProductIds.add(optimalBean.getProductId());

				xCor = optimalBean.getxCor();
				yCor = optimalBean.getyCor();

				optimalBean = grid.get(xCor).get(yCor);
			}

			// generates the total cost of the products
			// and adds the product ids to return
			for (Integer productId : optimalProductIds) {
				productIdsToCart.add(productId);
				total += price[productId];
				if (lowCost == 0 || lowCost > price[productId])
					lowCost = price[productId];
			}

			//determine if there is money left in the budget
			if ((total + lowCost) > budget) {
				completed = true;
			} else {
				budget = budget - total;
			}
		}
		return productIdsToCart;
	}

	/**
	 * The optimization algorithm for the knapsack 0/1
	 * 
	 * @param W
	 * @param wt
	 * @param val
	 * @param n
	 * @return List<ArrayList<KnapsackBean>>
	 */
	static private List<ArrayList<KnapsackBean>> ZeroToOneKnapsack(int W,
			int wt[], int val[], int n) {
		int i = 0, w = 0;
		int K[][] = new int[n + 1][W + 1];
		List<ArrayList<KnapsackBean>> grid = new ArrayList<ArrayList<KnapsackBean>>();

		for (i = 0; i <= n; i++) {
			ArrayList<KnapsackBean> tempList = new ArrayList<KnapsackBean>();
			for (w = 0; w <= W; w++) {
				KnapsackBean kBean = new KnapsackBean();
				// The case for non-existant values
				if (i == 0 || w == 0) {
					K[i][w] = 0;
					kBean.setProductId(-1);
					kBean.setWeight(0);
					kBean.setxCor(-1);
					kBean.setyCor(-1);
				}
				// Dynamic programming progressing through the best weight per
				// value
				else if (wt[i - 1] <= w) {
					int v1 = val[i - 1] + K[i - 1][w - wt[i - 1]];
					int v2 = K[i - 1][w];
					K[i][w] = max(v1, v2);
					kBean.setProductId(i - 1);
					kBean.setWeight(K[i][w]);
					kBean.setxCor(i - 1);

					if (K[i][w] == v1) {
						kBean.setProductId(i - 1);
						kBean.setyCor(w - wt[i - 1]);
					} else {
						kBean.setProductId(grid.get(i - 1).get(w)
								.getProductId());
						kBean.setyCor(w);
					}
				}
				// sets to previous value
				else {
					K[i][w] = K[i - 1][w];

					kBean.setProductId(grid.get(i - 1).get(w).getProductId());// change
																				// to
																				// the
																				// correct
																				// product
																				// id
					kBean.setWeight(K[i - 1][w]);
					kBean.setxCor(i - 1);
					kBean.setyCor(w);
				}
				tempList.add(kBean);
			}
			grid.add(tempList);
		}

		return grid;
	}

	/**
	 * gets the maximum between a and b
	 * 
	 * @param a
	 * @param b
	 * @return int
	 */
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// public static List<OrderBean> getTotalLikesForProducts(){
	// return null;
	// }
}
