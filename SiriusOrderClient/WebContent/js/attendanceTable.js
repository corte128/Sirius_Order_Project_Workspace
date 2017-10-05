/**
 * 
 */

var app = angular.module('attendanceTable', [ 'ngTouch', 'ui.grid',
		'ui.grid.pagination', 'ui.grid.autoResize' ]);

function showHelp() {
	var instructionsDiv = document.getElementById("instructionsBox");
	instructionsDiv.style.display = 'block';
}

function closeHelp() {
	var instructionsDiv = document.getElementById("instructionsBox");
	instructionsDiv.style.display = 'none';
}

function validateAttendance() {

	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var range = document.getElementById("range").value;
	var view = document.getElementById("view").value;
	var downloadDiv = document.getElementById("downloadDiv");
	var displayDiv = document.getElementById("displayDiv");

	var viewSelectError = document.getElementById("viewSelectError");
	var dateSelectError = document.getElementById("dateSelectError");

	if (view != 'PDF' || view != 'display') {
		$('#invalidView').fadeIn('fast').delay(1000).fadeOut('slow');
		return false;
	}
//	else {
//		viewSelectError.style.display = 'block';
//		downloadDiv.style.display = "none";
//		displayDiv.style.display = "none";
//		return false;
//	}
	if ((range != '%') && (startDate != '' || endDate != '')) {
		//dateSelectError.style.display = 'block';
		$('#invalidDate').fadeIn('fast').delay(1000).fadeOut('slow');
		return false;
	}
//	 else {
//		dateSelectError.style.display = 'none';
//	}
}

// function checkForResults() {
//
// var name = document.getElementById("name").value;
// var email = document.getElementById("email").value;
// var location = document.getElementById("locationSelect").value;
// var startDate = document.getElementById("startDate").value;
// var endDate = document.getElementById("endDate").value;
// var range = document.getElementById("range").value;
// var view = document.getElementById("view").value;
//
// var url = "/SiriusOrderClient/AttendanceServlet?action=getAttendance&name="
// + name + "&email=" + email + "&startDate=" + startDate
// + "&endDate=" + endDate + "&location=" + location + "&range="
// + range + "&view=" + view;
//
// var xhttp = new XMLHttpRequest();
// xhttp.open("GET", url, true);
//	
// xhttp.onreadystatechange = function() {
// var response = JSON.parse(xhttp.responseText);
// if (response == null|| response == "") {
// alert("no data found");
// return false;
// }
// };
// xhttp.send();
//	
// }

app.controller('AttendanceCtrl', [
		'$scope',
		'$http',
		function($scope, $http) {
			$scope.gridOptions = {
				data : [],
				paginationPageSizes : [ 10, 20 ],
				paginationPageSize : 10,
				columnDefs : [ {
					name : 'Name',
					width : "25%",
					pinnedLeft : true
				}, {
					name : 'Email',
					width : "27%"
				}, {
					name : 'Date',
					width : "25%",
					pinnedRight : true
				}, {
					name : 'Location',
					width : "25%"
				} ]
			};

			function getSearch() {

				var name = document.getElementById("name").value;
				var email = document.getElementById("email").value;
				var location = document.getElementById("locationSelect").value;
				var startDate = document.getElementById("startDate").value;
				var endDate = document.getElementById("endDate").value;
				var range = document.getElementById("range").value;
				var view = document.getElementById("view").value;

				var validated = validateAttendance();
				if (validated == false) {
					return false;
				}

				if (name == '') {
					name = "%";
				}
				if (email == '') {
					email = "%";
				}
				if (view == 'PDF') {
					var downloadDiv = document.getElementById("downloadDiv");
					var displayDiv = document.getElementById("displayDiv");
					var noInfoFoundError = document
							.getElementById("noInfoFoundError");
					noInfoFoundError.style.display = "none";
					downloadDiv.style.display = "block";
					displayDiv.style.display = "none";
				} else if (view == 'display') {
					var downloadDiv = document.getElementById("downloadDiv");
					var displayDiv = document.getElementById("displayDiv");
					var noInfoFoundError = document
							.getElementById("noInfoFoundError");
					noInfoFoundError.style.display = "none";
					downloadDiv.style.display = "none";
					displayDiv.style.display = "block";
				}
				// else {
				// var downloadDiv = document.getElementById("downloadDiv");
				// var displayDiv = document.getElementById("displaydDiv");
				// var noInfoFoundError =
				// document.getElementById("noInfoFoundError");
				// noInfoFoundError.style.display = "none";
				// downloadDiv.style.display = "none";
				// displayDiv.style.display = "none";
				// }

				$http.get(
						"/SiriusOrderClient/AttendanceServlet?action=getAttendance&name="
								+ name + "&email=" + email + "&startDate="
								+ startDate + "&endDate=" + endDate
								+ "&location=" + location + "&range=" + range
								+ "&view=" + view).then(
						function(response) {

							console.log(response.data);

							if (response.data.length == 0) {

								var downloadDiv = document
										.getElementById("downloadDiv");
								var displayDiv = document
										.getElementById("displayDiv");
								var noInfoFoundError = document
										.getElementById("noInfoFoundError");
								noInfoFoundError.style.display = "block";
								downloadDiv.style.display = "none";
								displayDiv.style.display = "none";
							}
							$scope.gridOptions.data = response.data;
						});
			}
			$scope.getSearch = getSearch;
			var mq = window.matchMedia("(max-width: 500px)");
			if (mq.matches) {
				$scope.gridOptions.columnDefs[0].width = "50%";
				$scope.gridOptions.columnDefs[1].visible = false;
				$scope.gridOptions.columnDefs[2].width = "50%";
				$scope.gridOptions.columnDefs[3].visible = false;
			}

		} ]);

