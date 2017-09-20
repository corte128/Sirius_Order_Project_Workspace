var app = angular.module('superAdminTable', [ 'ngTouch', 'ui.grid' ]);

app.controller('superAdminCtrl', [
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
								name : 'No of Employees',
								width : 100
							},
							{
								name : 'Recommended Budget',
								width : 100,
								cellTemplate : '<div class="ui-grid-cell-contents"><input type="text" class="super-admin-input" value=\'{{row.entity["Recommended Budget"]}}\'/></div>'
							},
							{
								name : 'Allotted Budget',
								width : 100,
								cellTemplate : '<div class="ui-grid-cell-contents"><input type="text" class="super-admin-input" value=\'{{row.entity["Allotted Budget"]}}\'/></div>'
							} ]
				};

				$http.get(
						"/SiriusOrderClient/SuperAdminServlet")
						.then(function(response) {
							$scope.gridOptions.data = response.data;
							console.log($scope.gridOptions.data);
						});
				

		} ]);

$(document).ready(function(){
	var officeData = [];
	
	$.getJSON( "/SiriusOrderClient/SuperAdminServlet?action=officeAdmin", function( data ){
		officeData = data;
		$.typeahead({
		    input: '.office-admin-input',
		    order: "desc",
		    source: {
		    	data: officeData
		    },
		    callback: {
		        onInit: function (node) {
		            console.log('Typeahead Initiated on ' + node.selector);
		        }
		    }
		});
	});
});