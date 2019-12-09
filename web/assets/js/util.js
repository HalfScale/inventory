(function ($) {
    
    $.fn.serializeForm = function() {
        var $this;
        var fd = {};
        
        if ($(this).is('form')) {
            $this = $(this);
        }else {
            $this = $(this).find('form');
        }
        
        $this.serializeArray().forEach(function (obj) {
            fd[obj.name] = obj.value;
        });
        
        return fd;
    };
    
}(jQuery));