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

							$http
									.get("/SiriusOrderClient/AttendanceServlet")
									.then(
											function(response) {

												console.log(response.data);
												$scope.gridOptions.data = response.data;
											});

}]);