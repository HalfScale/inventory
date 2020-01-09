$(function() {
	var transactionTable = $('#transactionTable');
	
	$.get($g.root_path + 'productTransaction.getAll').done(function(result) {
		console.log('result', result);
	});
	
//	transactionTable.DataTable({
//        ajax: {
//            url: $g.root_path + 'productTransaction.getAll',
//            dataSrc: 'data'
//        },
//        columns: [
//            {data: 'code'},
//            {data: 'name'},
//            {data: 'price'},
//            {data: 'stock'},
//            {data: 'status'}, {
//                data: null,
//                render: function () {
//                    return '<button type="button" class="productEditBtn btn btn-outline-warning btn-sm">' + 'Edit' + '</button>' +
//                            '<button type="button" class="productDeleteBtn btn btn-outline-danger btn-sm">' + 'Delete' + '</button>';
//                }
//            }
//        ],
//        createdRow: function (row, data) {
//            $(row).addClass('productRow')
//                    .data('product.row.data', data);
//        }
//    });
});