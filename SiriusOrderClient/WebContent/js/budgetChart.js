/**
 * 
 */
var app = angular.module('chartApp', ['ngTouch', 'ngAnimate', 'ui.bootstrap']);

app.controller('AssignEmployeesCtrl', ['$scope', '$http', '$q',  function ($scope, $http, $q) 
{
	function drawChart() 
	{
		// Define the chart to be drawn.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Element');
		data.addColumn('number', 'Percentage');
		
		$http.get("/ACEWeb/BudgetServlet?action=searchBudget&")
	   	.then(function(response) 
	   	{
	   		data.addRows(response.data);
	   		// Instantiate and draw the chart.
	   		var chart = new google.visualization.BarChart(document.getElementById('myChart'));
			chart.draw(data, null);
	   	});
    }
}]);