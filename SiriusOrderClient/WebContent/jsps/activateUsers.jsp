<!DOCTYPE HTML><%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="com.sirius.order.client.properties.common" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="/SiriusOrderClient/css/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/jquery-ui.structure.min.css" />
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/jquery-ui.theme.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title><fmt:message key="ACTIVATE_TITLE" />
</title>

<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="/SiriusOrderClient/js/jquery-ui-1.12.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
<script src="/SiriusOrderClient/js/activateUser.js"></script>
<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/activateUsers.css">

<c:if test="${activeUserID == null}">
	<jsp:forward page="/jsps/welcome.jsp" />
</c:if>

<c:if test="${activeUserType < 2}">
	<jsp:forward page="/jsps/welcome.jsp" />
</c:if>

</head>

<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>

	<div id="users-content">
		<div id="users-form">
			<div class="pageTitle">
				<h1>Activate Users</h1>
			</div>
			<div class="information-container">
				<div id="rejectSuccess" style="display: none">
					<bean:message key="ACTIVATE_USER_REJECT" />
				</div>

				<div id="confirmSuccess" style="display: none">
					<bean:message key="ACTIVATE_USER_CONFIRM" />
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th><fmt:message key="ACTIVATE_COLUMN_NAME" />
						</th>
						<th><fmt:message key="ACTIVATE_COLUMN_EMAIL" />
						</th>
						<th><fmt:message key="ACTIVATE_COLUMN_ACTION" />
						</th>
					</tr>
				</thead>
				<tbody>
					<html:form action="/ActivateUser" styleId="approvalForm">
						<input id="id" type="hidden" name="id" value="" />
						<input id="pressed" type="hidden" name="pressed" value="" />
						<c:forEach var="user" items="${employees}">
							<tr class="users-form-row">
								<td>${user.getName()}</td>
								<td>${user.getEmail()}</td>
								<td>
									<button value="${user.getId()}" type="button"
										id="approve-button" onclick="approve(this)"
										class="fa fa-check" aria-hidden="true" />
									<button value="${user.getId()}" type="button"
										id="reject-button" onclick="reject(this)" class="fa fa-times"
										aria-hidden="true" /></td>
							</tr>
						</c:forEach>
					</html:form>
				</tbody>
			</table>
		</div>
		<aside>
			<%@ include file="nav.jsp"%>
		</aside>
	</div>
</body>
</html>