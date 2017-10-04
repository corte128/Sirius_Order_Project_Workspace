<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="com.sirius.order.client.properties.common" />

<html>
<head>
<title>reviewCart</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/SiriusOrderClient/css/button.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/SiriusOrderClient/css/reviewCart.css">
<link rel="stylesheet" href="/SiriusOrderClient/css/superAdmin.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<div id="cartAndHeaderAndNavContainer">
		<%-- HEADER --%>
		<header>
			<%@ include file="../header.jsp"%>
		</header>
		<div id="cartAndNavContainer">
			<main>
			<div id="cartMainContentLabelContainer">
				<h1>
					<fmt:message key="CART_CART_LABEL" />
				</h1>
			</div>
			<div id="breakroomAboveBudgetContainer">
				<fmt:message key="REVIEW_CART_BREAKROOM_LABEL" />
				<span id="breakroomAboveBudgetAmountContainer"></span>
				<fmt:message key="CART_ABOVE_BUDGET_LABEL" />
			</div>
			<div id="cartOrderWillBePlacedContainer">
				<fmt:message key="CART_ORDER_WILL_BE_PLACED_LABEL" />
				<fmt:formatDate value="${thisFriday.time}" pattern="dd-MMM" />
			</div>
			<div id="cartHeaderContainer">
				<div id="cartHeaderProductImageContainerColumn"
					class="cart-product-image-container"></div>
				<div class="cart-product-name-container"></div>
				<div
					class="cart-product-price-container cart-header-product-price-container">
					<fmt:message key="CART_HEADER_PRICE_LABEL" />
				</div>
				<div
					class="cart-product-quantity-container cart-header-product-quantity-container">
					<fmt:message key="CART_HEADER_QUANTITY_LABEL" />
				</div>
				<div
					class="cart-product-action-container cart-header-product-action-container">
					<fmt:message key="CART_HEADER_ACTIONS_LABEL" />
				</div>
			</div>
			<div id="cartContentRows">
				<%-- BREAKROOM --%>
				<div
					class="cart-section-title-container cart-product-color-alternate-container">
					<div class="cart-product-image-container"></div>
					<div class="cart-section-title-text-container">
						<fmt:message key="REVIEW_CART_BREAKROOM_LABEL" />
						<br id="breakroomTitleLineBreak" /> (
						<fmt:message key="CART_BUDGET_LABEL" />
						: <span id="breakroomBudgetAmountContainer">${currentBudget.getBudgetAllotted()}</span>)
					</div>
					<input id="breakroomBudgetAmountValue" type="hidden"
						value="${currentBudget.getBudgetAllotted()}" /> <span
						class="include-saved-order-container" data-toggle="modal"
						data-target="#includeSavedModal"> <fmt:message
							key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" /> </span>
					<%--<span
						class="include-saved-order-container"
						onclick="showIncludeOrderModal()"> <fmt:message
							key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" /> </span> --%>
				</div>
				<c:set var="breakroomIndex" value="0" scope="page" />

				<c:forEach var="product" items="${breakroomProducts}">
					<div id="cartOrder${breakroomOrders.get(breakroomIndex).getId()}"
						class="cart-product-container cart-product-color-alternate-container">
						<div class="cart-product-image-container">
							<img src="${product.getImage()}" />
						</div>
						<div class="cart-product-name-container">
							${product.getName()}</div>
						<div
							class="cart-product-price-container 
								breakroom-cart-product-price-container"
							value="${product.getPrice()}">${product.getPrice()}</div>
						<div class="cart-product-quantity-container">
							<input
								id="cartProductQuantityInput${breakroomOrders.get(breakroomIndex).getId()}"
								class="breakroom-cart-product-quantity-input"
								onchange="calcBreakroomTotalsAndUpdate(${product.getId()}, ${breakroomOrders.get(breakroomIndex).getId()})"
								type="text"
								value="${breakroomOrders.get(breakroomIndex).getQuantity()}" />
						</div>
						<div class="cart-product-action-container">
							<div class="glyphicon glyphicon-trash"
								onclick="removeFromCart(${breakroomOrders.get(breakroomIndex).getId()})"></div>
						</div>
					</div>
					<c:set var="breakroomIndex" value="${breakroomIndex + 1}"
						scope="page" />
				</c:forEach>

				<div id="breakroomSummaryContainer"
					class="cart-product-color-alternate-container cart-summary-container">
					<div class="cart-product-image-container"></div>
					<div id="breakroomTotalQuantityContainer"
						class="cart-product-name-container"></div>
					<div id="breakroomTotalPriceContainer"
						class="cart-product-price-container"></div>
					<div class="cart-product-quantity-container"></div>
					<div class="cart-product-action-container"></div>
					<%-- <span class="save-order-container" onclick="showSaveOrderModal()">
						<fmt:message key="CART_SAVE_THE_ORDER_LABEL" /> </span> --%>
					<span class="save-order-container" data-toggle="modal"
						data-target="#saveModal"> <fmt:message
							key="CART_SAVE_THE_ORDER_LABEL" /> </span>
				</div>


				<%-- OFFICE SUPPLIES --%>
				<div
					class="cart-section-title-container cart-product-color-alternate-container">
					<div class="cart-product-image-container"></div>
					<div class="cart-section-title-text-container">
						<fmt:message key="REVIEW_CART_OFFICE_SUPPLIES_LABEL" />
					</div>
					<%-- 	<span class="include-saved-order-container"
						onclick="showIncludeOrderModal()"> <fmt:message
							key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" /> </span> --%>
					<span class="include-saved-order-container" data-toggle="modal"
						data-target="#includeSavedModal"> <fmt:message
							key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" /> </span>
				</div>
				<c:set var="officeSuppliesIndex" value="0" scope="page" />

				<c:forEach var="product" items="${officeSuppliesProducts}">
					<div
						id="cartOrder${officeSuppliesOrders.get(officeSuppliesIndex).getId()}"
						class="cart-product-container cart-product-color-alternate-container">
						<div class="cart-product-image-container">
							<img src="${product.getImage()}" />
						</div>
						<div class="cart-product-name-container">
							${product.getName()}</div>
						<div
							class="cart-product-price-container
								office-supplies-cart-product-price-container"
							value="${product.getPrice()}">${product.getPrice()}</div>
						<div class="cart-product-quantity-container">
							<input
								id="cartProductQuantityInput${officeSuppliesOrders.get(officeSuppliesIndex).getId()}"
								class="office-supplies-cart-product-quantity-input"
								onchange="calcOfficeSuppliesTotalsAndUpdate(${product.getId()}, ${officeSuppliesOrders.get(officeSuppliesIndex).getId()})"
								type="text"
								value="${officeSuppliesOrders.get(officeSuppliesIndex).getQuantity()}" />
						</div>
						<div class="cart-product-action-container">
							<div class="glyphicon glyphicon-trash"
								onclick="removeFromCart(${officeSuppliesOrders.get(officeSuppliesIndex).getId()})"></div>
						</div>
					</div>
					<c:set var="officeSuppliesIndex" value="${officeSuppliesIndex + 1}"
						scope="page" />
				</c:forEach>

				<div id="officeSuppliesSummaryContainer"
					class="cart-product-color-alternate-container cart-summary-container">
					<div class="cart-product-image-container"></div>
					<div id="officeSuppliesTotalQuantityContainer"
						class="cart-product-name-container"></div>
					<div id="officeSuppliesTotalPriceContainer"
						class="cart-product-price-container"></div>
					<div class="cart-product-quantity-container"></div>
					<div class="cart-product-action-container"></div>
					<%-- <span class="save-order-container" onclick="showSaveOrderModal()">
						<fmt:message key="CART_SAVE_THE_ORDER_LABEL" /> </span> --%>
					<span class="save-order-container" data-toggle="modal"
						data-target="#saveModal"> <fmt:message
							key="CART_SAVE_THE_ORDER_LABEL" /> </span>

				</div>

				<%-- INK AND TONER --%>
				<div
					class="cart-section-title-container cart-product-color-alternate-container">
					<div class="cart-product-image-container"></div>
					<div class="cart-section-title-text-container">
						<fmt:message key="REVIEW_CART_INK_AND_TONER_LABEL" />
					</div>
					<%-- <span class="include-saved-order-container"
						onclick="showIncludeOrderModal()"> <fmt:message
							key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" /> </span> --%>

					<span class="include-saved-order-container" data-toggle="modal"
						data-target="#includeSavedModal"> <fmt:message
							key="CART_INCLUDE_FROM_SAVED_ORDER_LABEL" /> </span>
				</div>
				<c:set var="inkIndex" value="0" scope="page" />

				<c:forEach var="product" items="${inkAndTonerProducts}">
					<div id="cartOrder${inkAndTonerOrders.get(inkIndex).getId()}"
						class="cart-product-container cart-product-color-alternate-container">
						<div class="cart-product-image-container">
							<img src="${product.getImage()}" />
						</div>
						<div class="cart-product-name-container">
							${product.getName()}</div>
						<div
							class="cart-product-price-container
								ink-cart-product-price-container"
							value="${product.getPrice()}">${product.getPrice()}</div>
						<div class="cart-product-quantity-container">
							<input
								id="cartProductQuantityInput${inkAndTonerOrders.get(inkIndex).getId()}"
								class="ink-cart-product-quantity-input"
								onchange="calcInkAndTonerTotalsAndUpdate(${product.getId()}, ${inkAndTonerOrders.get(inkIndex).getId()})"
								type="text"
								value="${inkAndTonerOrders.get(inkIndex).getQuantity()}" />
						</div>
						<div class="cart-product-action-container">
							<div class="glyphicon glyphicon-trash"
								onclick="removeFromCart(${inkAndTonerOrders.get(inkIndex).getId()})"></div>
						</div>
					</div>
					<c:set var="inkIndex" value="${inkIndex + 1}" scope="page" />
				</c:forEach>
			</div>

			<div id="inkAndTonerOrdersSummaryContainer"
				class="cart-product-color-alternate-container cart-summary-container">
				<div class="cart-product-image-container"></div>
				<div id="inkAndTonerTotalQuantityContainer"
					class="cart-product-name-container"></div>
				<div id="inkAndTonerTotalPriceContainer"
					class="cart-product-price-container"></div>
				<div class="cart-product-quantity-container"></div>
				<div class="cart-product-action-container"></div>
				<%-- <span class="save-order-container" onclick="showSaveOrderModal()">
					<fmt:message key="CART_SAVE_THE_ORDER_LABEL" /> </span> --%>
				<span class="save-order-container" data-toggle="modal"
					data-target="#saveModal"> <fmt:message
						key="CART_SAVE_THE_ORDER_LABEL" /> </span>
			</div>

			<%-- CART SUMMARY --%> <footer id="cartTaxAndGrandTotalContainer">
				<div id="cartTaxContainer">
					<fmt:message key="CART_TAX_PERCENTAGE_LABEL" />
					<span id="cartTaxAmountContainer"></span>
				</div>

				<div id="cartGrandTotalContainer">
					<fmt:message key="CART_TOTAL_LABEL" />
					<span id="cartGrandTotalAmountContainer"></span>
				</div>
			</footer> <!-- INCLUDE SAVED ORDER MODAL -->
			<div class="modal fade" id="includeSavedModal" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-body">
							<div id="includeSavedOrderModalTitleContainer">
								<fmt:message key="CART_SAVED_ORDERS_LABEL" />
								</span> <select id="includeSavedOrderModalOrderSelect"
									onchange="populateItemsFromOrders()">
									<option value="" selected>
										<fmt:message key="CART_SELECT_LABEL" />
									</option>
									<c:forEach var="orderName" items="${savedOrders.keySet()}">
										<option value="${orderName}">${orderName}</option>
									</c:forEach>
								</select>
							</div>
							<c:forEach var="orderName" items="${savedOrders.keySet()}">
								<div id="${orderName}OrderContainer" class="order-container">
									<div
										class="order-item-container
								cart-modal-color-alternate-container">
										<div class="order-item-input-container">
											<input id="${orderName}OrderTitleCheckbox" type="checkbox"
												onchange="selectAllOrderCheckboxes('${orderName}')" />
										</div>
										<div class="order-title-text-container items-label">
											<fmt:message key="CART_ITEMS_LABEL" />
										</div>
									</div>
									<!-- GENERATE A DIV FOR EACH ITEM IN AN ORDER -->
									<c:set var="orderIndex" value="0" scope="page" />
									<!--<c:set var="curOrderName" value="${orderName}" scope="page"/>-->
									<c:forEach var="orderItem"
										items="${savedOrders.get(orderName)}">
										<div
											class="order-item-container
									cart-modal-color-alternate-container">
											<div class="order-item-input-container">
												<input
													id="includeOrderCheckbox${savedOrders[orderName].get(orderIndex).getId()}"
													class="include-order-checkbox-${savedOrders[orderName].get(orderIndex).getOrderName()}"
													type="checkbox" />
											</div>
											<div class="order-title-text-container">
												${savedProducts.get(curOrderName)[orderIndex].getName()}</div>
											<input
												id="includeOrderProductId${savedOrders[orderName].get(orderIndex).getId()}"
												type="hidden"
												value="${savedProducts.get(curOrderName)[orderIndex].getId()}" />
										</div>
										<c:set var="orderIndex" value="${orderIndex + 1}" scope="page" />
									</c:forEach>
								</div>
							</c:forEach>
							<div class="modal-footer">

								<div id="includeSavedOrderModalButtonContainer"
									class="cart-modal-button-container">
									<button type="button" class="super-admin-button"
										data-dismiss="modal">
										<bean:message key="SUPER_ADMIN_CLOSE_LABEL" />
									</button>

									<button id="includeSavedOrderModalButton"
										class="project-button" onclick="includeOrderInCart()"
										data-dismiss="modal">
										<fmt:message key="CART_INCLUDE_LABEL" />
									</button>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

			<%--<div id="includeSavedOrderModal" class="cart-modal">
				<div id="includeSavedOrderModalTitleContainer">
					<span id="closeButton" class="closeButton"
						onclick="closeIncludeInOrderWindow()">X</span> <span
						id="includeSavedOrderModalTitleTextContainer"> <fmt:message
							key="CART_SAVED_ORDERS_LABEL" /> </span> <select
						id="includeSavedOrderModalOrderSelect"
						onchange="populateItemsFromOrders()">
						<option value="" selected>
							<fmt:message key="CART_SELECT_LABEL" />
						</option>
						<c:forEach var="orderName" items="${savedOrders.keySet()}">
							<option value="${orderName}">${orderName}</option>
						</c:forEach>
					</select>
				</div>
				<c:forEach var="orderName" items="${savedOrders.keySet()}">
					<div id="${orderName}OrderContainer" class="order-container">
						<div
							class="order-item-container
								cart-modal-color-alternate-container">
							<div class="order-item-input-container">
								<input id="${orderName}OrderTitleCheckbox" type="checkbox"
									onchange="selectAllOrderCheckboxes('${orderName}')" />
							</div>
							<div class="order-title-text-container items-label">
								<fmt:message key="CART_ITEMS_LABEL" />
							</div>
						</div>
						<!-- GENERATE A DIV FOR EACH ITEM IN AN ORDER -->
						<c:set var="orderIndex" value="0" scope="page" />
						<!--<c:set var="curOrderName" value="${orderName}" scope="page"/>-->
						<c:forEach var="orderItem" items="${savedOrders.get(orderName)}">
							<div
								class="order-item-container
									cart-modal-color-alternate-container">
								<div class="order-item-input-container">
									<input
										id="includeOrderCheckbox${savedOrders[orderName].get(orderIndex).getId()}"
										class="include-order-checkbox-${savedOrders[orderName].get(orderIndex).getOrderName()}"
										type="checkbox" />
								</div>
								<div class="order-title-text-container">
									${savedProducts.get(curOrderName)[orderIndex].getName()}</div>
								<input
									id="includeOrderProductId${savedOrders[orderName].get(orderIndex).getId()}"
									type="hidden"
									value="${savedProducts.get(curOrderName)[orderIndex].getId()}" />
							</div>
							<c:set var="orderIndex" value="${orderIndex + 1}" scope="page" />
						</c:forEach>
					</div>
				</c:forEach>
				<div id="includeSavedOrderModalButtonContainer"
					class="cart-modal-button-container">
					<button id="includeSavedOrderModalButton" class="project-button"
						onclick="includeOrderInCart()">
						<fmt:message key="CART_INCLUDE_LABEL" />
					</button>
				</div>
			</div> --%> <!-- SAVE ORDER MODAL -->

			<div class="modal fade" id="saveModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-body">
							<div id="saveOrderModalTitleContainer">
								<span id="saveOrderModalTitleTextContainer"> <fmt:message
										key="CART_NAME_LABEL" /> </span> <input
									id="saveOrderModalOrderInput" type="text" />
							</div>

							<div id="cartOrderContainer" class="cart-order-container">
								<div
									class="order-item-container
							cart-modal-color-alternate-container">
									<div class="order-item-input-container">
										<input id="saveOrderTitleCheckbox" type="checkbox"
											onchange="selectAllSaveCheckboxes()" />
									</div>
									<div class="order-title-text-container items-label">
										<fmt:message key="CART_ITEMS_LABEL" />
									</div>
								</div>
								<!-- GENERATE A DIV FOR EACH ITEM IN AN ORDER -->
								<c:set var="breakroomModalIndex" value="0" scope="page" />
								<c:forEach var="product" items="${breakroomProducts}">
									<div
										class="order-item-container
								cart-modal-color-alternate-container">
										<div class="order-item-input-container">
											<input
												id="saveOrderCheckbox${breakroomProducts.get(breakroomModalIndex).getId()}"
												class="save-order-checkbox" type="checkbox" />
										</div>
										<div class="order-title-text-container">
											${breakroomProducts.get(breakroomModalIndex).getName()}</div>
									</div>
									<c:set var="breakroomModalIndex"
										value="${breakroomModalIndex + 1}" scope="page" />
								</c:forEach>

								<c:set var="officeSuppliesModalIndex" value="0" scope="page" />
								<c:forEach var="product" items="${officeSuppliesProducts}">
									<div
										class="order-item-container
								cart-modal-color-alternate-container">
										<div class="order-item-input-container">
											<input
												id="saveOrderCheckbox${officeSuppliesProducts.get(officeSuppliesModalIndex).getId()}"
												class="save-order-checkbox" type="checkbox" />
										</div>
										<div class="order-title-text-container">
											${officeSuppliesProducts.get(officeSuppliesModalIndex).getName()}
										</div>
									</div>
									<c:set var="officeSuppliesModalIndex"
										value="${officeSuppliesModalIndex + 1}" scope="page" />
								</c:forEach>

								<c:set var="inkModalIndex" value="0" scope="page" />
								<c:forEach var="product" items="${inkAndTonerProducts}">
									<div
										class="order-item-container
								cart-modal-color-alternate-container">
										<div class="order-item-input-container">
											<input
												id="saveOrderCheckbox${inkAndTonerProducts.get(inkModalIndex).getId()}"
												class="save-order-checkbox" type="checkbox" />
										</div>
										<div class="order-title-text-container">
											${inkAndTonerProducts.get(inkModalIndex).getName()}</div>
									</div>
									<c:set var="inkModalIndex" value="${inkModalIndex + 1}"
										scope="page" />
								</c:forEach>
							</div>
							<div class='modal-footer'>
								<div id="includeSavedOrderModalButtonContainer"
									class="cart-modal-button-container">
									<button type="button" class="super-admin-button"
										data-dismiss="modal">
										<bean:message key="SUPER_ADMIN_CLOSE_LABEL" />
									</button>
									<button id="includeSavedOrderModalButton" onclick="saveOrder()"
										class="project-button" data-dismiss="modal">
										<fmt:message key="CART_SAVE_LABEL" />
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<%-- <div id="saveOrderModal" class="cart-modal">
				<div id="saveOrderModalTitleContainer">
					<span id="closeButton" class="closeButton"
						onclick="closeSavedOrdersWindow()">X</span> <span
						id="saveOrderModalTitleTextContainer"> <fmt:message
							key="CART_NAME_LABEL" /> </span> <input id="saveOrderModalOrderInput"
						type="text" />
				</div>
				<div id="cartOrderContainer" class="cart-order-container">
					<div
						class="order-item-container
							cart-modal-color-alternate-container">
						<div class="order-item-input-container">
							<input id="saveOrderTitleCheckbox" type="checkbox"
								onchange="selectAllSaveCheckboxes()" />
						</div>
						<div class="order-title-text-container items-label">
							<fmt:message key="CART_ITEMS_LABEL" />
						</div>
					</div>
					<!-- GENERATE A DIV FOR EACH ITEM IN AN ORDER -->
					<c:set var="breakroomModalIndex" value="0" scope="page" />
					<c:forEach var="product" items="${breakroomProducts}">
						<div
							class="order-item-container
								cart-modal-color-alternate-container">
							<div class="order-item-input-container">
								<input
									id="saveOrderCheckbox${breakroomProducts.get(breakroomModalIndex).getId()}"
									class="save-order-checkbox" type="checkbox" />
							</div>
							<div class="order-title-text-container">
								${breakroomProducts.get(breakroomModalIndex).getName()}</div>
						</div>
						<c:set var="breakroomModalIndex"
							value="${breakroomModalIndex + 1}" scope="page" />
					</c:forEach>
					<c:set var="officeSuppliesModalIndex" value="0" scope="page" />
					<c:forEach var="product" items="${officeSuppliesProducts}">
						<div
							class="order-item-container
								cart-modal-color-alternate-container">
							<div class="order-item-input-container">
								<input
									id="saveOrderCheckbox${officeSuppliesProducts.get(officeSuppliesModalIndex).getId()}"
									class="save-order-checkbox" type="checkbox" />
							</div>
							<div class="order-title-text-container">
								${officeSuppliesProducts.get(officeSuppliesModalIndex).getName()}
							</div>
						</div>
						<c:set var="officeSuppliesModalIndex"
							value="${officeSuppliesModalIndex + 1}" scope="page" />
					</c:forEach>
					<c:set var="inkModalIndex" value="0" scope="page" />
					<c:forEach var="product" items="${inkAndTonerProducts}">
						<div
							class="order-item-container
								cart-modal-color-alternate-container">
							<div class="order-item-input-container">
								<input
									id="saveOrderCheckbox${inkAndTonerProducts.get(inkModalIndex).getId()}"
									class="save-order-checkbox" type="checkbox" />
							</div>
							<div class="order-title-text-container">
								${inkAndTonerProducts.get(inkModalIndex).getName()}</div>
						</div>
						<c:set var="inkModalIndex" value="${inkModalIndex + 1}"
							scope="page" />
					</c:forEach>
				</div>
				<div id="includeSavedOrderModalButtonContainer"
					class="cart-modal-button-container">
					<button id="includeSavedOrderModalButton" onclick="saveOrder()"
						class="project-button">
						<fmt:message key="CART_SAVE_LABEL" />
					</button>
				</div>
			</div>--%> </main>
			<aside>
				<%@ include file="../nav.jsp"%>
			</aside>
		</div>
	</div>
	<script type="text/javascript"
		src="/SiriusOrderClient/js/calcCartTotal.js"></script>
</body>
</html>