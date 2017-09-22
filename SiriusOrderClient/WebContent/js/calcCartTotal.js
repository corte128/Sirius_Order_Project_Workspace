/**
 * 
 */

function calcBreakroomTotals()
{
	var price = document.getElementById("breakromPrice");
	var quantity = document.getElementById("breakroomQuantity");
	var output = price*quantity;
	document.getElementById("breakroomTotalAmount").innerHTML = $ + output;	
}

function calcOfficeSuppliesTotals()
{
	var price = document.getElementById("officeSuppliesPrice");
	var quantity = document.getElementById("officeSuppliesQuantity");
	var output = price*quantity;
	document.getElementById("officeSuppliesTotalAmount").innerHTML = $ + output;	
}

function calcInkAndTonerTotals()
{
	var price = document.getElementById("inkAndTonerPrice");
	var quantity = document.getElementById("inkAndTonerQuantity");
	var output = price*quantity;
	document.getElementById("inkAndTonerTotalAmount").innerHTML = $ + output;	
}

