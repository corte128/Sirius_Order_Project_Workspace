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

<body onresize="resizeDisplay()")>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div style="display:flex">
		<div class="wishlist-header">
			<%-- TITLE --%>
			<div class="wishlist-header-title">
				<h1>
					<bean:message key='WISHLIST_TITLE'/>
				</h1>
			</div>

			<%-- CONTENT --%>
			<div class="wishlist-container">
				<input type="hidden" id="profileImage"
					value="${sessionScope.activeUserPicture}" /> <input type="hidden"
					id="profileName" value="${sessionScope.activeUserName}" /> <input
					type="hidden" id="wishlistWelcome"
					value="<bean:message key='WISHLIST_WELCOME_TEXT'/>" /> <input
					type="hidden" id="wishlistWishlistLabel"
					value="<bean:message key='WISHLIST_WISHLIST_LABEL'/>" /> <input
					type="hidden" id="wishlistYouHave"
					value="<bean:message key='WISHLIST_YOU_HAVE_TEXT'/>" /> <input
					type="hidden" id="wishlistInWishlist"
					value="<bean:message key='WISHLIST_IN_WISHLIST_TEXT'/>" /> <input
					type="hidden" id="wishlistLocationLabel"
					value="<bean:message key='WISHLIST_LOCATION_LABEL'/>" /> <input
					type="hidden" id="wishlistLocation"
					value="${requestScope.location}" />

				<div class="wishlist-profile-container" id="profileCard">
					<div class="wishlist-profile-image-welcome">
						<div class="wishlist-profile-image">
							<c:choose>
								<c:when test="${sessionScope.activeUserPicture}">
									<img class="employee-profile-photo" alt="profile image"
										src="data:image/jpeg;base64,${sessionScope.activeUserPicture}" />
								</c:when>
								<c:otherwise>
									<img class="employee-profile-photo" alt="profile image"
										src="/SiriusOrderClient/assets/default.png" />
								</c:otherwise>
							</c:choose>
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
						<span id="productAmount"> <%-- <c:out value="${ProductAmount}"/> --%>
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
				<div style="display: flex; margin: auto;">
					<div class="glyphicon glyphicon-chevron-left wishlist-arrow-left"
						onclick="Left()"
						id="arrow-left"></div>

					<%-- PRODUCT CARDS --%>
					<div class="wishlist-product-card-container" id="productContainer">

					</div>

					<div class="glyphicon glyphicon-chevron-right wishlist-arrow-right"
						onclick="scrollRight()"
						id="arrow-right"></div>
				</div>
				<input id="userType" type="hidden"
					value="${sessionScope.activeUserType}" />

			</div>
		</div>


		<%-- ASIDE --%>
		<div class="wishlist-navbar-container">
			<%@ include file="nav.jsp"%>
		</div>
	</div>

</body>
</html>