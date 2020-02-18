(function ($) {
    $.fn.standardDialog = function (args) {
        var _args = $.extend({
            title: 'Dialog',
            onOpen: null,
            onClose: null,
            ajax: null,
            done: null,
            formReset: true,
            scrollable: false,
            small: false,
            large: false,
            xlarge: false,
            hideButtons: false
        }, args);

        var $this = $(this).attr({
            tabIndex: -1,
            role: 'dialog'
        }).addClass('modal fade');

        var form = $this.find('form').first();
        var bodyContent = $this.children();

        var dialog = $('<div>', {
            class: 'modal-dialog',
            role: 'document'
        });

        if (_args.small) {
            dialog.addClass('modal-sm');
        } else if (_args.large) {
            dialog.addClass('modal-lg');
        } else if (_args.xlarge) {
            dialog.addClass('modal-xl');
        }

        if (_args.scrollable) {
            dialog.addClass('modal-dialog-scrollable');
        }

        var content = $('<div>', {
            class: 'modal-content'
        });

        var saveBttn = $('<button>', {
            class: 'btn btn-primary',
            type: 'button',
            text: 'Save'
        }).on('click', function () {
            form.find('.dummy-submit').click();
        });

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
                    },
                    {
                        el: '<button>',
                        attr: {
                            class: 'close',
                            type: 'button',
                            'data-dismiss': 'modal',
                            'aria-label': 'close'
                        },
                        child: {
                            el: '<span>',
                            attr: {
                                'aria-hidden': true,
                                html: '&times;'
                            }
                        }
                    }
                ]
            },
            {
                class: 'modal-body'
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
                            text: 'Close'
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
                });
            }
            if (section.class === 'modal-body') {
                bodyContent.each(function () {
                    $(this).appendTo(div);
                });
            }
            if (section.class === 'modal-footer') {
                saveBttn.appendTo(div);
            }

            div.appendTo(content);
        });

        content.appendTo(dialog);
        dialog.appendTo($this);

        if ($.isFunction(_args.onOpen)) {
            $this.on('show.bs.modal', function () {
                _args.onOpen();
            });
        }

        if (_args.formReset) {
            $this.on('hidden.bs.modal', function () {
                $(this).find('form').trigger('reset');
            });
        }

        if ($.isFunction(_args.onClose)) {
            $this.on('hidden.bs.modal', function () {
                _args.onClose();
            });
        }

        if (_args.hideButtons) {
            content.find('.modal-footer').hide();
        }

        form.on('submit', function (e) {
            e.preventDefault();
            var fd = $(this).serializeForm();
            var ajaxRequest = {};

            if ($.isFunction(_args.ajax)) {
                ajaxRequest = _args.ajax(fd, form);
            }
			
			$this.modal('hide');
			processWrapper({
				ajax: function () {
					return ajaxRequest;
				},
				done: function (result, modal) {
                    _args.done(result, modal);
				}
			});
        });

        return $this;
    };

}(jQuery));