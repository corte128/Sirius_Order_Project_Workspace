/**
 * 
 */
function addToCart(productID)
{
	var xhttp = new XMLHttpRequest();
	var quantity = getElementbyID("quantityToAdd").value;
	if (quantity == null){
		quantity = 1;
	}
	var url = "/SiriusOrderClient/CartServlet?query=addToCart&productID=" + productID +"&quantity="+quantity;
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

