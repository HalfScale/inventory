$(function () {
	var brandAddModal = $('#brandAddModal'),
			brandUpdateModal = $('#brandUpdateModal'),
			brandDeleteModal = $('#brandDeleteModal'),
			brandTable = $('#brandTable'),
			brandAddBttn = $('#brandAddBttn'),
			brandAddSave = $('#brandAddSave'),
			brandCloseClose = $('#brandAddClose'),
			brandUpdateSave = $('#brandUpdateSave'),
			brandUpdateClose = $('#brandUpdateClose');

	brandAddBttn.on('click', function () {
		brandAddModal.find('form').trigger('reset');
		brandAddModal.modal('show');
	});

	brandAddSave.on('click', function () {
		brandAddModal.find('form .dummy-submit').click();
	});
	
	brandUpdateSave.on('click', function () {
		brandUpdateModal.find('form .dummy-submit').click();
	});

	brandAddModal.find('form').on('submit', function (e) {
		e.preventDefault();
		var fd = $(this).serializeForm();
//		console.log('fd before submit', fd);
		$.post($g.root_path + 'brand.create', fd).done(function (result) {
//			console.log('result', result);
			brandTable.DataTable().ajax.reload();
			brandAddModal.modal('hide');

		});
	});
	
	brandUpdateModal.find('form').on('submit', function (e) {
		e.preventDefault();
		var fd = $(this).serializeForm();
		
		$.post($g.root_path + 'brand.update', fd).done(function (result) {
//			console.log('result', result);
			brandTable.DataTable().ajax.reload();
			brandAddModal.modal('hide');

		});
	});

	brandTable.DataTable({
		ajax: {
			url: $g.root_path + 'brand.getAll',
			dataSrc: 'data'
		},
		columns: [
			{data: 'id'},
			{data: 'name'},
			{data: 'status'},
			{
				data: null,
				render: function () {
					return '<button type="button" class="brandEditBtn btn btn-outline-warning btn-sm">' + 'Edit' + '</button>' +
							'<button type="button" class="branDeleteBtn btn btn-outline-danger btn-sm">' + 'Delete' + '</button>';
				}
			}
		],
		createdRow: function (row, data) {
			$(row).addClass('brandRow')
					.data('brand.row.data', data);
		}
	});

	brandTable.on('click', '.brandEditBtn', function () {
		var data = $(this).parents('tr').data('brand.row.data');

		brandUpdateModal.find('form').fillForm({
			source: data,
			filter: function (key, value, elem) {
				if (key === 'status') {
					var statusValue = value ? 1 : 0;
					elem.val(statusValue);
				}
			}
		});
		
		brandUpdateModal.trigger('reset');
		brandUpdateModal.modal('show');
	});
	
	brandTable.on('click', '.branDeleteBtn', function () {
		var data = $(this).parents('tr').data('brand.row.data');
		
		brandDeleteModal.modal('show');
	});

});