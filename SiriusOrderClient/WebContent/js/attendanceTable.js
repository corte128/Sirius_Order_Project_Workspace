/**
 * 
 */

var app = angular.module('attendanceTable', [ 'ngTouch', 'ui.grid' ]);

app.controller(
				'AttendanceCtrl',
				[
						'$scope',
						'$http',
						'$q',
						function($scope, $http, $q) {

							$scope.gridOptions = {
								data : [],
								columnDefs : [
										{
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
								
								var firstName = document.getElementById("firstName").value;
								var lastName = document.getElementById("lastName").value;
								var email = document.getElementById("email").value;
								var role = document.getElementById("role").value;
								if(firstName==''){
									firstName = "%";
								}
								if(lastName == ''){
									lastName="%";
								}
								if(email == ''){
									email="%";
								}

							$http
									.get("/SiriusOrderClient/AttendanceServlet?query=getRecords?name="+name+"?email="+email+"?startDate="+startDate+"?endDate="+endDate+
											"?location="+location+"?range="+range)
									.then(
											function(response) {

												console.log(response.data);
												$scope.gridOptions.data = response.data;
											});
							}
							$scope.getSearch =getSearch;
							

}]);