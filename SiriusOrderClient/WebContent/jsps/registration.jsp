<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head> 
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/button.css" />
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
				<c:if test="${errorArray.contains('REGISTRATION_NAME_INVALID')}">
					<div class="error-div"></div>
				</c:if>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_EMAIL_LABEL" /></label>
				<div id="email-error-spacing"></div>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_LOCATION_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_PASSWORD_LABEL" /></label>
				<br />
				<label class="registration-label"><fmt:message key="REGISTRATION_CONFIRM_PASSWORD_LABEL" /></label>
				<c:if test="${errorArray.contains('REGISTRATION_PASSWORDS_INVALID')}">
					<div id="password-error"></div>
				</c:if>
				<br/>
				<label id="profile-label" class="registration-label"><fmt:message key="REGISTRATION_PROFILE_PIC_LABEL" /></label>
			</div>
			<div id="registration-inputs-container">
				<div id="registration-inputs">
					<html:form action="/Registration" enctype="multipart/form-data">
						<html:text name="RegistrationForm" property="name" styleClass="registration-field"/>
						<%--<input class="registration-field" required type="text" name="name" /> --%>
						<c:if test="${errorArray.contains('REGISTRATION_NAME_INVALID')}">
							<span class="error">
								<fmt:message key="REGISTRATION_NAME_INVALID" />
							</span>
						</c:if>
						<br />
						<html:text name="RegistrationForm" property="email" onblur="checkEmail()" styleId="registration-email" styleClass="registration-field"/>
						<%--<input id="registration-email" onblur="checkEmail()" class="registration-field" required type="email" name="email" /> --%>
						<span id="email-taken-error" class="error"><fmt:message key="REGISTRATION_EMAIL_TAKEN" /></span>
						<span class="error" id="invalid-email"><fmt:message key="REGISTRATION_EMAIL_INVALID" /></span>
						<br />
						<select id="location-field" class="registration-field" name="location">
							<c:forEach var="loc" items="${sessionScope.locations}">
								<option class="location-object" value="${loc.getId()}">${loc.getCity()}, ${loc.getState()}</option>
							</c:forEach>
						</select>
						<br />
						<html:password name="RegistrationForm" property="password" styleClass="registration-field" onchange="checkEmail()" />
						<%--<input class="registration-field" required type="password" name="password" /> --%>
						<br />
						<html:password name="RegistrationForm" property="confirm_password" styleClass="registration-field" />
						<%--<input class="registration-field" required type="password" name="confirm_password" /> --%>
						<c:if test="${errorArray.contains('REGISTRATION_PASSWORDS_INVALID')}"> 
							<span class="error">
								<fmt:message key="REGISTRATION_PASSWORDS_INVALID" />
							</span>
						</c:if>
						<br />
						<label id="browse-button" for="photo-field">Browse</label>
						<span id="chosen-file"></span>
						<input type="file" id="photo-field" onchange="changeFileName()" name="photo" style="display: none"/>
						<br />
						<c:if test="${errorArray.contains('REGISTRATION_NAME_INVALID')}">
							<div></div>
						</c:if>
						<c:if test="${errorArray.contains('REGISTRATION_PASSWORDS_INVALID')}">
							<div></div>
						</c:if>
						<html:submit styleId="registration-button" styleClass="project-button" value="Register" />
					</html:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>