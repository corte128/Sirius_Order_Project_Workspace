<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head> 
<fmt:setBundle basename="com.sirius.order.client.properties.common"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/activateUsers.css">
<link rel="stylesheet" href="/SiriusOrderClient/css/font-awesome-4.7.0/css/font-awesome.min.css">
<title><fmt:message key="ACTIVATE_TITLE" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
<script src="/SiriusOrderClient/js/activateUser.js"></script>
<c:if test="${activeUserID==null}">
	<jsp:forward page = "/jsps/welcome.jsp" />
</c:if> 
</head>
<body>
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="users-content">
		<div id="users-form">
			<table class="table">
				<thead>
			      <tr>
			        <th><fmt:message key="ACTIVATE_COLUMN_NAME" /></th>
			        <th><fmt:message key="ACTIVATE_COLUMN_EMAIL" /></th>
			        <th><fmt:message key="ACTIVATE_COLUMN_ACTION" /></th>
			      </tr>
			    </thead>
			    <tbody>
				    <html:form action="/ActivateUser">
				    	<input id="id" type="hidden" name="id" value="" />
						<input id="pressed" type="hidden" name="pressed" value="" />
						<c:forEach var="user" items="${employees}">
							<tr>
								<td>${user.getName()}</td>
								<td>${user.getEmail()}</td>
								<td>
									<button value="${user.getId()}" type="submit" id="approve-button" onclick="approve(this)" class="fa fa-check" aria-hidden="true"/>
									<button value="${user.getId()}" type="submit" id="reject-button" onclick="reject(this)" class="fa fa-times" aria-hidden="true"/>
								</td>
							</tr>
						</c:forEach>
					</html:form>
			    </tbody>
			</table>
		</div>
		<aside>
			<%@ include file="nav.jsp" %>
		</aside>
	</div>
</body>
</html>