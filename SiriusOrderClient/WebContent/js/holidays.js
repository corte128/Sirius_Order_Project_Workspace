$(function () 
{
    // Initialize appendGrid
    $('#tblAppendGrid').appendGrid(
    {
        initRows: 1,
        columns: [
            { 
            	name: 'FederalHoliday', 
            	display: 'Federal Holiday', 
            	type: 'text', 
            	ctrlAttr: { maxlength: 100, placeholder: "Enter holiday name..." }, 
            	ctrlCss: { width: '100%'} 
            },
            { 
            	name: 'Date', 
            	display: 'Date', 
            	type: 'ui-datepicker', 
            	ctrlAttr: { maxlength: 50 }, 
            	ctrlCss: { width: '100%'},
            	onChange: function (evt, rowIndex) {
            		$("#tblAppendGrid_Date_"+(rowIndex+1)+".hasDatepicker").datepicker( "option", "dateFormat", "MM d, yy" );
                }
            },
            { 
            	name: 'DayofWeek', 
            	display: 'Day of Week', 
            	type: 'text', 
            	ctrlAttr: { maxlength: 50, placeholder: "Enter day of week..." }, 
            	ctrlCss: { width: '100%'} 
            },
            { 
            	name: 'ID', 
            	type: 'hidden', 
            	value: 0 
            }
        ],
        customRowButtons: [
            { 
            	uiButton: { label: 'Save', showLabel: false },
            	click: saveEntry, 
            	btnAttr: { title: 'Save Entry' }, 
            	btnClass: 'save_button',
            	atTheFront: true 
            },
            { 
            	uiButton: { label: 'Delete', showLabel: false }, 
            	click: deleteEntry, 
            	btnAttr: { title: 'Delete Entry' }, 
            	btnClass: 'delete_button'
            }
        ],
        customGridButtons: {
        	append: $('<button class="project-button"></button>').text('Add').get(0)
        },
        hideButtons: {
        	removeLast: true,
        	moveUp: true,
        	moveDown: true,
        	insert: true,
        	remove: true
        }
    });
    getTableData();
    $( ".hasDatepicker" ).datepicker( "option", "dateFormat", "MM d, yy" );
    resizeAppendGrid();
});

function saveEntry(evtObj, uniqueIndex, rowData){
	console.log(rowData.Date);
	if(rowData.ID > 0){
		$('#saveFailure').fadeIn('fast').delay(1000).fadeOut('slow');
//		$("<div id='save_dialog' title='Save Failed'>Cannot save. Holiday is already in database.</div>").dialog({
//			resizable: false,
//			modal: true,
//			buttons: {
//				Close: function(){
//					$(this).dialog("close");
//				}
//			}
//		});
	}
	else {
		//ajax call to delete holiday
		var xhttp = new XMLHttpRequest();
		var url = "/SiriusOrderClient/QueryServlet?query=addHoliday&name="+rowData.FederalHoliday+"&date="+rowData.Date;
		xhttp.open("GET", url, false);
		xhttp.onreadystatechange = function()
		{
			var response = xhttp.responseText;
			console.log("Response: "+response);
			if(response != null){
				if(response == "true"){
					console.log("Save Success");
					$('#saveSuccess').fadeIn('fast').delay(1000).fadeOut('slow');
//					$("<div id='save_dialog' title='Save Successful'>The holiday was successfully saved!</div>").dialog({
//						resizable: false,
//						modal: true,
//						buttons: {
//							Close: function(){
//								$(this).dialog("close");
//							}
//						}
//					});
				}
				else{
					console.log("Save Unsuccessful");
				}
			}
			else{
				console.log("Results are empty");
			}
		};
		xhttp.send();
	}
}

function deleteEntry(evtObj, uniqueIndex, rowData){
	console.log(evtObj+" "+uniqueIndex+" "+rowData);
	if(rowData.ID > 0){
		$('#tblAppendGrid').appendGrid('removeRow', uniqueIndex-1);
		//ajax call to delete holiday
		var xhttp = new XMLHttpRequest();
		var url = "/SiriusOrderClient/QueryServlet?query=deleteHoliday&id="+rowData.ID;
		xhttp.open("GET", url, false);
		xhttp.onreadystatechange = function()
		{
			var response = xhttp.responseText;
			if(response != null){
				console.log(response);
				if(response == "true"){
					$('#deleteSuccess').fadeIn('fast').delay(1000).fadeOut('slow');
//					$("<div id='delete_dialog' title='Delete Successful'>The holiday was successfully deleted!</div>").dialog({
//						resizable: false,
//						modal: true,
//						buttons: {
//							Close: function(){
//								$(this).dialog("close");
//							}
//						}
//					});
				}
			}
			else{
				console.log("Results are empty");
			}
		};
		xhttp.send();
	}
	else {
		$('#tblAppendGrid').appendGrid('removeRow', uniqueIndex-1);
	}
}
    
function getTableData ()
{
	var xhttp = new XMLHttpRequest();
	var url = "/SiriusOrderClient/QueryServlet?query=getHolidays";
	xhttp.open("GET", url, true);
	xhttp.onreadystatechange = function()
	{
		var response = xhttp.responseText;
		if(response != null){
			console.log(response);
			$('#tblAppendGrid').appendGrid('load', JSON.parse(response));
		}
		else{
			console.log("Results are empty");
		}
	};
	xhttp.send();
}

function resizeAppendGrid(){
	var mq = window.matchMedia("(min-width: 500px)");
    if(mq.matches){
    	$("#tblAppendGrid").appendGrid('showColumn', 'DayofWeek');
    }
    else{
    	$("#tblAppendGrid").appendGrid('hideColumn', 'DayofWeek');
    }
}