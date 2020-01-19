$(function () {
	var roleTable = $('#roleTable'),
			roleAddBttn = $('#roleAddBttn'),
			roleAddModal = $('#roleAddModal');
	$.get($g.root_path + 'role.getAllWithModules').done(function(result) {
		console.log('getAllWithModules', result);
	});

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
						modules += module.name + ",";
					});
					
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

	roleAddModal.standardDialog({
		title: 'Add Role',
		ajax: function (fd, form) {
			console.log('fd', fd);
			var selectedModules = [];
			form.find('#moduleSelection .moduleSwitch').each(function () {
				if ($(this).is(':checked')) {
					selectedModules.push($(this).val());
				}
			});
			fd['modules'] = selectedModules;
			console.log('selectedModules', selectedModules);
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
});