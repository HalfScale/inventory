$(function () {

	var categoryTable = $('#categoryTable'),
			categoryAddBtn = $('#categoryAddBttn');

	categoryAddBtn.on('click', function () {
		categoryAddModal.modal('show');
	});

	categoryTable.DataTable({
		ajax: {
			url: $g.root_path + 'category.getAll',
			dataSrc: 'data'
		},
		columns: [
			{data: 'id'},
			{data: 'name'},
			{data: 'status'},
			{
				data: null,
				render: function () {
					return '<button type="button" class="categoryEditBtn btn btn-outline-warning btn-sm">' + 'Edit' + '</button>' +
							'<button type="button" class="categoryDeleteBtn btn btn-outline-danger btn-sm">' + 'Delete' + '</button>';
				}
			}
		],
		createdRow: function (row, data) {
			$(row).addClass('brandRow')
					.data('brand.row.data', data);
		}
	});

	var categoryAddModal = $('#categoryAddModal').standardDialog({
		title: 'Add Category',
		ajax: function (fd) {
			return $.post($g.root_path + 'category.create', fd);
		},
		done: function (result, modal) {
			categoryTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		}
	});
});