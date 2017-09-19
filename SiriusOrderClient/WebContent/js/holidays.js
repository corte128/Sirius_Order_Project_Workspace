$(function () 
{
    // Initialize appendGrid
    $('#tblAppendGrid').appendGrid(
    {
        caption: 'My CD Collections',
        initRows: 1,
        columns: [
            { name: 'FederalHoliday', display: 'Federal Holiday', type: 'text', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '160px'} },
            { name: 'Date', display: 'Date', type: 'date', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '130px'} },
            { name: 'DayofWeek', display: 'Day of Week', type: 'text', ctrlAttr: { maxlength: 10 }, ctrlCss: { width: '100px'} },
            { name: 'ID', type: 'hidden', value: 0 }
        ]
    });
    getTableData();
});
    
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