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
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/registration.css">
<meta name="viewport" content="width=device-width" />
<script src="/SiriusOrderClient/js/registration.js"></script>
<title><fmt:message key="REGISTRATION_TITLE" /></title> 
</head>
<body>
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="registration-content">
		<div id="registration-title"><fmt:message key="REGISTRATION_TITLE" /></div>
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
					<html:form action="/Registration" enctype="multipart/form-data">
						<input class="registration-field" required type="text" name="name" />
						<c:if test="${errorArray.contains('REGISTRATION_NAME_INVALID')}">
							<label class="error">
								<fmt:message key="REGISTRATION_NAME_INVALID" />
							</label>
						</c:if>
						<br />
						<input id="registration-email" onblur="checkEmail()" class="registration-field" required type="email" name="email" />
						<label id="email-taken-error" class="error"><fmt:message key="REGISTRATION_EMAIL_TAKEN" /></label>
						<c:if test="${errorArray.contains('REGISTRATION_EMAIL_INVALID')}">
							<label class="error">
								<fmt:message key="REGISTRATION_EMAIL_INVALID" />
							</label>
						</c:if>
						<br />
						<select id="location-field" class="registration-field" name="location">
							<c:forEach var="loc" items="${sessionScope.locations}">
								<option value="${loc.getId()}">${loc.getCity()}, ${loc.getState()}</option>
							</c:forEach>
						</select>
						<br />
						<input class="registration-field" required type="password" name="password" />
						<br />
						<input class="registration-field" required type="password" name="confirm_password" />
						<c:if test="${errorArray.contains('REGISTRATION_PASSWORDS_INVALID')}"> 
							<label class="error">
								<fmt:message key="REGISTRATION_PASSWORDS_INVALID" />
							</label>
						</c:if>
						<br />
						<html:file styleId="photo-field" name="RegistrationForm" property="photo" />
						<br />
						<html:submit styleId="registration-button" value="Register" />
					</html:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>