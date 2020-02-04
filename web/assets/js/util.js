(function ($) {

	$.fn.serializeForm = function (opts) {
		var settings = $.extend({
			custom: null
		}, opts);

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

		if ($.isFunction(settings.custom)) {
			settings.custom(fd, $this);
		}

		return fd;
	};

}(jQuery));

(function ($) {
	$.fn.fillForm = function (args) {
		return this.each(function () {

			var _args = $.extend({
				source: null,
				custom: null,
				readonly: false,
				filter: null
			}, args);

			var $this;

			if ($(this).is('form')) {
				$this = $(this);
			} else {
				$this = $(this).find('form');
			}

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
		});
	};

	function isPrimitive(input) {
		if (typeof input === 'number' ||
				typeof input === 'boolean' ||
				typeof input === 'string') {

			return true;
		}

		return false;
	}
}(jQuery));

(function ($) {
	$.fn.createOption = function (val, text, selected, disabled) {
		return this.each(function () {
			$('<option>', {
				text: text,
				value: val,
				selected: selected ? true : false,
				disabled: disabled ? true : false
			}).appendTo(this);
		});
	};
}(jQuery));

(function ($) {
	$.fn.clearOptions = function (defaultOption) {
		return this.each(function () {
			$(this).empty().createOption('', defaultOption, true, true);
		});
	};
}(jQuery));

String.prototype.commafy = function () {
	return commafy(this);
};

String.prototype.unCommafy = function () {
	return this.replace(/,/g, '');
};

Number.prototype.commafy = function () {
	return commafy(this.toString());
};

function commafy(number) {
	'use strict';
	var n = number.toString().split('.');

	n[0] = n[0].replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');

	return n.join('.');
}

function sysAlert(options) {
	'use strict';

	var body = $('body #content');
	var settings = $.extend({
		text: 'Alert!',
		delay: 1500,
		type: 'light'
	}, options);

	var defAlertClass = 'standard-alert alert alert-dismissible fade show text-center';
	var alertType = 'alert-' + settings.type;

	var alert = $('<div>', {
		class: defAlertClass + ' ' + alertType,
		role: 'alert'
	});

	[
		{
			el: '<strong>',
			attr: {
				text: settings.text
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

	setTimeout(function () {
		alert.alert('close');
	}, settings.delay);
}

function sysConfirm(args) {
	'use strict';

	var _args = $.extend({
		title: 'Confirm',
		text: 'Do you want to confirm this action?',
		onOpen: null,
		close: null,
		ok: null,
		okText: 'Confirm',
		cancelText: 'Close'
	}, args);

	var modal = $('<div>').attr({
		tabIndex: -1,
		role: 'dialog'
	}).addClass('modal fade');

	var dialog = $('<div>', {
		class: 'modal-dialog',
		role: 'document'
	});

	var content = $('<div>', {
		class: 'modal-content'
	});

	var confirmBtn = $('<button>', {
		class: 'btn btn-primary',
		type: 'button',
		text: _args.okText
	}).on('click', function () {
		if ($.isFunction(_args.ok)) {
			_args.ok(modal);
		}
	});

	//The default close bttn of a modal
	var closeBtn = $('<button>', {
		class: 'close',
		type: 'button',
		'data-dismiss': 'modal',
		'aria-label': 'close'
	}).on('click', function () {
		if ($.isFunction(_args.close)) {
			_args.close();
		}
	});

	$('<span>', {
		'aria-hidden': true,
		html: '&times;'
	}).appendTo(closeBtn);

	[
		{
			class: 'modal-header',
			children: [
				{
					el: '<h5>',
					attr: {
						class: 'modal-title',
						text: _args.title
					}
				}
			]
		},
		{
			class: 'modal-body',
			children: [
				{
					el: '<span>',
					attr: {
						text: _args.text
					}
				}
			]
		},
		{
			class: 'modal-footer',
			children: [
				{
					el: '<button>',
					attr: {
						class: 'btn btn-secondary',
						type: 'button',
						'data-dismiss': 'modal',
						text: _args.cancelText
					}
				}
			]
		}
	].forEach(function (section) {
		var div = $('<div>').addClass(section.class);

		if (section.children) {
			section.children.forEach(function (child) {
				var elem = $(child.el, child.attr);
				if (child.child) {
					$(child.child.el, child.child.attr).appendTo(elem);
				}

				elem.appendTo(div);

				if (section.class === 'modal-header') {
					closeBtn.appendTo(div);
				}

				//This is only for the cancel button of this confirm dialog
				if (section.class === 'modal-footer') {
					elem.on('click', function () {
						if ($.isFunction(_args.cancel)) {
							_args.cancel(modal);
						}
					});
				}
			});
		}
		if (section.class === 'modal-footer') {
			confirmBtn.appendTo(div);
		}

		div.appendTo(content);
	});

	content.appendTo(dialog);
	dialog.appendTo(modal);
	modal.modal('show');
}

function processWrapper(options) {
	'use strict';
	
	var settings = $.extend({
		ajax: null,
		done: null
	}, options);

	var modal = $('<div>').attr({
		tabIndex: -1,
		role: 'dialog',
		'data-backdrop': 'static'
	}).addClass('modal');

	var dialog = $('<div>', {
		class: 'modal-dialog',
		role: 'document'
	});

	var content = $('<div>', {
		class: 'modal-content'
	});

	var modalBody = $('<div>', {
		class: 'modal-body'
	}).appendTo(content);

	var progressContainer = $('<div>', {
		class: 'progress',
		height: '50px'
	}).appendTo(modalBody);

	$('<div>', {
		text: 'Saving...',
		class: 'progress-bar progress-bar-striped progress-bar-animated w-100 fs-150 noselect',
		role: 'progressbar',
		'aria-valuenow': '75',
		'aria-valuemin': '0',
		'aria-valuemax': '100'
	}).appendTo(progressContainer);


	content.appendTo(dialog);
	dialog.appendTo(modal);
	modal.modal('show');

	var ajax = {};
	if ($.isFunction(settings.ajax)) {
		ajax = settings.ajax();
	}
	
	$.when(ajax).done(function (result) {
		if ($.isFunction(settings.done)) {
			settings.done(result, modal);
		}
	});
}