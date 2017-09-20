function approve(btn){
	console.log("approving...");
	$('#pressed').val("approved");
	$('#id').val(btn.value);
	console.log(btn.value);
};
function reject(btn){
	console.log("rejecting...");
	$('#pressed').val("rejected");
	$('#id').val(btn.value);
	console.log(btn.value);
};