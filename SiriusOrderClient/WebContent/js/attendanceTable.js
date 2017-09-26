/**
 * 
 */

var app = angular.module('attendanceTable', [ 'ngTouch', 'ui.grid',
		'ui.grid.pagination', 'ui.grid.autoResize' ]);


function validateAttendance() {

	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var range = document.getElementById("range").value;
	var view = document.getElementById("view").value;
	var downloadDiv = document.getElementById("downloadDiv");
	var displayDiv = document.getElementById("displayDiv"); 
	
	
	var viewSelectError = document
			.getElementById("viewSelectError");
	var dateSelectError = document
			.getElementById("dateSelectError");
	if (view == 'PDF' || view == 'display') {
		viewSelectError.style.display = 'none';
	} else {
		viewSelectError.style.display = 'block';
		downloadDiv.style.display = "none";
		displayDiv.style.display = "none";
		return false;
	}
	if ((range != '%') && (startDate != '' || endDate != '')) {
		dateSelectError.style.display = 'block';
		return false;
	} else {
		dateSelectError.style.display = 'none';
	}
}


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
					width : 125,
					pinnedLeft : true
				}, {
					name : 'Email',
					width : 150
				}, {
					name : 'Date',
					width : 100,
					pinnedRight : true
				}, {
					name : 'Location',
					width : 125
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
				if (validated==false){
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
					downloadDiv.style.display = "block";
					displayDiv.style.display = "none";
				} else if (view == 'display') {
					var downloadDiv = document.getElementById("downloadDiv");
					var displayDiv = document.getElementById("displayDiv");
					downloadDiv.style.display = "none";
					displayDiv.style.display = "block";
				} else {
					var downloadDiv = document.getElementById("downloadDiv");
					var displayDiv = document.getElementById("displaydDiv");
					downloadDiv.style.display = "none";
					displayDiv.style.display = "none";
				}

				$http.get(
						"/SiriusOrderClient/AttendanceServlet?action=getAttendance&name="
								+ name + "&email=" + email + "&startDate="
								+ startDate + "&endDate=" + endDate
								+ "&location=" + location + "&range=" + range
								+ "&view=" + view).then(function(response) {

					console.log(response.data);
					$scope.gridOptions.data = response.data;
				});
			}
			$scope.getSearch = getSearch;

		} ]);