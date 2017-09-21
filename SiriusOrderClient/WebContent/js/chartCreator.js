/**
 * 
 */
$(document).ready(function() {
    $('#budgetGrid').DataTable( {
        "pagingType": "full_numbers"
    } );
});
google.load("visualization", "1", { packages: ["table", "corechart"] });
var app = angular.module('chartApp', ['ngTouch', 'ngAnimate']);
app.controller('BudgetChartCtrl', ['$scope', '$http',  function ($scope, $http) 
{
	$scope.generateChart = function()
	{
		//get inputed parameters to query by
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
	   		//console.log(response.data);
	   		
	   		// Instantiate and draw the chart
	   		data.addRows(response.data);
	   		var numRows = response.data.length;
			var expectedHeight = numRows * 100;
	   		var chart = new google.visualization.BarChart(document.getElementById('budgetChartContainer'));
			chart.draw(data, {colors: ['#A7C1C3', '#B0B47A'], bar: {groupWidth: '90%'}, height: expectedHeight});
			

			var table = $('#budgetGrid').DataTable();
			table.clear();
			for(key in response.data)
			{
				response.data[key].push(response.data[key][1]-response.data[key][2]);
				console.log(response.data[key]);
				table.row.add(response.data[key]).draw();
			}
			
			var displayOption = $('#budgetSearchViewInput').value;
			if(displayOption == 'PDF')
			{	
			
			}
	   	});
    };
}]);


