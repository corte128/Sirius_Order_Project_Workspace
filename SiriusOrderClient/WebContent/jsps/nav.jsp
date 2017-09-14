
<!DOCTYPE HTML>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

	<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
	<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/nav.css">
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">
	<script src="/SiriusOrderClient/js/nav.js"></script>
	
	<nav id="navIconLinks">
		<ul id="navIconLinksList">
			<li>
				<a href="/SiriusOrderClient/jsps/wishlist.jsp">
					<div class="nav-icon-image-container">
						<img class="nav-icon-image nav-icon-image-shadow" src="/SiriusOrderClient/assets/wishlist_icon.png" alt="Wishlist Icon"/>
					</div>
				</a>
			</li>
			<li>
				<a href="/SiriusOrderClient/jsps/cart.jsp">
					<div class="nav-icon-image-container">
						<img class="nav-icon-image" src="/SiriusOrderClient/assets/cart_icon.png" alt="Cart Icon"/>
					</div>
				</a>
			</li>
			<li onmouseout="closeReportsModal()">
				<div id="navReportsSubModal" onmouseover="showReportsModal()" onmouseout="closeReportsModal()">
					<div id="reportsNavSubModalTextContainer">
						<div class="reports-nav-sub-modal-text-container">
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/jsps/visitors.jsp">
								Add Visitors
							</a>
						</div>
						<div class="reports-nav-sub-modal-text-container">
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/jsps/holidays.jsp">
								Add Public Holidays
							</a>
						</div>
					</div>
					
					<img id="navReportsSubModalImage" 
						src="/SiriusOrderClient/assets/left_options_modal.png" 
						alt="Reports Sub Menu" 
					/>
				</div>
				
				<div id="reportsNavIconContainer" class="nav-icon-image-container" onmouseover="showReportsModal()">
					<img class="nav-icon-image nav-icon-image-shadow" src="/SiriusOrderClient/assets/reports_icon.png" alt="Reports Icon"/>
				</div>
			</li>
			<li onmouseout="closeAdminModal()">
				<div id="navAdminSubModal" onmouseover="showAdminModal()" onmouseout="closeAdminModal()">
					<div id="adminNavSubModalTextContainer">
						<div class="admin-nav-sub-modal-text-container">
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/jsps/visitors.jsp">
								Add Visitors
							</a>
						</div>
						<div class="admin-nav-sub-modal-text-container">
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/jsps/holidays.jsp">
								Add Public Holidays
							</a>
						</div>
						<div class="admin-nav-sub-modal-text-container">
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/jsps/activateUsers.jsp">
								Activate Users
							</a>
						</div>
					</div>
					
					<img id="navAdminSubModalImage" 
						src="/SiriusOrderClient/assets/left_options_modal.png" 
						alt="Admin Sub Menu" 
					/>
				</div>
				<div id="adminNavIconContainer" class="nav-icon-image-container" onmouseover="showAdminModal()">
					<img class="nav-icon-image nav-icon-image-shadow" src="/SiriusOrderClient/assets/admin_icon.png" alt="Admin Icon"/>
				</div>
			</li>
		</ul>
	</nav>