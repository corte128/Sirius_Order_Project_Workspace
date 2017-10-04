var app = angular.module('superAdminTable', [ 'ngTouch', 'ui.grid' ]);

app.controller('superAdminCtrl', [
		'$scope',
		'$http',
		'$q',
		function($scope, $http, $q) {
			var locationHeaderCellTemplate = '<div class="ngHeaderSortColumn ngHeaderSortColumnLocation {{col.headerClass}}" ng-style="{cursor: col.cursor}" ng-class="{ ngSorted: !noSortVisible }">'+
            '<div ng-click="col.sort($event)" ng-class="\'colt\' + col.index" class="ngHeaderText">{{col.displayName}}</div>'+
            '<div class="ngSortButtonDown" ng-show="col.showSortButtonDown()"></div>'+
            '<div class="ngSortButtonUp" ng-show="col.showSortButtonUp()"></div>'+
            '<div class="ngSortPriority">{{col.sortPriority}}</div>'+
          '</div>'+
          '<div ng-show="col.resizable" class="ngHeaderGrip" ng-click="col.gripClick($event)" ng-mousedown="col.gripOnMouseDown($event)"></div>';
			
			var nameHeaderCellTemplate = '<div class="ngHeaderSortColumn ngHeaderSortColumnName {{col.headerClass}}" ng-style="{cursor: col.cursor}" ng-class="{ ngSorted: !noSortVisible }">'+
            '<div ng-click="col.sort($event)" ng-class="\'colt\' + col.index" class="ngHeaderText">{{col.displayName}}</div>'+
            '<div class="ngSortButtonDown" ng-show="col.showSortButtonDown()"></div>'+
            '<div class="ngSortButtonUp" ng-show="col.showSortButtonUp()"></div>'+
            '<div class="ngSortPriority">{{col.sortPriority}}</div>'+
          '</div>'+
          '<div ng-show="col.resizable" class="ngHeaderGrip" ng-click="col.gripClick($event)" ng-mousedown="col.gripOnMouseDown($event)"></div>';
			
			var locationRowTemplate = '<input hidden name="locationIds" value=\'{{row.entity["Location Id"]}}\'></input>' +
			'<div>{{row.entity.Location}}</div>';
			
			var rowTemplate = '<div ng-style="{\'cursor\': row.cursor, \'z-index\': col.zIndex() }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell {{col.cellClass}}" ng-cell></div>';
			
			$scope.gridOptions = {
					data : [],
					rowHeight: 45,
					columnDefs : [
							{
								name : 'Location',
								width : 125,
								
								cellClass: 'location-row',
								headerCellTemplate: locationHeaderCellTemplate,
								cellTemplate: locationRowTemplate,
								pinnedLeft : true
							},
							{
								name : 'Admin Name',
								headerCellTemplate: nameHeaderCellTemplate,
								width : 125,
								cellClass: 'name-row'
								//cellTemplate:'<div class="name-row"> {{row.entity["Admin Name"]}} </div>'
							},
							{
								name : 'Admin Email',
								headerCellTemplate: nameHeaderCellTemplate,
								width : 125,
								cellClass: 'name-row'
								//cellTemplate:'<div class="name-row"> {{row.entity["Admin Email"]}} </div>'
							},
							{
								name : 'No. of Employees',
								headerCellTemplate: nameHeaderCellTemplate,
								width : 125,
								cellClass: 'name-row',
								cellTemplate:'<div> {{row.entity["No of Employees"]}} </div>'
							},
							{
								name : 'Recommended Budget',
								headerCellTemplate: nameHeaderCellTemplate,
								width : 135,
								cellFilter: 'currency',
								cellClass: 'name-row'
								//cellTemplate:'<div class="name-row"> {{row.entity["Recommended Budget"]}} </div>'
								
							},
							{
								name : 'Allotted Budget',
								headerCellTemplate: nameHeaderCellTemplate,
								width : 100,
								cellClass: 'name-row',
								cellFilter: 'currency',
								cellTemplate : '<span style="float:left">$</span> <input class="allotted-budget-input" type="text" class="super-admin-input" name="budgetAllotted" value=\'{{row.entity["Allotted Budget"]}}\'/>'
							} ]
				};

				$http.get(
						"/SiriusOrderClient/SuperAdminServlet")
						.then(function(response) {
							$scope.gridOptions.data = response.data;
							console.log($scope.gridOptions.data);
							resizeAppendGrid($scope);
						});
				
				
		} ]);

$(document).ready(function(){
	var officeData = [];
	
	$.getJSON( "/SiriusOrderClient/SuperAdminServlet?action=officeAdmin", function( data ){
		officeData = data;
		console.log(officeData);
		$.typeahead({
		    input: '#typeaheadAdminInput',
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

function resizeAppendGrid($scope){
	var mq = window.matchMedia("(max-width: 500px)");
	console.log("removing columns");
    if(mq.matches){
    	console.log("on mobile");
    	$scope.gridOptions.columnDefs[0].width = "10%";
    	$scope.gridOptions.columnDefs[1].visible = false;
    	$scope.gridOptions.columnDefs[2].visible = false;
    	$scope.gridOptions.columnDefs[3].visible = false;
    	$scope.gridOptions.columnDefs[4].width = "33%";
    	$scope.gridOptions.columnDefs[5].width = "33%";
    }
}

function locationSuccessPopup(){
	$('#locationSuccess').fadeIn('fast').delay(1000).fadeOut('slow');
}

function locationFailurePopup(){
	$('#locationFailure').fadeIn('fast').delay(1000).fadeOut('slow');
}

function assignSuccessPopup(){
	$('#assignSuccess').fadeIn('fast').delay(1000).fadeOut('slow');
}

function assignFailurePopup(){
	$('#assignFailure').fadeIn('fast').delay(1000).fadeOut('slow');
}

function budgetSuccessPopup(){
	$('#budgetSuccess').fadeIn('fast').delay(1000).fadeOut('slow');
}

function budgetFailurePopup(){
	$('#budgetFailure').fadeIn('fast').delay(1000).fadeOut('slow');
}

