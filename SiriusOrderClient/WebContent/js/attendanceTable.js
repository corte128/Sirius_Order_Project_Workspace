/**
 * 
 */

var app = angular.module('attendanceTable', [ 'ngTouch', 'ui.grid' ]);

app.controller('AttendanceCtrl', [
		'$scope',
		'$http',
		'$q',
		function($scope, $http, $q) {
			$scope.gridOptions = {
				data : [],
				columnDefs : [ {
					name : 'Name',
					width : 130,
					pinnedLeft : true
				}, {
					name : 'Email',
					width : 250
				}, {
					name : 'Date',
					width : 130,
					pinnedRight : true
				}, {
					name : 'Location',
					width : 130
				} ]
			};
			
							function getSearch(){
								
								var name = document.getElementById("name").value;
								var email = document.getElementById("email").value;
								var location = document.getElementById("locationSelect").value;
								var startDate = document.getElementById("startDate").value;
								var endDate = document.getElementById("endDate").value;
								var range = document.getElementById("range").value;
								if(name == ''){
									name = "%";
								}
								if(email == ''){
									email="%";
								}
								
								alert("name given: " + name);
								alert("email given: " + email);
								alert("location given: " + location);
								alert("startDate given: " + startDate);
								alert("endDate given: " + endDate);
								alert("range given: " + range);
								
							$http
									.get("/SiriusOrderClient/AttendanceServlet?action=getAttendance&name="+name+"&email="+email+"&startDate="+startDate+"&endDate="+endDate+
											"&location="+location+"&range="+range)
									.then(
											function(response) {


							console.log(response.data);
							$scope.gridOptions.data = response.data;
						});
			}
			$scope.getSearch = getSearch;

			}]);