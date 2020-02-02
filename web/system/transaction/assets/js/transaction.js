$(function () {
	var transactionTable = $('#transactionTable'),
			transactionDetailTotal = $('#transactionDetailTotal'),
			transactionDetailDialog = $('#transactionDetailDialog'),
			transactionDetailTable = transactionDetailDialog.find('table');

	transactionTable.DataTable({
		ajax: {
			url: $g.root_path + 'productTransaction.getAll',
			dataSrc: 'data'
		},
		columns: [
			{
				data: null,
				render: function (data) {
					var firstName = data.transaction.user.firstName;
					var lastName = data.transaction.user.lastName;
					return firstName + ' ' + lastName;
				}
			},
			{data: 'transaction.transactionType.name'},
			{data: 'transaction.timestamp'}
		],
		createdRow: function (row, data) {
			$(row).addClass('transaction-row clicky')
					.data('transaction.row.data', data);
		}
	});

	transactionDetailDialog.standardDialog({
		title: 'Transaction Details',
		scrollable: true,
		xlarge: true,
		hideButtons: true
	});

	transactionTable.on('click', '.transaction-row', function () {
//		console.log('transaction row data', $(this).data('transaction.row.data'));
		var data = $(this).data('transaction.row.data');
		var tbody = transactionDetailTable.find('tbody').empty();
		
		data.transactionDetails.forEach(function (detail) {
			createTransactionDetailRow(detail).appendTo(tbody);
		});
		
		var transactionDetailsTotal = computeTransactionDetails(data);
		transactionDetailTotal.text(transactionDetailsTotal.toFixed(2).commafy());
		transactionDetailDialog.modal('show');
	});

	function createTransactionDetailRow(data) {
		var row = $('<tr>');

		[
			{
				text: data.product.name
			},
			{
				text: data.product.brand.name,
				class: 'text-center'
			},
			{
				text: data.product.category.name,
				class: 'text-center'
			},
			{
				text: data.quantity,
				class: 'text-center'
			},
			{
				text: data.product.price.toFixed(2).commafy(),
				class: 'text-right'
			},
			{
				text: data.product.resellerPrice.toFixed(2).commafy(),
				class: 'text-right'
			},
			{
				text: data.isReseller,
				class: 'text-center',
				type: 'badge'
			}
		].forEach(function (col) {
			var td = $('<td>');

			if (col.type === 'badge') {
				if (col.text) {
					$('<span>', {
						class: 'badge badge-success',
						text: 'Yes'
					}).appendTo(td);
				} else {
					$('<span>', {
						class: 'badge badge-danger',
						text: 'No'
					}).appendTo(td);
				}
			} else {
				td.text(col.text);
			}

			td.addClass(col.class ? col.class : '');
			td.appendTo(row);
		});

		return row;
	}

	function computeTransactionDetails(data) {
		var total = 0.00;
		data.transactionDetails.forEach(function (detail) {
			console.log('detail data', detail);
			var quantity = detail.quantity;
			
			if (detail.isReseller) {
				total += detail.product.resellerPrice * quantity;
			}else {
				total += detail.product.price * quantity;
			}
		});
		
		return total;
	}
});