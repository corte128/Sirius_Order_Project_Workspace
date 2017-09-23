<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productCard.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
			<span class="glyphicon glyphicon-heart clickable-like"></span>
			<span>$${currentProduct.getPrice()}</span>
		</div>
		<input type="button" onclick="addToCart(${currentProduct.getId()})" value="Add To Cart"/>
	</div>