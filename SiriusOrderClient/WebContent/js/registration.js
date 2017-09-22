function checkEmail(){
	email=document.getElementById("registration-email").value;
	if (email){
		console.log(email);
		var xhttp = new XMLHttpRequest();
		var url = "/SiriusOrderClient/QueryServlet?query=checkEmail&email="+email;
		xhttp.open("GET", url, true);
		var message = document.getElementById("email-taken-error");
		var button = document.getElementById("registration-button");
		xhttp.onreadystatechange = function(){
			var response = xhttp.responseText;
			console.log(response);
			if(response == "true"){
				//display error
	        	console.log("Email is taken");
	        	message.style.display = 'inline';
	        	button.disabled = true;
	        }
	        else if(response == "false") {
	        	//all is good
	        	console.log("Email is free");
	        	message.style.display = 'none';
	        	button.disabled = false;
	        }
		};
		xhttp.send();
	}
};