$(document).ready(function() {
	console.log("ready!!");
	$('#errorDiv').hide();
	$.ajax({
		url : "serv?param=errors",
		type : "get",
		async : true,
		success : function(data) {
			console.log('errors ==> ' + data);
			if (data.length > 4) {
				$('#errorDiv').html(data);
				$('#errorDiv').show();
			}
		}
	});
});