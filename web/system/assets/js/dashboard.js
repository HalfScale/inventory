$(function () {

	var totalInventoryProductsLabel = $('#totalInventoryProductsLabel'),
			totalSoldProductsLabel = $('#totalSoldProductsLabel'),
			dashBoardTable = $('#dashBoardTable'),
			totalUsers = $('#totalUsers');

	init();
	
	$.get($g.root_path + 'productTransactionDetail.getAll').done(function (result) {
		console.log('productTransactionDetail result', result);
		
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
	}

	function getTotalInventoryProducts() {
		$.get($g.root_path + 'product.getAll').done(function (result) {
			console.log('product.getAll', result);
			var label = result.data.length;
			totalInventoryProductsLabel.text(label);
		});
	}

	function getTotalSoldProducts() {
		$.get($g.root_path + 'productTransaction.getAllDetails').done(function (result) {
			console.log('productTransaction result', result);
			var totalSoldProduct = 0;
			result.data.forEach(function (prod) {
				totalSoldProduct += prod.quantity;
			});

			totalSoldProductsLabel.text(totalSoldProduct);
		});
	}
	
	function getTotalUsers() {
		$.get($g.root_path + 'user.getAll').done(function (result) {
			console.log('result', result);
			var label = result.data.length;
			totalUsers.text(label);
		});
	}
});