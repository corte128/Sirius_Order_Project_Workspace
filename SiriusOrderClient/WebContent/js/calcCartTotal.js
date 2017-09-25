/**
 * 
 */

function calcBreakroomTotals()
{
	var prices = document.getElementsByClassName("breakroom-cart-product-price-container");
	var quantities = document.getElementsByClassName("breakroom-cart-product-price-container");

	var totalPrice = 0;
	var itemTotal = 0;
	for(var i = 0; i < prices.length; ++i){
		price = prices[i].slice(1);
		totalPrice += price * quantities[i];
		itemTotal += quantities[i];
	}
	
	
	document.getElementById("breakroomTotalQuantityContainer").innerHTML = 'Breakroom Total(' + quantity + ' items)';
	
	document.getElementById("breakroomTotalPriceContainer").innerHTML = '$' + totalPrice;
}

function calcOfficeSuppliesTotals()
{
	var price = document.getElementById("officeSuppliesPrice");
	price = price.slice(1);
	var quantity = document.getElementById("officeSuppliesQuantity");
	var output = price*quantity;
	document.getElementById("officeSuppliesTotalAmount").innerHTML = $ + output;	
}

function calcInkAndTonerTotals()
{
	var price = document.getElementById("inkAndTonerPrice");
	price = price.slice(1);
	var quantity = document.getElementById("inkAndTonerQuantity");
	var output = price*quantity;
	document.getElementById("inkAndTonerTotalAmount").innerHTML = $ + output;
}

function removeFromCart(orderID){
	var url = '/SiriusOrderClient/CartServlet?action=removeFromCart&orderId=' + orderID;
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var productDiv = document.getElementById("cartOrder" + orderID);
//		var parentDiv = productDiv.parentElement.nodeName;
		productDiv.parentNode.removeChild(productDiv);
	};
	xhttp.send();
}

