$(function() {
    var formSignin = $('.form-signin');
    
    formSignin.on('submit', function(e) {
        e.preventDefault();
        var fd = $(this).serializeForm();
        
        var submitButton = $('.btn-primary');
        
        $.post('WebLogin', fd).done(function (result) {
            console.log('WebLogin result', result);
            if (result.status === 0) {
                submitButton.text('Logging in...')
                setTimeout(function () {
                    window.location.replace('system/');
                }, 1000);
            }
        });
    });
    
});