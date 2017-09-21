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
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/registration.css">
<title><fmt:message key="REGISTRATION_TITLE" /></title> 
</head>
<body>
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="registration-content">
		<div id="registration-form">
			<div id="registration-labels">
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_NAME_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_EMAIL_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_LOCATION_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_PASSWORD_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_CONFIRM_PASSWORD_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_PROFILE_PIC_LABEL" /></label>
			</div>
			<div id="registration-inputs-container">
				<div id="registration-inputs">
					<div id="registration-title"><fmt:message key="REGISTRATION_TITLE" /></div>
					<html:form action="/Registration" enctype="multipart/form-data">
						<input class="registration-field" required type="text" name="name" placeholder=<fmt:message key="REGISTRATION_NAME_LABEL" /> />
						<br />
						<input class="registration-field" required type="email" name="email" placeholder=<fmt:message key="REGISTRATION_EMAIL_LABEL" /> />
						<br />
						<select name="location">
							<c:forEach var="loc" items="${sessionScope.locations}">
								<option value="${loc.getId()}">${loc.getCity()}, ${loc.getState()}</option>
							</c:forEach>
						</select>
						<br />
						<input class="registration-field" required type="password" name="password" placeholder=<fmt:message key="REGISTRATION_PASSWORD_LABEL" /> />
						<br />
						<input class="registration-field" required type="password" name="confirm_password" placeholder=<fmt:message key="REGISTRATION_CONFIRM_PASSWORD_LABEL" /> />
						<br />
						<html:file name="RegistrationForm" property="photo" />
						<html:submit styleId="registration-button" value="Register" />
						<div id="errors">
							<html:errors  />
						</div>
					</html:form>
				</div>
			</div>
		</div>
		<aside>
			<%@ include file="nav.jsp" %>
		</aside>
	</div>
</body>
</html>