$(function () {

	var productTable = $('#productTable'),
			productAddBtn = $('#productAddBtn');

	var selectedProduct;

	productTable.DataTable({
		ajax: {
			url: $g.root_path + 'product.getAll',
			dataSrc: 'data'
		},
		columns: [
			{data: 'code'},
			{data: 'name'},
			{data: 'price'},
			{data: 'stock'},
			{data: 'status'},			{
				data: null,
				render: function () {
					return '<button type="button" class="productEditBtn btn btn-outline-warning btn-sm">' + 'Edit' + '</button>' +
							'<button type="button" class="productDeleteBtn btn btn-outline-danger btn-sm">' + 'Delete' + '</button>';
				}
			}
		],
		createdRow: function (row, data) {
			$(row).addClass('productRow')
					.data('product.row.data', data);
		}
	});

	var productAddModal = $('#productAddModal').standardDialog({
		title: 'Add Product',
		scrollable: true,
		ajax: function (fd) {
			return $.post($g.root_path + 'product.create', fd);
		},
		done: function (result, modal) {
			productTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		},
		onClose : function() {
			getAllBrands();
			getAllCategories();
		}
	});
	
	var productUpdateModal = $('#productUpdateModal').standardDialog({
		title: 'Update product',
		scrollable: true,
		onOpen: function() {
			if (selectedProduct) {
				productUpdateModal.find('form').fillForm({
					source: selectedProduct,
					filter: function (key, val, elem) {
						if (key === 'status') {
							elem.val(val ? 1 : 0);
						}
					},
					custom: function (form, fd) {
						form.find('.brand').val(fd.brand.id);
						form.find('.category').val(fd.category.id);
					}
				});
			}
		},
		ajax: function (fd) {
			return $.post($g.root_path + 'product.update', fd);
		},
		done: function(result, modal) {
			productTable.DataTable().ajax.reload();
			modal.modal('hide');
			sysAlert({
				text: result.response,
				delay: 2000
			});
		},
		onClose : function() {
			getAllBrands();
			getAllCategories();
		}
	});

	productAddBtn.on('click', function () {
		productAddModal.modal('show');
	});
	
	productTable.on('click', '.productEditBtn', function () {
		selectedProduct = $(this).parents('tr').data('product.row.data');
		console.log('selectedProduct', selectedProduct);
		productUpdateModal.modal('show');
	});
	
	productTable.on('click', '.productDeleteBtn', function () {
		selectedProduct = $(this).parents('tr').data('product.row.data');
		sysConfirm({
			title: 'Delete Product',
			text: 'Do you really want to delete this entry?',
			ok: function (modal) {
				$.post($g.root_path + 'product.delete', selectedProduct).done(function (result) {
					productTable.DataTable().ajax.reload();
					modal.modal('hide');
					sysAlert({
						text: result.response,
						delay: 2000
					});
				});
			}
		});
	});

	getAllBrands();
	getAllCategories();
	
	function getAllBrands() {
		var brandElem = $('.brand');

		$.get($g.root_path + 'brand.getAll').done(function (result) {
//			console.log('getAllBrands', result);
			brandElem.clearOptions('Select a brand');
			result.data.forEach(function (brand) {
				brandElem.createOption(brand.id, brand.name);
			});
		});
	}

	function getAllCategories() {
		var categoryElem = $('.category');

		$.get($g.root_path + 'category.getAll').done(function (result) {
//			console.log('getAllCategories', result);
			categoryElem.clearOptions('Select a category');
			result.data.forEach(function (category) {
				categoryElem.createOption(category.id, category.name);
			});
		});
	}
});