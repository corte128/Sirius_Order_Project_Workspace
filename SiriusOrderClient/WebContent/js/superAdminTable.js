var superAdminTable = angular.module('superAdminTable', [ 'ngTouch',
		'ngAnimate', 'ui.bootstrap' ]);

superAdminTable
		.controller(
				'SuperAdminCtrl',
				[
						'$scope',
						'$http',
						'$q',
						function($scope, $http, $q) {

							$scope.gridOptions = {
								data : [],
								columnDefs : [
										{
											name : 'Location',
											width : 100,
											pinnedLeft : true
										},
										{
											name : 'Admin Name',
											width : 100
										},
										{
											name : 'Admin Email',
											width : 150
										},
										{
											name : 'No. Of Employees',
											width : 100
										},
										{
											name : 'Recommended Budget',
											width : 100,
											cellTemplate : '<div class="ui-grid-cell-contents"><input type="text" class="super-admin-input">{{row.entity.recomendedBudget}}</input></div>'
										},
										{
											name : 'Allotted Budget',
											width : 100,
											cellTemplate : '<div class="ui-grid-cell-contents"><input type="text" class="super-admin-input">{{row.entity.allottedBudget}}</input></div>'
										} ]
							};

							$http
									.get("/SiriusOrderClient/SuperAdminServlet")
									.then(
											function(response) {

												console.log(response.data);
												$scope.gridOptions.data = response.data;
											});

						} ]);