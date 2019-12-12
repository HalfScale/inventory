(function ($) {

	$.fn.serializeForm = function () {
		var $this;
		var fd = {};

		if ($(this).is('form')) {
			$this = $(this);
		} else {
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

function sysAlert(args) {
	'use strict';
	
	var body = $('body #content');
	var _args = $.extend({
		delay: 1500
	}, args);
	
	var alert = $('<div>', {
		class: 'system-alert alert alert-light alert-dismissible fade show',
		role: 'alert'
	});
	
	[
		{
			el: '<strong>',
			attr: {
				text: 'Some alert message!'
			}
		},
		{
			el: '<button>',
			attr: {
				type: 'button',
				class: 'close',
				'data-dismiss': 'alert',
				'aria-label': 'close'
			},
			children: [
				{
					el: '<span>',
					attr: {
						html: '&times;',
						'aria-hidden': true
					}
				}
			]
		}
	].forEach(function (content) {
		var elem = $(content.el, content.attr);
		
		if (content.children) {
			content.children.forEach(function (child) {
				$(child.el, child.attr).appendTo(elem);
			});
		}
		
		elem.appendTo(alert);
	});
	
	alert.appendTo(body);
	
	setTimeout(function() {
		alert.alert('close');
	}, _args.delay);
}

function testWrapper() {
	'use strict';
	var modal = $('<div>', {
		tabIndex: -1,
		role: 'dialog',
		class: 'modal fade'
	});

	var dialog = $('<div>', {
		class: 'modal-dialog',
		role: 'document'
	});

	var content = $('<div>', {
		class: 'modal-content'
	});

	[
		{
			attr: {
				class: 'modal-header',
				role: 'document'
			},
			children: [
				{
					el: '<h5>',
					attr: {
						class: 'modal-title',
						text: 'Modal Title'
					}
				},
				{
					el: '<button>',
					attr: {
						class: 'close',
						type: 'button',
						'data-dismiss': 'modal',
						'aria-label': 'close'
					},
					children: [
						{
							el: '<span>',
							attr: {
								'aria-hidden': true,
								html: '&times;'
							}
						}
					]
				}
			]
		},
		{
			attr: {
				class: 'modal-body'
			}
		},
		{
			attr: {
				class: 'modal-footer'
			},
			children: [
				{
					el: '<button>',
					attr: {
						type: 'button',
						class: 'btn btn-secondary',
						'data-dismiss': 'modal',
						text: 'Close'
					}
				},
				{
					el: '<button>',
					attr: {
						type: 'button',
						class: 'btn btn-primary',
						'data-dismiss': 'modal',
						text: 'Confirm'
					}
				}
			]
		}
	].forEach(function (section) {
		var div = $('<div>', section.attr);


		if (section.children) {
			section.children.forEach(function (child) {
				var elem = $(child.el, child.attr);

				if (child.children) {
					child.children.forEach(function (cc) {
						$(cc.el, cc.attr).appendTo(elem);
					});
				}

				elem.appendTo(div);
			});
		}

		div.appendTo(content);
	});

	content.appendTo(dialog);
	dialog.appendTo(modal);
	modal.modal('show');
}