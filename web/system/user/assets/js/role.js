$(function () {
    var roleTable = $('#roleTable'),
            roleAddBttn = $('#roleAddBttn'),
            roleAddModal = $('#roleAddModal');

    roleTable.DataTable({
        ajax: {
            url: $g.root_path + 'role.getAll',
            dataSrc: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'name'}
        ],
        createdRow: function (row, data) {
            $(row).addClass('roleRow clicky')
                    .data('role.row.data', data);
        }
    });
    
    roleAddBttn.on('click', function() {
        roleAddModal.modal('show');
    });

    roleAddModal.standardDialog({
        title: 'Add Role',
        hideButtons: true
    });
});