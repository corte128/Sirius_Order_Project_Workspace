<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<title>wishlist</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%-- HEADER --%>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div class="wishlist-container">
	
		<%-- PROFILE CARD --%>
		<div class="wishlist-profile-container">
			<div>
				<div class="wishlist-profile-image">
					<img class="employee_profile_photo" alt="profile image" src="data:image/jpeg;base64,${sessionScope.viewPicture}"/>
				</div>
				<div class="wishlist-profile-welcome">
					<bean:message key="WISHLIST_WELCOME_TEXT" />
				</div>
			</div>
			<div class="wishlist-profile-title">
				<bean:message key="WISHLIST_WISHLIST_LABEL" />
			</div>
			<div class="wishlist-profile-text">
				<bean:message key="WISHLIST_YOU_HAVE_TEXT" />
				<bean:message key="WISHLIST_IN_WISHLIST_TEXT" />
			</div>
			<div class="wishlist-profile-title">
				<bean:message key="WISHLIST_LOCATION_LABEL" />
			</div>
			<div class="wishlist-profile-text"></div>
		</div>
		
		<%-- PRODUCT CARDS --%>
		<div class="wishlist-product-card-container">
			
		</div>
		
		<%-- ASIDE --%>
		<aside>
			<%@ include file="nav.jsp"%>
		</aside>
		
	</div>
</body>
</html>