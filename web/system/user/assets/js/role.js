$(function () {
	var roleTable = $('#roleTable'),
			roleAddBttn = $('#roleAddBttn'),
			roleUpdateModal = $('#roleUpdateModal'),
			roleAddModal = $('#roleAddModal');

	roleTable.DataTable({
		ajax: {
			url: $g.root_path + 'role.getAllWithModules',
			dataSrc: 'data'
		},
		columns: [
			{data: 'name'},
			{
				data: null,
				render: function(data) {
					var modules = "";
					data.modules.forEach(function (module) {
						modules += module.name + ", ";
					});
					
					if (modules === "") {
						return "None";
					}
					
					return modules.replace(/,\s*$/, "");
				}
			}
		],
		createdRow: function (row, data) {
			$(row).addClass('roleRow clicky')
					.data('role.row.data', data);
		}
	});

	roleAddBttn.on('click', function () {
		roleAddModal.modal('show');
	});
	
	roleTable.on('click', '.roleRow', function() {
		var data = $(this).data('role.row.data');
		
		roleUpdateModal.fillForm({
			source: data,
			custom: function (form, fd) {
				var modules = [];
				
				fd.modules.forEach(function (module) {
					modules.push(module.id);
				});
				
				form.find('#moduleSelection input').each(function () {
					var elem = $(this);
					var val = elem.val();
					if (modules.includes(Number(val))) {
						elem.prop('checked', true);
					}else {
						elem.prop('checked', false);
					}
				});
			}
		});
		
		roleUpdateModal.modal('show');
	});

	roleAddModal.standardDialog({
		title: 'Add Role',
		ajax: function (fd, form) {
//			console.log('fd', fd);
			var selectedModules = [];
			form.find('#moduleSelection .moduleSwitch').each(function () {
				if ($(this).is(':checked')) {
					selectedModules.push($(this).val());
				}
			});
			fd['modules'] = selectedModules;
			return $.post($g.root_path + 'role.create', fd);
		},
		done: function (result, modal) {
			roleTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		}
	});
	
	roleUpdateModal.standardDialog({
		title: 'Update Role',
		ajax: function (fd, form) {
			var selectedModules = [];
			form.find('#moduleSelection .moduleSwitch').each(function () {
				if ($(this).is(':checked')) {
					selectedModules.push($(this).val());
				}
			});
			fd['modules'] = selectedModules;
			return $.post($g.root_path + 'role.update', fd);
		},
		done: function (result, modal) {
			roleTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		}
	});
});