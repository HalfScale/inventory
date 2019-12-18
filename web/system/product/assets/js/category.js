$(function () {

	var categoryTable = $('#categoryTable'),
			categoryAddBtn = $('#categoryAddBttn');

	var selectedCategory;

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
					.data('category.row.data', data);
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

	var categoryUpdateModal = $('#categoryUpdateModal').standardDialog({
		title: 'Update Category',
		onOpen: function () {
//			console.log('open', $(this));
			if (selectedCategory) {
				categoryUpdateModal.find('form').fillForm({
					source: selectedCategory,
					filter: function (key, val, elem) {
						if (key === 'status') {
							elem.val(val ? 1 : 0);
						}
					}
				});
			}
		},
		ajax: function (fd) {
			return $.post($g.root_path + 'category.update', fd);
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

	categoryTable.on('click', '.categoryEditBtn', function () {
		selectedCategory = $(this).parents('tr').data('category.row.data');
		categoryUpdateModal.modal('show');
	});

	categoryTable.on('click', '.categoryDeleteBtn', function () {
		selectedCategory = $(this).parents('tr').data('category.row.data');
		sysConfirm({
			title: 'Delete Category',
			text: 'Are you sure you want to delete this entry?',
			ok: function (modal) {
				$.post($g.root_path + 'category.delete', selectedCategory).done(function (result) {
					categoryTable.DataTable().ajax.reload();
					modal.modal('hide');
					sysAlert({
						text: result.response,
						delay: 2000
					});
				});
			}
		});
	});
});