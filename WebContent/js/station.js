$(document).ready(function() {
	$('.station-popup').editable({
		error : function(response, newValue) {
			return errors[lang][response.responseText];
		}
	});
})