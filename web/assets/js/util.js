(function ($) {
    
    $.fn.serializeForm = function () {
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

(function ($) {
	$.fn.fillForm = function (args) {
		var _args = $.extend({
			source: null,
			custom: null,
			readonly: false,
			filter: null
		}, args);
		
		var $this = $(this);
		if (_args.source != null) {
			Object.keys(_args.source).forEach(function (key) {
				if (isPrimitive(_args.source[key])) {
					var elem = $this.find('.' + key)
							.val(_args.source[key])
							.prop('readonly', _args.readonly);
					
					if ($.isFunction(_args.filter)) {
						_args.filter(key, _args.source[key], elem);
					}
				}
			});

			if ($.isFunction(_args.custom)) {
				_args.custom($this, _args.source);
			}
		}

		function isPrimitive(input) {
			if (typeof input === 'number' ||
					typeof input === 'boolean' ||
					typeof input === 'string') {

				return true;
			}

			return false;
		}
	};
}(jQuery));