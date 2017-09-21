/**
 * 
 */
$(document).ready(function() {
    $('#budgetGrid').DataTable( {
        "pagingType": "full_numbers"
    } );
});
google.load("visualization", "1", { packages: ["table", "corechart"] });
var app = angular.module('chartApp', ['ngTouch', 'ui.grid', 'ngAnimate', 'ui.bootstrap']);
app.controller('BudgetChartCtrl', ['$scope', '$http',  function ($scope, $http) 
{
	$scope.gridOptions = { data: [] 
		,columnDefs : [
		    { field: 'Time', displayName: ' ', width:70, cellTemplate:
		   		'<div class="ui-grid-cell-contents"><input class="delete-employee-checkbox" name="{{row.entity.ID}}" type="checkbox"></div>'},
			{ field:'Actual', width:130},
			{ field:'Budget', width:130},
			{ field:'Variance', width:130}]
	};
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
			
//			$('#budgetGridBody').empty();
//			for(key in response.data)
//			{
//				var timeColumn = $('<td></td>').html(response.data[key][0]);
//				var actualColumn = $('<td></td>').html(response.data[key][2]);
//				var budgetColumn = $('<td></td>').html(response.data[key][1]);
//				console.log(response.data[key][1]);
//				var varianceColumn = $('<td></td>').html(response.data[key][1]-response.data[key][2]);
//				
//				var tableRow = $('<tr></tr>');
//				tableRow.append(timeColumn, [actualColumn, budgetColumn, varianceColumn]);
//				
//				$('#budgetGridBody').append(tableRow);
//			}
			var table = $('#budgetGrid').DataTable();
			table.clear();
			for(key in response.data)
			{
				response.data[key].push(response.data[key][1]-response.data[key][2]);
				console.log(response.data[key]);
				table.row.add(response.data[key]).draw();
			}
	   	});
    };
}]);


