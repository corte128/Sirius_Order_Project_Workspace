/**
 * 
 */
google.load("visualization", "1", { packages: ["table", "corechart"] });
var app = angular.module('chartApp');
app.controller('BudgetChartCtrl', ['$scope', '$http',  function ($scope, $http) 
{
	function generateChart() 
	{
		var locationId = document.getElementById("budgetSearchLocationInput").value;
		var reportType = document.getElementById("budgetSearchReportTypeInput").value;
		var fromDate = document.getElementById("budgetSearchFromDateTypeInput").value;
		var toDate = document.getElementById("budgetSearchToDateTypeInput").value;
		// Define the chart to be drawn.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Time');
		data.addColumn('number', 'Budget');
		data.addColumn('number', 'Actual');
		$http.get("/SiriusOrderClient/BudgetServlet?action=searchBudget&locationId=" +
				locationId + "&reportType=" +
				reportType + "&fromDate=" + 
				fromDate + "&toDate=" + 
				toDate)
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


