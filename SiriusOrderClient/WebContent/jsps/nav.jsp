
<!DOCTYPE HTML>
<!--  
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>nav</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
-->
	<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
	<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/nav.css">
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
	
	<nav id="navIconLinks">
		<ul id="navIconLinksList">
			<li>
				<div class="nav-icon-image-container">
					<img class="nav-icon-image nav-icon-image-shadow" src="/SiriusOrderClient/assets/wishlist_icon.png" alt="Wishlist Icon"/>
				</div>
			</li>
			<li>
				<div class="nav-icon-image-container">
					<img class="nav-icon-image" src="/SiriusOrderClient/assets/cart_icon.png" alt="Cart Icon"/>
				</div>
			</li>
			<li>
				<div class="nav-icon-image-container">
					<img class="nav-icon-image nav-icon-image-shadow" src="/SiriusOrderClient/assets/reports_icon.png" alt="Reports Icon"/>
				</div>
			</li>
			<li>
				<div id="navAdminSubModal">
					<div id="adminNavSubModalTextContainer">
						<div class="admin-nav-sub-modal-text-container">
							Add Visitors
						</div>
						<div class="admin-nav-sub-modal-text-container">
							Add Public Holidays
						</div>
						<div class="admin-nav-sub-modal-text-container">
							Activate Users
						</div>
					</div>
					
					<img id="navAdminSubModalImage" src="/SiriusOrderClient/assets/left_options_modal.png" alt="Admin Sub Menu"/>
				</div>
				<div class="nav-icon-image-container">
					<img class="nav-icon-image nav-icon-image-shadow" src="/SiriusOrderClient/assets/admin_icon.png" alt="Admin Icon"/>
				</div>
			</li>
		</ul>
	</nav>
<!--  
</body>
</html>
-->