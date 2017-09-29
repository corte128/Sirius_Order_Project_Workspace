function approve(btn){
	$('#pressed').val("approved");
	$('#id').val(btn.value);
//	alert("Employee Approved!");
	$("<div id='approve_dialog' title='Employee Approved'>Employee has been approved and may now log in.</div>").dialog({
		resizable: false,
		modal: true,
		buttons: {
			Close: function(){
				$("#approvalForm").submit();
				$(this).dialog("close");
			}
		}
	});
};
function reject(btn){
	$('#pressed').val("rejected");
	$('#id').val(btn.value);
//	alert("Employee Rejected!");
	$("<div id='reject_dialog' title='Employee Rejected'>Employee has been rejected.</div>").dialog({
		resizable: false,
		modal: true,
		buttons: {
			Close: function(){
				$("#approvalForm").submit();
				$(this).dialog("close");
			}
		}
	});
};