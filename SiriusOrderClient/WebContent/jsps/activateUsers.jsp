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
<title><fmt:message key="REGISTRATION_TITLE" /></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="users-content">
		<div id="users-form">
			<html:form action="/ActivateUser">
				<table class="table">
					<thead>
				      <tr>
				        <th>Name</th>
				        <th>Email</th>
				        <th>Action</th>
				      </tr>
				    </thead>
				    <tbody>
				    	<c:forEach var="user" items="${employees}">
							<tr>
								<td>${user.getName()}</td>
								<td class="user-email">${user.getEmail()}</td>
								<input type="hidden" name="id" value=${user.getId()} />
								<td>
									<button type="submit" name="approve" value="Approve" class="fa fa-check" aria-hidden="true"/></button>
									<button type="submit" name="reject" value="Reject" class="fa fa-times" aria-hidden="true"/></button>
								</td>
							</tr>
						</c:forEach>
				    </tbody>
				</table>
			</html:form>
		</div>
		<aside>
			<%@ include file="nav.jsp" %>
		</aside>
	</div>
</body>
</html>