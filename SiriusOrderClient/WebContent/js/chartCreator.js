/**
 * 
 */
  google.load("visualization", "1", { packages: ["table", "corechart"] });
var app = angular.module('chartApp', ['ngTouch', 'ngAnimate']);
app.controller('BudgetChartCtrl', ['$scope', '$http', '$q',  function ($scope, $http, $q) 
{
	window.onload = function() 
	{
		// Define the chart to be drawn.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Time');
		data.addColumn('number', 'Budget');
		data.addColumn('number', 'Actual');
		$http.get("/SiriusOrderClient/BudgetServlet?action=searchBudget&")
	   	.then(function(response) 
	   	{
	   		alert(response.data);
	   		console.log(response.data);
	   		var dataArray = [];
	   		$.each(response.data, function(i, obj) {
	   			dataArray.push([obj.Time, obj.Budget, obj.Actual]);
	   			console.log(obj.Time);
	   		});
	   		data.addRows(dataArray);
	   		// Instantiate and draw the chart.
	   		var chart = new google.visualization.BarChart(document.getElementById('myChart'));
			chart.draw(data, {colors: ['#A7C1C3', '#B0B47A'], bar: {groupWidth: '80%'}, height: 400, width: 500});
	   	});
    };
}]);


