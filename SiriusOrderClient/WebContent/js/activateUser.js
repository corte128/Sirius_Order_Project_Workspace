function approve(btn){
	$('#pressed').val("approved");
	$('#id').val(btn.value);
	alert("Employee Approved!");
};
function reject(btn){
	$('#pressed').val("rejected");
	$('#id').val(btn.value);
	alert("Employee Rejected!");
};