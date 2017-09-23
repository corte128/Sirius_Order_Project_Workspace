/**
 * 
 */

function calcBreakroomTotals()
{
	var price = document.getElementById("breakroomPrice");
	price = price.slice(1);
	var quantity = document.getElementById("breakroomQuantity");
	var output = price*quantity;
	document.getElementById("breakroomTotalAmount").innerHTML = $ + output;	
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

