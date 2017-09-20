<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>productCard</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productCard.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="productContainer">
		<html:link href="/servlet?id=${currentProduct.getId()}">
			<img src="${currentProduct.getImage()}" alt="name of item" class="productImage"></img>
		</html:link>

		<div class="productLabel">
			<p>${currentProduct.getName()}</p>
		</div>
		<div class="likesAndPrice">
			<span class="glyphicon glyphicon-heart" style="color:#AF4B33"></span>
			<span>$${currentProduct.getPrice()}</span>
		</div>
		<html:form action="/Login" styleClass="addToCartBtn">
			<input type="submit" value="Add To Cart"/>
		</html:form>
	</div>
</body>
</html>