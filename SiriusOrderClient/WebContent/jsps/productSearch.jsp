<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head> 
		<title>productSearch</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productSearch.css">
		
		<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productCard.css">
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    	
		<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/SiriusOrderClient/js/productSearch.js"></script>
		
	</head>
	<body>
		<header>
			<%@ include file="header.jsp" %>
		</header> 
		<div id="productSearchAndNavContainer">
			<main id="productSearchContainer">
				<div class="pageTitle">
						<h1>Product Search</h1>
					</div>
				<div class="searchContainer">
					<!-- <form method="post" action="/SiriusOrderClient/ProductSearchServlet" class="search">
						<select class="categorySelect" name="category">
							<option value="0">All</option>
							<option value="3">Ink & Toner</option>
							<option value="1">Breakroom</option>
							<option value="2">Office Supplies</option>
						</select>
						<input type="text" class="search searchBar" name="search"></input>
						<input id="productSearchButton" type="submit" class="search" value="Search"/>
					</form> -->
					<select class="category-select" id="category" name="category">
						<option value="0">All</option>
						<option value="3">Ink & Toner</option>
						<option value="1">Breakroom</option>
						<option value="2">Office Supplies</option>
					</select>
					<input type="text" class="search searchBar" id="search" name="search"></input>
					<div class="search-icon-container">
						<span id="productSearchButton" onclick="searchProducts()"><i class="material-icons search-icon">search</i></span>
					</div>
					<div id="likedLimit">
						You have 5 products in Wishlist already.
					</div>
				</div>
				<div id="productContainer" class="productContainer">
					<c:forEach var="product" items="${Products}">
						<c:set var="currentProduct" value="${product}" scope="request"/>
						<div class="productContainerCard">
							<div class="productCard">
								<div class="imageContainer">
									<a href="/SiriusOrderClient/NavigationServlet?action=productDetails&id=${currentProduct.getId()}">
										<img src="${currentProduct.getImage()}" alt="name of item" class="productImage"></img>
									</a>
								</div>
								<div class="productLabel">
									<p class="nameLabel">${currentProduct.getName()}</p>
								</div>
								<div class="likesAndPrice">
									<span class="glyphicon glyphicon-heart clickable-like" onclick='addToWishlist(${currentProduct.getId()})'></span>
									<c:set var="productId" value="LikesForProduct:${currentProduct.getId()}" scope="request"/>
									<div id="numOfLikes${currentProduct.getId()}" class="num-of-likes" onmouseover="createModal(${currentProduct.getId()})" onmouseout="deleteModal('likesModal${currentProduct.getId()}')">${requestScope[productId].size()}</div>
									<div class="price-tag">$ ${currentProduct.getPrice()}</div>
									<div id="likesModal${currentProduct.getId()}" class="likes-modal">
										<c:forEach var="user" items="${requestScope[productId]}">
											<div>
												${user}
											</div>
										</c:forEach>
									</div>
								</div>
								<c:if test ="${sessionScope.activeUserType == 2}">
									<div class="add-to-cart-btn-container">
										<input class="addToCartBtn" type="button" 
										onclick="addToCart('${currentProduct.getId()}')" 
										value="Add To Cart" />
									</div>
									<span id="addConfirmed${currentProduct.getId()}" class="confirm-popup">
										Product Added!
									</span>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</main>
			<input id="userType" type="hidden" value="${sessionScope.activeUserType}" />
			<aside id="productSearchNavContainer">
				<%@ include file="nav.jsp"%>
			</aside>
		</div>
	</body>
</html>