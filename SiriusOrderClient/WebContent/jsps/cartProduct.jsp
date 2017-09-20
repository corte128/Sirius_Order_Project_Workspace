<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<title>cartProduct</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div>
		<img src="${product.getImage()}"></img>
		<span>${product.getName()}</span>
		<span>${product.getPrice()}</span>
		<input type="text" value="${product.getQuantity()}"/>
		<a href="#" class="glyphicon glyphicon-trash"></a>
	</div>
</body>
</html>