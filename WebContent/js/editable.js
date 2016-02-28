var errors = {
	'en' : {
		"notEmpty" : "Cannot be empty",
		"chooseSeat" : "Choose a seat, please",
		'message.name_not_valid' : "Name is not valid",
		'message.try_again' : "Try again later"
	},
	'ru' : {
		"notEmpty" : "Заполните поле",
		"chooseSeat" : "Пожалуйста, выберите место",
		'message.name_not_valid' : 'Имя некорректно',
		'message.try_again' : "Попробуйте позже"
	}
}

$(document).ready(function() {
	var lang = $('body').attr('lang');

	$('.my-popup').editable({
		error : function(response, newValue) {
			return errors[lang][response.responseText];
		}
	});
});