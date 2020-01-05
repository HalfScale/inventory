$(function () {
    var posProductTable = $('#posProductTable'),
            checkOutTable = $('#checkOutTable'),
            posCheckoutDialog = $('#posCheckoutDialog'),
            checkoutTransactionType = $('#checkoutTransactionType'),
            checkoutTotal = $('#checkoutTotal'),
            checkOutBttn = $('#posCheckOutBttn');

    getTransactionTypes();

    posProductTable.DataTable({
        ajax: {
            url: $g.root_path + 'product.getAll',
            dataSrc: 'data'
        },
        columns: [
            {data: 'code'},
            {data: 'name'},
            {data: 'price'},
            {data: 'stock'}
        ],
        createdRow: function (row, data) {
            $(row).addClass('productRow clicky')
                    .data('product.row.data', data);
        }
    });

    var posProductEntries = [];
    posProductTable.on('click', '.productRow', function () {
        var data = $(this).data('product.row.data');
        console.log('productRow data', data);

        var tbody = checkOutTable.find('tbody');

        if (!posProductEntries.includes(data.id)) {
            posProductEntries.push(data.id);

            sysConfirm({
                title: 'Type of price',
                text: 'Is a reseller price?',
                ok: function (modal) {
                    createCheckOutRow(data, true).appendTo(tbody);
                    checkoutTotal.text('Total: ' + computeCheckoutTotal().toFixed(2));
                    modal.modal('hide');
                },
                okText: 'Yes',
                cancel: function () {
                    createCheckOutRow(data, false).appendTo(tbody);
                    checkoutTotal.text('Total: ' + computeCheckoutTotal().toFixed(2));
                },
                cancelText: 'No'
            });
        }

    });
    
    posCheckoutDialog.standardDialog({
        title: 'Checkout Transaction',
        onOpen: function() {
            posCheckoutDialog.find('.total').val(computeCheckoutTotal().toFixed(2));
        },
        ajax: function(fd, form) {
            var product = [];
            checkOutTable.find('tbody').children().each(function () {
                var item = $(this).data('checkout.item.data');
                var quantity = $(this).find('input').val();
                var isReseller = $(this).data('reseller.data');
                
                product.push({
                    productId: item.id,
                    quantity: quantity,
                    isReseller: isReseller
                });
            });
            
            fd['products'] = JSON.stringify(product);
            console.log('posCheckoutDialog fd', fd);
            return $.post($g.root_path + 'product.checkout', fd);
        }
    });
    
    checkOutBttn.on('click', function () {
        posCheckoutDialog.modal('show');
    });
    
    function checkout() {
        
    }

    function getTransactionTypes() {
        $.get($g.root_path + 'transactionType.getAll').done(function (result) {
            console.log('result', result);
            checkoutTransactionType.clearOptions('Select a transaction type');
            result.data.forEach(function (item) {
                checkoutTransactionType.createOption(item.id, item.name);
            });
        });
    }

    function createCheckOutRow(data, isReseller) {
        var row = $('<tr>').data('checkout.item.data', data)
                .data('reseller.data', isReseller);

        [
            {
                el: '<span>',
                attr: {
                    text: data.name
                }
            },
            {
                el: '<input>',
                attr: {
                    type: 'number',
                    class: 'wem-4'
                }
            },
            {
                el: '<span>',
                attr: {
                    text: isReseller ? data.resellerPrice : data.price
                }
            }
        ].forEach(function (col) {
            var td = $('<td>');
            var el = $(col.el, col.attr).appendTo(td);

            if (col.el === '<input>') {
                el.val('1');
                el.on('change', function () {
                    var val = $(this).val();
                    if (val === '0') {
                        posProductEntries.some(function (id, index) {
                            if (data.id === id) {
                                posProductEntries.splice(index, 1);
                            }
                        });
                        row.remove();
                    }
                });
            }

            td.appendTo(row);
        });

        return row;
    }
    
    function computeCheckoutTotal() {
        var total = 0;
        checkOutTable.find('tbody').children().each(function () {
            var item = $(this).data('checkout.item.data');
            var isReseller = $(this).data('reseller.data');
            
            console.log('item', item);
            
            if (isReseller) {
                total += Number(item.resellerPrice);
            }else {
                total += Number(item.price);
            }
        });
        
        return total;
    }

});