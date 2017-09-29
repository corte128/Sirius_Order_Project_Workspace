package com.sirius.service.generate.database;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.websphere.scheduler.UserCalendar;
import com.ibm.websphere.scheduler.UserCalendarHome;
import com.ibm.websphere.scheduler.UserCalendarPeriodInvalid;
import com.ibm.websphere.scheduler.UserCalendarSpecifierInvalid;
import com.sirius.adminws.officeadmin.wsdl.Holiday;
import com.sirius.adminws.officeadmin.wsdl.OfficeAdminProxy;
import com.sirius.locationws.location.wsdl.LocationBean;
import com.sirius.locationws.location.wsdl.LocationProxy;
import com.sirius.order.service.attendance.jaxws.jaxws.attendance.wsdl.AttendanceProxy;
import com.sirius.order.service.attendance.jaxws.jaxws.attendance.wsdl.AttendanceRecordBean;
import com.sirius.service.cart.cart.wsdl.BudgetBean;
import com.sirius.service.cart.cart.wsdl.CartProxy;
import com.sirius.service.cart.cart.wsdl.OrderBean;
import com.sirius.service.generate.beans.KnapsackBean;

//import com.sirius.service.beans.OrderBean;

public class GenerateDAO {
	private static final Logger logger = Logger.getLogger(GenerateDAO.class
			.getName());

	public static BigDecimal generateBudget(int locationId) {
		BigDecimal recommendedBudget = null;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		int numberOfEmployees = 0;
		int numberOfHolidays = 0;
		String beginningOfWeek = "";
		String endOfWeek = "";
		Connection conn = null;

		// service access variables
		AttendanceProxy attendance = new AttendanceProxy();
		OfficeAdminProxy officeAdmin = new OfficeAdminProxy();

		try {
			Calendar cal = getStartDate();
			// getting the budget week
			beginningOfWeek = dt.format(cal.getTime());
			cal.add(Calendar.DATE, +6);
			endOfWeek = dt.format(cal.getTime());

			// pushes back to the previous week for attendance
			cal.add(Calendar.DATE, -13);
			String beginDate = dt.format(cal.getTime());
			cal.add(Calendar.DATE, +5);
			String endDate = dt.format(cal.getTime());

			// gets the all the attendance from the previous week
			List<AttendanceRecordBean> attendanceResults = attendance
					.getAttendanceRecords("%", "%", String.valueOf(locationId),
							beginDate, endDate);
			numberOfEmployees = attendanceResults.size();

			// gets the holidays and checks if the holidays are with in the date
			// range
			List<Holiday> holidays = officeAdmin.getAllHolidays(locationId);
			for (Holiday day : holidays) {
				if (day.getDate().compareTo(endOfWeek) > 0) {

				} else if (day.getDate().compareTo(beginningOfWeek) < 0) {

				} else {
					numberOfHolidays++;
				}
			}
			conn = DBConnection.getConnection();

			// gets the visitors for the week
			numberOfEmployees += GenerateDAOImplementation.getNumberOfVisitors(
					conn, locationId, beginningOfWeek.toString(),
					endOfWeek.toString());

			// budget algorithm
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
		boolean flag = false;
		Connection conn = null;
		CartProxy cp = new CartProxy();
		LocationProxy lpObj = new LocationProxy();
		List<LocationBean> locations = lpObj.getLocations();
		// clear out the carts
		

		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			//GenerateDAOImplementation.clearCart(conn);
			
			for (LocationBean location : locations) {
				BigDecimal budget = generateBudget(location.getId());
				int roundedBudget = budget.toBigInteger().intValue();
				BudgetBean budgetObj = new BudgetBean();
				OrderBean orderObj = new OrderBean();
				List<List<Integer>> items = null;
				
				// get all products that were liked
				items = GenerateDAOImplementation.getProductsLikesPrices(conn,
						location.getId());

				List<Integer> products = items.get(0);
				Map<Integer, Integer> productsNeeded = new HashMap<Integer, Integer>();
				
				int likes[] = new int[products.size()];
				int prices[] = new int[products.size()];

				// converting to array lists
				for (int index = 0; index < products.size(); index++) {
					likes[index] = items.get(1).get(index);
					prices[index] = items.get(2).get(index);
				}

				if(prices.length != 0){
					// optimize
					productsNeeded = optimizationOfProductIds(roundedBudget,
							prices, likes, products.size());
	
					// gets the budget object and date
					GregorianCalendar gCal = new GregorianCalendar();
					gCal.setTime(new Date());
	
					XMLGregorianCalendar calendar = null;
					try {
						calendar = DatatypeFactory.newInstance()
								.newXMLGregorianCalendar(gCal);
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}
					budgetObj.setBudgetAllotted(budget);
					budgetObj.setBudgetRecommended(budget);
					budgetObj.setBudgetDate(calendar);
					budgetObj.setLocationId(location.getId());
	
					// adds the cart to the database
					for (Integer key : productsNeeded.keySet()) {
						int quantity = productsNeeded.get(key);
						BigDecimal totalPrice = new BigDecimal(items.get(2)
								.get(key) * quantity);
	
						orderObj.setProductId(products.get(key));
						orderObj.setQuantity(quantity);
						orderObj.setOrderName("cart");
						orderObj.setTotalPrice(totalPrice);
	
						cp.addProductToCart(orderObj, budgetObj, 0);
					}
				}

			}
			// make re-occurring using scheduling
			schedule();
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,
						"SQL Exception Found: Couldn't roll back", e1);
			} finally {
				logger.log(Level.SEVERE,
						"SQL Exception Found: Incorrect properties", e);
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.log(Level.SEVERE,
						"SQL Exception Found: Couldn't roll back", e1);
			} finally {
				logger.log(Level.SEVERE, "Exception Found ", e);
			}
		} finally {
			if (conn != null) {
				try {
					DBConnection.closeConnection(conn);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "SQL Exception ", e);
				}
			}
		}

		return flag;
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
	private static Map<Integer, Integer> optimizationOfProductIds(int budget,
			int price[], int likes[], int productAmount) {

		int xCor = 0, yCor = 0;
		int total = 0;
		int lowCost = 0;
		boolean completed = false;

		Map<Integer, Integer> products = new HashMap<Integer, Integer>();
		List<ArrayList<KnapsackBean>> grid = null;
		Set<Integer> optimalProductIds = null;

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
				if (products.containsKey(productId))
					products.put(productId, products.get(productId) + 1);
				else
					products.put(productId, 1);

				total += price[productId];
				if (lowCost == 0 || lowCost > price[productId])
					lowCost = price[productId];
			}

			// determine if there is money left in the budget
			if ((total + lowCost) > budget)
				completed = true;
			else
				budget = budget - total;
		}
		return products;
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
	private static List<ArrayList<KnapsackBean>> ZeroToOneKnapsack(int W,
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
																				// //
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
	private static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	private static void schedule() throws NamingException, RemoteException,
			CreateException, UserCalendarSpecifierInvalid,
			UserCalendarPeriodInvalid {
		// Create an initial context
		InitialContext ctx = new InitialContext();

		// Lookup and narrow the default UserCalendar home.
		UserCalendarHome defaultCalHome = (UserCalendarHome) PortableRemoteObject
				.narrow(ctx.lookup(UserCalendarHome.DEFAULT_CALENDAR_JNDI_NAME),
						UserCalendarHome.class);

		// Create the default UserCalendar instance.
		UserCalendar defaultCal = defaultCalHome.create();

		// Calculate a date using CRON based on the current
		// date and time. Return the next date that is
		// Sunday at 11am
		// 0 0 11 ? * SUN
		Date newDate = defaultCal.applyDelta(new Date(), "CRON",
				"0 0 11 ? * SUN");
	}

}
