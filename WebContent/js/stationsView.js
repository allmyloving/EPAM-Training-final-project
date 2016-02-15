var errors = {
	'ru' : {
		'message.name_not_valid' : 'Имя не может быть пустым и должно начинаться с заглавной буквы',
		'message.try_again' : "Попробуйте позже"
	},
	'en' : {
		'message.name_not_valid' : "Name should start with upper-case letter",
		'message.try_again' : "Try again later"
	}
}

$(document).ready(function() {
	var lang = $('body').attr('lang');

	// $('#addNewStation').editable({
	// success : function(response, newValue) {
	// console.log(response);
	// var href = window.location.href;
	// var index = href.indexOf("controller");
	// var newLoc = href.slice(0, index) + response.responseText;
	// console.log('redirecting to ' + newLoc);
	// window.location.href = newLoc;
	// }
	// });
	$('.station-popup').editable({
		error : function(response, newValue) {
			return errors[lang][response.responseText];
		}
	});
	$('#addStationDiv').hide();
	$('#addNewStation').click(function(event) {
		console.log('clicked');
		event.preventDefault();
		$('#addStationDiv').toggle();

	})
});