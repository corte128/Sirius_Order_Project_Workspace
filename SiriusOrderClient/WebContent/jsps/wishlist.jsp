<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<title>wishlist</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/productCard.css">
	<link rel="stylesheet" type="text/css"
	href="/SiriusOrderClient/css/wishlist.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/SiriusOrderClient/js/wishlist.js"></script>
</head>  
<body>
	<%-- HEADER --%>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div class="wishlist-container">

		<%-- PROFILE CARD --%>
		<div class="wishlist-profile-container">
			<div class="wishlist-profile-image-welcome">
				<div class="wishlist-profile-image">
					<img class="employee-profile-photo" alt="profile image"
						src="data:image/jpeg;base64,${sessionScope.activeUserPicture}" />
				</div>
				<div class="wishlist-profile-welcome">
					<bean:message key="WISHLIST_WELCOME_TEXT" />
					<c:out value="${sessionScope.activeUserName}" />
				</div>
			</div>
			<div class="wishlist-profile-title wishlist-profile-title-wishlist">
				<bean:message key="WISHLIST_WISHLIST_LABEL" />
			</div>
			<div class="wishlist-profile-text wishlist-profile-text-wishlist">
				<bean:message key="WISHLIST_YOU_HAVE_TEXT" />
				<span id="productAmount">
					<%--<c:out value="${ProductAmount}"/> --%>
				</span>
				<bean:message key="WISHLIST_IN_WISHLIST_TEXT" />
			</div>
			<div class="wishlist-profile-title wishlist-profile-title-location">
				<bean:message key="WISHLIST_LOCATION_LABEL" />
			</div>
			<div class="wishlist-profile-text wishlist-profile-text-location">
				<c:out value="${requestScope.location}" />
			</div>
		</div>

		<%-- PRODUCT CARDS --%>
		<div class="wishlist-product-card-container" id="productContainer">
			
		</div>
		<input id="userType" type="hidden" value="${sessionScope.activeUserType}" />
		<%-- ASIDE --%>
		<aside class="wishlist-navbar-container">
			<%@ include file="nav.jsp"%>
		</aside>

	</div>
</body>
</html>