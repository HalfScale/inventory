$(function () {

	var totalInventoryProductsLabel = $('#totalInventoryProductsLabel'),
			totalSoldProductsLabel = $('#totalSoldProductsLabel'),
			dashBoardTable = $('#dashBoardTable'),
			totalUsers = $('#totalUsers');

	init();

	$('.dashboard-item').on('click', function () {
		var data = $(this).data('ref');
		
		if (data != null) {
			window.open(data, '_blank');
		}
	});

	dashBoardTable.DataTable({
		ajax: {
			url: $g.root_path + 'productTransactionDetail.getAll',
			dataSrc: 'data'
		},
		columns: [
			{
				data: 'productTransaction',
				render: function (data) {
					var user = data.user;
					return user.firstName + ' ' + user.lastName;
				}
			},
			{data: 'productTransaction.timestamp'},
			{
				data: null,
				render: function () {
					return 'Checkout';
				}
			},
			{data: 'product.name'},
			{data: 'quantity'},
			{
				data: null,
				render: function (data) {
					var quantity = data.quantity;

					if (data.isReseller) {
						return (data.product.resellerPrice * quantity).toFixed(2).commafy();
					}

					return (data.product.price * quantity).toFixed(2).commafy();
				}
			}
		]
	});
	
	$('#dashBoardTable_wrapper').addClass('dashboard-table-margin');

	function init() {
		getTotalInventoryProducts();
		getTotalSoldProducts();
		getTotalUsers();
		
		if (window.location.hash === '') {
			
		}
	}

	function getTotalInventoryProducts() {
		totalInventoryProductsLabel.text('Loading');
		$.get($g.root_path + 'product.getAll').done(function (result) {
			console.log('product.getAll', result);
			var label = result.data.length;
			totalInventoryProductsLabel.text(label);
			totalInventoryProductsLabel.parent().data('ref', $g.root_path + 'system/product/')
					.addClass('dashboard-anim clicky');
		});
	}

	function getTotalSoldProducts() {
		totalSoldProductsLabel.text('Loading');
		$.get($g.root_path + 'productTransaction.getAllDetails').done(function (result) {
			console.log('productTransaction result', result);
			var totalSoldProduct = 0;
			result.data.forEach(function (prod) {
				totalSoldProduct += prod.quantity;
			});

			totalSoldProductsLabel.text(totalSoldProduct);
			totalSoldProductsLabel.parent().data('ref', $g.root_path + 'system/transaction/')
					.addClass('dashboard-anim clicky');
		});
	}

	function getTotalUsers() {
		totalUsers.text('Loading');
		$.get($g.root_path + 'user.getAll').done(function (result) {
			console.log('result', result);
			var label = result.data.length;
			totalUsers.text(label);
			totalUsers.parent().data('ref', $g.root_path + 'system/user/')
					.addClass('dashboard-anim clicky');
		});
	}
});