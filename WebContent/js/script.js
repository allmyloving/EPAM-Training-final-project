var errors = {
	'en' : {
		"email" : "Email is not valid",
		"password" : "Password should contain at least 4 symbols",
		"repPassword" : "Passwords should match",
		"notEmpty" : "Cannot be empty"
	},
	'ru' : {
		"email" : "Email не валидный",
		"password" : "Пароль должен содержать не менее 4 символов",
		"repPassword" : "Пароли должны совпадать",
		"notEmpty" : "Заполните поле"
	}
}

$(document).ready(function() {
	console.log("ready!!");
	var lang = $('body').attr('lang');

	// index.jsp
	$('#swapStations').click(function(event) {
		var text1 = $('#stationFrom').val();
		var text2 = $('#stationTo').val();
		$('#stationFrom').val(text2);
		$('#stationTo').val(text1);
	});

	// displayFreeSeats
	$('.seat-container').css('display', 'none');
	$('.carriage-item').click(function() {
		var id = $(this).attr('id');
		console.log("carriage ==> " + id);
		$('#carriageId').val(id);
		$('div[id=' + id + ']').slideToggle('slow');
	});
	$('.seat').click(function() {
		// $(this).blur();
		var idVal = $(this).html().trim();
		console.log('seat num==> ' + idVal);
		$('#seatNum').val(idVal);
		// $(this).toggleClass('active');
	});

	$('#signUpForm').submit(function(event) {
		event.preventDefault();
		var map = {
			"email" : /\w+@\w+\.\w+/,
			"password" : /\w{4,50}/

		};

		var proceed = true;
		var $sel, $err, div;
		for (key in map) {
			$sel = $('#' + key);
			div = $sel.parent();
			var regex = new RegExp(map[key]);
			$err = $('#' + key + "Error");

			if (!regex.test($sel.val())) {
				proceed = false;
				$err.html(errors[lang][key]);
			} else {
				$err.html('');
			}
		}
		var key = 'repPassword';
		var $sel = $('#' + key);
		$err = $('#' + key + 'Error');
		if ($sel.val() != $('#password').val()) {
			$err.html(errors[lang][key]);
			proceed = false;
		}

		if (proceed) {
			$(this).off("submit");
			this.submit();
		}
	});

	$('.input-not-empty').submit(function(event) {
		event.preventDefault();
		var proceed = true;
		var items = $(this).find('.form-control');

		for (var i = 0; i < items.length; i++) {
			var id = $(items[i]).attr('id');
			if ($(items[i]).val() == '') {
				$('#' + id + 'Error').html(errors[lang]['notEmpty']);
				proceed = false;
			} else {
				$('#' + id + 'Error').html('');
			}
		}
		if (proceed) {
			$(this).off("submit");
			this.submit();
		}
	});

});

function toggleButton(button) {
	console.log($(button).attr('id'));
	$(button).toggleClass('focus');
};

function getStations(input) {
	$.ajax({
		url : "serv?command=getStations&filter=" + $(input).val(),
		type : "get",
		async : true,
		success : function(data) {
			var stations = JSON.parse(data).stations;

			var names = new Array();
			for (var i = 0; i < stations.length; i++) {
				names.push(stations[i].name);
			}

			// console.log(names);
			$(input).autocomplete({
				source : names
			});
		}
	});
};

function addStationSelect(event) {
	event.preventDefault();
	var html = $('#selectStation').html();
	$('#stationContainer').append(html);
};

function addRoute() {
	$.ajax({
		url : "serv?command=addRoute&trainId=" + $('#trainSelect').val() + "&date=" + $('#date').val(),
		type : "post",
		async : true,
		success : function(data) {
			console.log('success!!!');
		}
	});
}
