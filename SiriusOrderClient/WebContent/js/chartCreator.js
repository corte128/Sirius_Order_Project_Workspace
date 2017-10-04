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
		var view = document.getElementById("budgetSearchViewInput").value;
		// Define the chart to be drawn.
		
		$http.get("/SiriusOrderClient/BudgetServlet?action=searchBudget&view=" + view + "&locationId=" +
				locationId + "&reportType=" +
				reportType + "&fromDate=" + 
				fromDate + "&toDate=" + 
				toDate)
	   	.then(function(response) 
	   	{
	   		//console.log(response.data);
			var displayOption = document.getElementById("budgetSearchViewInput").value;
			if(response.data.invalidDate || (displayOption == '') || (reportType == '')){
				validateAttendance(response.data.invalidDate);
			}
			else{
				document.getElementById('invalidView').style.display = 'none';
				document.getElementById('invalidReport').style.display = 'none';
				document.getElementById('invalidDate').style.display = 'none';
				if(displayOption == 'Display'){
					var downloadDiv = document.getElementById("downloadDiv");
					downloadDiv.style.visibility = 'hidden';
					document.getElementById('budgetChartGraph').style.visibility = 'visible';
					document.getElementById('budgetGridContainer').style.visibility = 'visible';
					
					// Adjust CSS for desktop view
					var mq = window.matchMedia("(min-width: 1024px)");
				    if(mq.matches){
				    	$("#budgetChartContainer").attr('style', 'flex: 1;');
				    }
					
					var data = new google.visualization.DataTable();
					data.addColumn('string', 'Time');
					data.addColumn('number', 'Budget');
					data.addColumn('number', 'Actual');
			   		// Instantiate and draw the chart
					
			   		data.addRows(response.data);
			   		var numRows = response.data.length;
					var expectedHeight = numRows * 100;
					var expectedWidth = screen.width;
					if(screen.width > 769){
						expectedWidth /= 2;
					}
					
			   		var chart = new google.visualization.BarChart(document.getElementById('budgetChartContainer'));
					var formatter = new google.visualization.NumberFormat({decimalSymbol: '.',groupingSymbol: ',', prefix: '$'});
									
					formatter.format(data, 1);
					formatter.format(data, 2);

					chart.draw(data, {
						colors: ['#A7C1C3', '#B0B47A'], 
						bar: {groupWidth: '90%'},
						hAxis: {minValue: 0}, 
						chartArea: {
							height: "80%",
							width: "50%"
						},
						height: expectedHeight
					});

					var table = $('#budgetGrid').DataTable();
					table.clear();
					
					for(key in response.data)
					{
						response.data[key].push(response.data[key][1]-response.data[key][2]);
						for(var i = 1; i < response.data[key].length; ++i){
							response.data[key][i] = '$' + Math.abs(response.data[key][i].toFixed(2));
						}
						table.row.add(response.data[key]).draw();
					}
				}
				if(displayOption == 'PDF')
				{	
					var downloadDiv = document.getElementById("downloadDiv");
					document.getElementById('budgetChartGraph').style.visibility = 'hidden';
					document.getElementById('budgetGridContainer').style.visibility = 'hidden';
					downloadDiv.style.visibility = 'visible';
				}
			}
	   	});
    };
}]);

function validateAttendance(response) {

	document.getElementById("downloadDiv").style.visibility = 'hidden';
	document.getElementById('budgetChartGraph').style.visibility = 'hidden';
	document.getElementById('budgetGridContainer').style.visibility = 'hidden';
//	var startDate = document.getElementById("budgetSearchFromDateTypeInput").value;
//	var endDate = document.getElementById("budgetSearchToDateTypeInput").value;
	var reportType = document.getElementById("budgetSearchReportTypeInput").value;
	var view = document.getElementById("budgetSearchViewInput").value;
	
	var viewDiv = document.getElementById('invalidView');
	var reportDiv = document.getElementById('invalidReport');
	var dateDiv = document.getElementById('invalidDate');
	
	if (view == '') {
		viewDiv.style.display = 'inline-block';
		reportDiv.style.display = 'none';
		dateDiv.style.display = 'none';
		return;
	}
	if(reportType == ''){
		reportDiv.style.display = 'inline-block';
		dateDiv.style.display = 'none';
		viewDiv.style.display = 'none';
		return;
	}
	if (response) {
		dateDiv.style.display = 'inline-block';
		reportDiv.style.display = 'none';
		viewDiv.style.display = 'none';
		return;
	}
}
