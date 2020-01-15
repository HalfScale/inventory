$(function() {
	var transactionTable = $('#transactionTable');
	var transactionDetailDialog = $('#transactionDetailDialog');
	var transactionDetailTable = transactionDetailDialog.find('table');
	
	transactionTable.DataTable({
        ajax: {
            url: $g.root_path + 'productTransaction.getAll',
            dataSrc: 'data'
        },
        columns: [
            {
                data: null,
                render: function(data) {
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
	
	transactionTable.on('click', '.transaction-row', function() {
//		console.log('transaction row data', $(this).data('transaction.row.data'));
		var data = $(this).data('transaction.row.data');
		var tbody = transactionDetailTable.find('tbody').empty();
		data.transactionDetails.forEach(function(detail) {
			createTransactionDetailRow(detail).appendTo(tbody);
		});
		
		transactionDetailDialog.modal('show');
	});
	
	function createTransactionDetailRow(data) {
		var row = $('<tr>');
		
		[
			data.product.code,
			data.product.name,
			data.product.price,
			data.product.resellerPrice,
			data.quantity,
			data.isReseller
		].forEach(function(text){
			var td = $('<td>').text(text);
			td.appendTo(row);
		});
		
		return row;
	}
});