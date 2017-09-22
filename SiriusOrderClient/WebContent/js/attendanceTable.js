/**
 * 
 */

var app = angular.module('attendanceTable', [ 'ngTouch', 'ui.grid' ]);

app.controller('AttendanceCtrl', [
		'$scope',
		'$http',
		function($scope, $http) {
			$scope.gridOptions = {
				data : []
//				columnDefs : [ {
//					name : 'Name',
//					width : 150,
//					pinnedLeft : true
//				}, {
//					name : 'Email',
//					width : 150
//				}, {
//					name : 'Date',
//					width : 100,
//					pinnedRight : true
//				}, {
//					name : 'Location',
//					width : 100
//				} ]
			};
			
							function getSearch(){
								
								var name = document.getElementById("name").value;
								var email = document.getElementById("email").value;
								var location = document.getElementById("locationSelect").value;
								var startDate = document.getElementById("startDate").value;
								var endDate = document.getElementById("endDate").value;
								var range = document.getElementById("range").value;
								var view = document.getElementById("view").value;
								
								if(name == ''){
									name = "%";
								}
								if(email == ''){
									email="%";
								}
								if (view == 'PDF'){
									var downloadDiv = document.getElementById("downloadDiv");
									downloadDiv.style.display = "block";
								}else{
									var downloadDiv = document.getElementById("downloadDiv");
									downloadDiv.style.display = "none";
								}
								
							
								
							$http
									.get("/SiriusOrderClient/AttendanceServlet?action=getAttendance&name="+name+"&email="+email+"&startDate="+startDate+"&endDate="+endDate+
											"&location="+location+"&range="+range+"&view="+view)
									.then(
											function(response) {


							console.log(response.data);
							$scope.gridOptions.data = response.data;
						});
			}
			$scope.getSearch = getSearch;

			}]);