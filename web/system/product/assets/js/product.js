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

	getAllBrands();
	getAllCategories();

	function getAllBrands() {
		var brandElem = $('.brand');

		$.get($g.root_path + 'brand.getAll').done(function (result) {
			console.log('getAllBrands', result);
			result.data.forEach(function (brand) {
				$('<option>', {
					value: brand.id
				}).text(brand.name)
						.appendTo(brandElem);
			});
		});
	}

	function getAllCategories() {
		var categoryElem = $('.category');

		$.get($g.root_path + 'category.getAll').done(function (result) {
			console.log('getAllCategories', result);
			result.data.forEach(function (category) {
				$('<option>', {
					value: category.id
				}).text(category.name)
						.appendTo(categoryElem);
			});
		});
	}
});