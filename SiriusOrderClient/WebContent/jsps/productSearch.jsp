<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<title>productSearch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/productSearch.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
	<div>
		<div>
			<html:form action="/Login" styleClass="search">
				<html:select styleClass="categorySelect" property="email">
					<option value="all">All</option>
					<option value="ink_and_toner">Ink & Toner</option>
					<option value="breakroom">Breakroom</option>
					<option value="office_supplies">Office Supplies</option>
				</html:select>
				<input type="text" class="search"></input>
				<input type="button" class="search" value="Search"/>
			</html:form>

		</div>
		<div>
			<img src="pic_mountain.jpg" alt="name of item" style="width:250px;height:200px;"></img>
			<p></p>
			<div>
				<img></img> nums
				$price
			</div>
			<input type="button" value="Add To Cart"/>
		</div>
	</div>

</body>
</html>