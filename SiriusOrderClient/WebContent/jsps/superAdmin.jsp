<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>superAdmin</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%-- HEADER --%>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	
	<%-- SUPER ADMIN --%>
	<div class="super-admin-container">
		<%-- BUDGET TABLE --%>
		<div class="super-admin-budget-table">
			<table class="table table-responsive table-striped">
				<thead>
					<tr>
						<td>
							<bean:message key="SUPER_ADMIN_LOCATION_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_NAME_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_EMAIL_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_EMPLOYEE_NUMBERS_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_RECOMMENDED_BUDGET_LABEL" />
						</td>
						<td>
							<bean:message key="SUPER_ADMIN_ALLOTTED_BUDGET_LABEL" />
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${offices}"var="office" >
						<tr>
							<td>
								${office.location}
							</td>
							<td>
								${office.adminName}
							</td>
							<td>
								${office.adminEmail}
							</td>
							<td>
								${office.numberOfEmployees}
							</td>
							<td>
								${office.recommendedBuget}
							</td>
							<td>
								${office.allottedBudget}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<%-- BUTTONS --%>
		<div class="super-admin-button-container">
			<button>
				<bean:message key="SUPER_ADMIN_ASSIGN_ADMIN_LABEL" />
			</button>
		</div>
		<div class="super-admin-button-container">
			<button>
				<bean:message key="SUPER_ADMIN_ADD_LOCATION_LABEL" />
			</button>
		</div>
	</div>
	
</body>
</html>