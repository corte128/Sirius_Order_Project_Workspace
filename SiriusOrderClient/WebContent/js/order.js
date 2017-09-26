/**
 * 
 */

function saveOrder(orderID){
	var xhttp = new XMLHttpRequest();
	var quantityElement = document.getElementById("quantityToAdd");
	var url = "/SiriusOrderClient/CartServlet?action=saveOrder&orderName=" + orderName;
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
//		var response = xhttp.responseText;
//		if(response != null){
//			console.log(response);
//			$('#tblAppendGrid').appendGrid('load', JSON.parse(response));
//		}
//		else{
//			console.log("Results are empty");
//		}
		//TO DO alert user
	};
	xhttp.send();
}