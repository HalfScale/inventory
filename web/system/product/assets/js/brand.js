$(function () {
	var brandTable = $('#brandTable'),
			brandAddBtn = $('#brandAddBttn');

	var selectedBrand = null;

	brandAddBtn.on('click', function () {
		brandAddModal.modal('show');
	});

	var brandAddModal = $('#brandAddModal').standardDialog({
		title: 'Add Category',
		ajax: function (fd) {
			return $.post($g.root_path + 'brand.create', fd);
		},
		done: function (result, modal) {
			brandTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		}
	});

	var brandUpdateModal = $('#brandUpdateModal').standardDialog({
		title: 'Update Brand',
		onOpen: function () {
			if (selectedBrand) {
				brandUpdateModal.find('form').fillForm({
					source: selectedBrand,
					filter: function (key, val, elem) {
						if (key === 'status') {
							elem.val(val ? 1 : 0);
						}
					}
				});
			}
		},
		ajax: function (fd) {
			return $.post($g.root_path + 'brand.update', fd);
		},
		done: function (result, modal) {
			brandTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		}
	});

	brandTable.on('click', '.brandEditBtn', function () {
		selectedBrand = $(this).parents('tr').data('brand.row.data');
		brandUpdateModal.modal('show');
	});

	brandTable.on('click', '.brandDeleteBtn', function () {
		selectedBrand = $(this).parents('tr').data('brand.row.data');
		sysConfirm({
			title: 'Delete Brand',
			text: 'Are you sure you want to delete this entry?',
			ok: function (modal) {
				$.post($g.root_path + 'brand.delete', selectedBrand).done(function (result) {
					brandTable.DataTable().ajax.reload();
					modal.modal('hide');
					sysAlert({
						text: result.response,
						delay: 2000
					});
				});
			}
		});
	});

	brandTable.DataTable({
		ajax: {
			url: $g.root_path + 'brand.getAll',
			dataSrc: 'data'
		},
		columns: [
			{data: 'name'},
			{
				data: null,
				width: '7em',
				className: 'text-center',
				render: function (data) {
					if (data.status) {
						return $('<span>', {
							class: 'badge badge-success',
							text: 'Active'
						}).prop('outerHTML');
					}

					return $('<span>', {
						class: 'badge badge-danger',
						text: 'Inactive'
					}).prop('outerHTML');
				}
			},
			{
				data: null,
				width: '10em',
				className: 'text-center',
				render: function () {
					var editBtn = $('<button>', {
						type: 'button',
						class: 'brandEditBtn btn btn-outline-warning btn-sm',
						text: 'Edit'
					}).prop('outerHTML');

					var deleteBtn = $('<button>', {
						type: 'button',
						class: 'brandDeleteBtn btn btn-outline-danger btn-sm',
						text: 'Delete'
					}).prop('outerHTML');

					return editBtn + deleteBtn;
				}
			}
		],
		createdRow: function (row, data) {
			$(row).addClass('brandRow')
					.data('brand.row.data', data);
		}
	});
});