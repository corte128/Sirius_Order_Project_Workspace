
<!DOCTYPE HTML>
<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

	<meta name="viewport" content="width=device-width" />
	
	<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
	<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%><!--  
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/font.css">-->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/SiriusOrderClient/css/nav.css">
	
	<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/SiriusOrderClient/js/nav.js"></script>
	
	<nav id="navIconLinks">
		<div id="navIconSidebarContainer">
			<div class="responsive-nav-sidebar responsive-nav-animate-right" 
				style="display:none" 
				id="navIconLinksSidebar"
			>
			<!--
				<div id="responsiveNavCloseContainer">
					<div id="responsiveNavClose"
						class="glyphicon glyphicon-remove" 
						onclick="closeSidebar()"
					></div>
				</div>
			-->
				<ul id="navSidebarIconLinksList">
					<li>
						<a href="/SiriusOrderClient/jsps/wishlist.jsp">
							<div class="nav-sidebar-icon-image-container">
								<img class="nav-sidebar-icon-image nav-icon-image-shadow" 
									src="/SiriusOrderClient/assets/wishlist_icon.png" 
									alt="Wishlist Icon"
								/>
							</div>
						</a>
					</li>
					
					<c:if test="${sessionScope.activeUserType} == 2">
						<li>	
							<a href="/SiriusOrderClient/jsps/cart.jsp">
								<div class="nav-sidebar-icon-image-container">
									<img class="nav-sidebar-icon-image" 
										src="/SiriusOrderClient/assets/cart_icon.png" 
										alt="Cart Icon"
									/>
								</div>
							</a>
						</li>
					</c:if>
					
					<c:if test="${sessionScope.activeUserType} == 2">	
						<li>	
							<div id="reportsNavIconContainer" 
								class="nav-sidebar-icon-image-container" 
								data-toggle="collapse" 
								data-target="#reportsNavSubItemsList"
							>
								<img class="nav-sidebar-icon-image nav-icon-image-shadow" 
									src="/SiriusOrderClient/assets/reports_icon.png" 
									alt="Reports Icon"
								/>
							</div>
							<div id="reportsNavSubItemsList" class="panel-collapse collapse">
								<ul class="nav-sidebar-sub-list">
							      	<div class="register-nav-sidebar-sub-text-container">
										<a class="nav-sidebar-sub-text-link" href="/SiriusOrderClient/NavigationServlet?action=attendance">
											Attendance Report
										</a>
									</div>
									<div class="register-nav-sidebar-sub-text-container">
										<a class="nav-sidebar-sub-text-link" href="/SiriusOrderClient/NavigationServlet?action=budget">
											Budget Vs Actual
										</a>
									</div>
								</ul>
							</div>
						</li>
					</c:if>
					
					<li>
						<div id="adminNavIconContainer" 
							class="nav-sidebar-icon-image-container" 
							data-toggle="collapse" 
							data-target="#adminNavSubItemsList"
						>
							<img class="nav-sidebar-icon-image nav-icon-image-shadow"
								src="/SiriusOrderClient/assets/admin_icon.png" 
								alt="Admin Sub Menu" 
							/>
						</div>
						<div id="adminNavSubItemsList" class="panel-collapse collapse">
							<ul class="nav-sidebar-sub-list">
								<div class="admin-nav-sidebar-sub-text-container">
									<a class="nav-sidebar-sub-text-link" href="/SiriusOrderClient/jsps/visitors.jsp">
										Add Visitors
									</a>
								</div>
								<div class="admin-nav-sidebar-sub-text-container">
									<a class="nav-sidebar-sub-text-link" href="/SiriusOrderClient/jsps/holidays.jsp">
										Add Public Holidays
									</a>
								</div>
								<div class="admin-nav-sidebar-sub-text-container">
									<a class="nav-sidebar-sub-text-link" href="/SiriusOrderClient/NavigationServlet?action=activateUsers">
										Activate Users
									</a>
								</div>
							</ul>
						</div>
					</li>
				</ul>
			</div>
	      	
	      	<div id="responsiveNavHamburgerButton" 
	      		class="glyphicon glyphicon-menu-hamburger" 
	      		onclick="openSidebar()"
	      	></div>
	      	
		</div>
    	
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
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/NavigationServlet?action=attendance">
								Attendance Report
							</a>
						</div>
						<div class="reports-nav-sub-modal-text-container">
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/NavigationServlet?action=budget">
								Budget Vs Actual
							</a>
						</div>
					</div>
					
					<img id="navReportsSubModalImage" 
						src="/SiriusOrderClient/assets/left_options_modal.png" 
						alt="Reports Sub Menu" 
					/>
				</div>
				
				<div id="reportsNavIconContainer" class="nav-icon-image-container" onmouseover="showReportsModal()">
					<img class="nav-icon-image nav-icon-image-shadow" 
						src="/SiriusOrderClient/assets/reports_icon.png" 
						alt="Reports Icon"
					/>
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
							<a class="nav-sub-modal-text-link" href="/SiriusOrderClient/NavigationServlet?action=activateUsers">
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