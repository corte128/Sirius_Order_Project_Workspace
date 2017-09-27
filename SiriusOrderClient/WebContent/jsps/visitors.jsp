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
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/jquery-ui.structure.min.css"/>
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/jquery-ui.theme.min.css"/>
	<title><fmt:message key="VISITORS_TITLE" /></title>
	<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/SiriusOrderClient/js/jquery-ui-1.12.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/visitors.css">
</head>
<body>    
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<div id="visitors-content">
		<div id="visitors-title"><h3><fmt:message key="VISITORS_FORM_TITLE" /></h3></div>
		<div id="visitors-form">
			<div id="visitors-labels">
				<br />
				<label class="visitor-form-label"><fmt:message key="VISITORS_FROM_DATE_LABEL" /></label>
				<br />
				<c:if test="${errorArray.contains('VISITORS_FUTURE_DATE_ERROR')}">
					<div id="future-date" class="mobile-error"></div>
				</c:if>
				<label class="visitor-form-label top-padding"><fmt:message key="VISITORS_TO_DATE_LABEL" /></label>
				<br />
				<c:if test="${errorArray.contains('VISITORS_DATE_ORDER_ERROR')}">
					<div id="date-order" class="mobile-error"></div>
				</c:if>
				<label class="visitor-form-label top-padding"><fmt:message key="VISITORS_COUNT_LABEL" /></label>
				<br />
				<c:if test="${errorArray.contains('VISITORS_COUNT_ERROR')}">
					<div id="count-error" class="mobile-error"></div>
				</c:if>
				<label class="visitor-form-label top-padding"><fmt:message key="VISITORS_COMMENT_LABEL" /></label>
			</div>
			<div id="visitors-input-fields">
				<html:form action="/Visitors">
					<input class="visitors-form-input" required type="date" name="from_date" />
					<c:if test="${errorArray.contains('VISITORS_FUTURE_DATE_ERROR')}">
						<span class="error">
							<fmt:message key="VISITORS_FUTURE_DATE_ERROR" />
						</span>
					</c:if>
					<br />
					<input class="visitors-form-input top-padding" required type="date" name="to_date" />
					<c:if test="${errorArray.contains('VISITORS_DATE_ORDER_ERROR')}">
						<span class="error">
							<fmt:message key="VISITORS_DATE_ORDER_ERROR" />
						</span>
					</c:if>
					<br />
					<html:text styleId="count-input" styleClass="visitors-form-input top-padding" property="count"></html:text>
					<c:if test="${errorArray.contains('VISITORS_COUNT_ERROR')}">
						<span class="error">
							<fmt:message key="VISITORS_COUNT_ERROR" />
						</span>
					</c:if>
					<br />
					<html:textarea styleClass="visitors-form-input top-padding" property="comment"></html:textarea>
					<br />
					<html:submit styleId="visitor-add-button" value=Add><fmt:message key="VISITORS_ADD_BUTTON" /></html:submit>
				</html:form>
			</div>
		</div>
		<aside>
			<%@ include file="nav.jsp" %>
		</aside>
	</div>
</body>
</html>