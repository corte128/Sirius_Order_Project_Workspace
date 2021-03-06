function checkEmail(){
	email=document.getElementById("registration-email").value;
	if (email){
		console.log(email);
		var xhttp = new XMLHttpRequest();
		var url = "/SiriusOrderClient/QueryServlet?query=checkEmail&email="+email;
		xhttp.open("GET", url, true);
		var message = document.getElementById("email-taken-error");
		var button = document.getElementById("registration-button");
		var error = document.getElementById("email-error-spacing");
		var invalidError = document.getElementById("invalid-email");
		var errorbool = false;
		if(!email.match("^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?(siriuscom)\\.com$")){
			invalidError.style.display = 'inline';
			button.disabled = true;
			errorbool = true;
			error.style.display = 'block';
		}
		else{
			invalidError.style.display = 'none';
			button.disabled = false;
			errorbool = false;
			error.style.display = 'none';
		}
		if (errorbool == false){
			xhttp.onreadystatechange = function(){
				var response = xhttp.responseText;
				console.log(response);
				if(response == "true"){
					//display error
		        	console.log("Email is taken");
		        	message.style.display = 'inline';
		        	button.disabled = true;
		        	error.style.display = 'block';
		        }
		        else if(response == "false") {
		        	//all is good
		        	console.log("Email is free");
		        	message.style.display = 'none';
		        	error.style.display = 'none';
		        	button.disabled = false;
		        }
			};
			xhttp.send();
		}
	}
};
function changeFileName(){
	var nameField = document.getElementById("chosen-file");
	var fileChosen = document.getElementById("photo-field");
	var pathName = fileChosen.value.replace(/^.*\\/, "");
	nameField.innerHTML = pathName;
};