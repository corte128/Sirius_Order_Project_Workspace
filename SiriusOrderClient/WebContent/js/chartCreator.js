/**
 * 
 */
google.load("visualization", "1", { packages: ["table", "corechart"] });
var app = angular.module('chartApp', ['ngTouch', 'ngAnimate']);
app.controller('BudgetChartCtrl', ['$scope', '$http', '$q',  function ($scope, $http, $q) 
{
	function generateChart() 
	{
		
		// Define the chart to be drawn.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Time');
		data.addColumn('number', 'Budget');
		data.addColumn('number', 'Actual');
		$http.get("/SiriusOrderClient/BudgetServlet?action=searchBudget&locationId=" +
				)
	   	.then(function(response) 
	   	{
	   		alert(response.data);
	   		console.log(response.data);
	   		data.addRows(response.date);
	   		// Instantiate and draw the chart.
	   		var chart = new google.visualization.BarChart(document.getElementById('budgetChartContainer'));
			chart.draw(data, {colors: ['#A7C1C3', '#B0B47A'], bar: {groupWidth: '80%'}, height: 400, width: 500});
	   	});
    };
}]);


