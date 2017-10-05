function approve(btn) {
	addedSuccessPopup();
	$('#pressed').val("approved");
	$('#id').val(btn.value);
	
	setTimeout(function() {
		$("#approvalForm").submit();
	}, 1500);

	// alert("Employee Approved!");
	// $("<div id='approve_dialog' title='Employee Approved'>Employee has been
	// approved and may now log in.</div>").dialog({
	// resizable: false,
	// modal: true,
	// buttons: {
	// Close: function(){
	// $("#approvalForm").submit();
	// $(this).dialog("close");
	// }
	// }
	// });
};
function reject(btn) {
	removeSuccessPopup();
	$('#pressed').val("rejected");
	$('#id').val(btn.value);
	
	setTimeout(function() {
		$("#approvalForm").submit();
	}, 1500);
	
	// alert("Employee Rejected!");
	// $("<div id='reject_dialog' title='Employee Rejected'>Employee has been
	// rejected.</div>").dialog({
	// resizable: false,
	// modal: true,
	// buttons: {
	// Close: function(){
	// $("#approvalForm").submit();
	// $(this).dialog("close");
	// }
	// }
	// });
};

function addedSuccessPopup() {
	$('#confirmSuccess').fadeIn('fast').delay(5000).fadeOut('slow');

}

function removeSuccessPopup() {
	$('#rejectSuccess').fadeIn('fast').delay(5000).fadeOut('slow');
}
