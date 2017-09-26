$(function () 
{
    // Initialize appendGrid
    $('#tblAppendGrid').appendGrid(
    {
        initRows: 1,
        columns: [
            { name: 'FederalHoliday', display: 'Federal Holiday', type: 'text', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '160px'} },
            { name: 'Date', display: 'Date', type: 'date', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '130px'} },
            { name: 'DayofWeek', display: 'Day of Week', type: 'text', ctrlAttr: { maxlength: 10 }, ctrlCss: { width: '100px'} },
            { name: 'ID', type: 'hidden', value: 0 }
        ],
        customRowButtons: [
            { 
            	uiButton: { label: 'Save' },
            	click: saveEntry, 
            	btsCss: { 'width': '30px' }, 
            	btnAttr: { title: 'Save Entry' }, 
            	btnClass: 'save_button',
            	atTheFront: true 
            },
            { 
            	uiButton: { label: 'Delete' }, 
            	click: deleteEntry, 
            	btsCss: { 'width': '30px' }, 
            	btnAttr: { title: 'Delete Entry' }, 
            	btnClass: 'delete_button'
            }
        ],
        customGridButtons: {
        	append: $('<button></button>').text('Add').get(0)
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
});

function saveEntry(evtObj, uniqueIndex, rowData){
	console.log(rowData.ID);
	console.log(rowData.Date);
	if(rowData.ID > 0){
		alert('Already in database');
	}
	else {
		alert('Saved Holiday in position: ' + uniqueIndex);
		//ajax call to delete holiday
		var xhttp = new XMLHttpRequest();
		var url = "/SiriusOrderClient/QueryServlet?query=addHoliday&name="+rowData.FederalHoliday+"&date="+rowData.Date;
		xhttp.open("GET", url, true);
		xhttp.onreadystatechange = function()
		{
			var response = xhttp.responseText;
			if(response != null){
				console.log(response);
				if(response == 1){
					alert("Successfully saved holiday: " + rowData.FederalHoliday);
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
		alert('Deleting Holiday in position: ' + uniqueIndex);
		$('#tblAppendGrid').appendGrid('removeRow', uniqueIndex-1);
		//ajax call to delete holiday
		var xhttp = new XMLHttpRequest();
		var url = "/SiriusOrderClient/QueryServlet?query=deleteHoliday&id="+rowData.ID;
		xhttp.open("GET", url, true);
		xhttp.onreadystatechange = function()
		{
			var response = xhttp.responseText;
			if(response != null){
				console.log(response);
				if(response == 1){
					alert("Successfully deleted holiday: " + rowData.FederalHoliday);
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