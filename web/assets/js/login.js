$(function() {
    var formSignin = $('.form-signin');
	var statusBox = $('#statusBox');
    var submitBtn = $('button');
	
    formSignin.on('submit', function(e) {
        e.preventDefault();
        var fd = $(this).serializeForm();
        
		submitBtn.empty();
		createLoadingIcon().appendTo(submitBtn);
        $.post('WebLogin', fd).done(function (result) {
            console.log('WebLogin result', result);
            if (result.status === 0) {
                statusBox.addClass('alert alert-success').text();
				fillStatusBox('Success! Signing you in...', true);
                setTimeout(function () {
                    window.location.replace('system/');
                }, 1000);
            }else {
				fillStatusBox(result.response, false);
				submitBtn.empty().text('Sigin in');
			}
			
        });
    });
	
	function fillStatusBox(text, isSuccess) {
		statusBox.removeClass().addClass('alert text-center');
		if (isSuccess) {
			statusBox.addClass('alert-success').text(text);
		}else {
			statusBox.addClass('alert-danger').text(text);
		}
	}
	
	function createLoadingIcon() {
		var container = $('<div>', {
			class: 'spinner-border',
			role: 'status'
		});
		$('<span>').addClass('sr-only').text('Loading...').appendTo(container);
		return container;
	}
    
});