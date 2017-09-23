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
		<script type="text/javascript" src="/SiriusOrderClient/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/SiriusOrderClient/js/productSearch.js"></script>
	 
	</head>
	<body onload="selectedOption(${param.type})">
		<header>
			<%@ include file="header.jsp" %>
		</header> 
		<div id="productSearchAndNavContainer">
			<main id="productSearchContainer">
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
					<select class="categorySelect" id="category" name="category">
						<option value="0">All</option>
						<option value="3">Ink & Toner</option>
						<option value="1">Breakroom</option>
						<option value="2">Office Supplies</option>
					</select>
					<input type="text" class="search searchBar" id="search" name="search"></input>
					<input id="productSearchButton" type="button" onclick="searchProducts()" class="search" value="Search"/>
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
									<span id="numOfLikes${currentProduct.getId()}">${requestScope[productId].size()}</span>
									<span>$${currentProduct.getPrice()}</span>

								</div>
								<input type="button" onclick="addToCart(${currentProduct.getId()})" value="Add To Cart"/>
							</div>
						</div>
					</c:forEach>
				</div>
			</main>
			<aside id="productSearchNavContainer">
				<%@ include file="nav.jsp"%>
			</aside>
		</div>
	</body>
</html>