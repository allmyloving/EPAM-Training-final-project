$(document).ready(function() {
	console.log("ready!!");
	// $('#errorDiv').hide();
	// $.ajax({
	// url : "serv?param=errors",
	// type : "get",
	// async : true,
	// success : function(data) {
	// console.log('errors ==> ' + data);
	// if (data.length > 4) {
	// $('#errorDiv').html(data);
	// $('#errorDiv').show();
	// }
	// }
	// });

	// init elements
	$('#date').datepicker({
		startDate : '0'
	// ,todayBtn: "linked"
	});
	$('[data-toggle="popover"]').popover({
		html: true,
		content: function(){
			var d = $.ajax({
				url : "serv?param=stations&filter=Ха",
				type : "get",
				async : false,
				success : function(data) {
					return data;
				}
			});
			console.log(d);
			return d;
		}
	});

	$('#signUpForm').submit(function(event) {
		event.preventDefault();
		var map = {
			"email" : /\w+@\w+\.\w+/,
			"password" : /\w{4,50}/

		};
		var errors = {
			"email" : "Email is not valid",
			"password" : "Password should contain more than 4 symbols",
			"repPassword" : "Passwords should match"
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
				div.addClass('has-error');
				$err.html(errors[key]);
			} else {
				div.removeClass('has-error');
				$err.html('');
			}
		}
		var key = 'repPassword';
		var $sel = $('#' + key);
		$err = $('#' + key + 'Error');
		if ($sel.val() != $('#password').val()) {
			$sel.parent().addClass('has-error');
			$err.html(errors[key]);
			proceed = false;
		}
		// proceed = true;
		// console.log(proceed);
		if (proceed) {
			$(this).off("submit");
			this.submit();
		}
	});
});

function getStations(input) {
	$.ajax({
		url : "serv?param=stations&filter=" + $(input).val(),
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

