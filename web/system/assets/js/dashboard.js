$(function () {
	
	var totalInventoryProductsLabel = $('#totalInventoryProductsLabel');
	
	init();
	
	function init() {
		getTotalInventoryProducts();
	}
	
	function getTotalInventoryProducts() {
		$.get($g.root_path + 'product.getAll').done(function (result) {
			console.log('product.getAll', result);
			var message = 'Total Inventory Products ' + result.data.length;
			totalInventoryProductsLabel.text(message);
		});
	}
});