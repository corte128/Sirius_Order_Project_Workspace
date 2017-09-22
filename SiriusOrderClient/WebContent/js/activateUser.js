function approve(btn){
	console.log("approving...");
	$('#pressed').val("approved");
	$('#id').val(btn.value);
	alert("Employee Approved!");
};
function reject(btn){
	console.log("rejecting...");
	$('#pressed').val("rejected");
	$('#id').val(btn.value);
	alert("Employee Rejected!");
};