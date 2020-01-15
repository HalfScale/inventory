$(function () {
    var userTable = $('#userTable'),
            userAddBttn = $('#userAddBttn'),
            userAddModal = $('#userAddModal'),
            userUpdateModal = $('#userUpdateModal');

    userTable.DataTable({
        ajax: {
            url: $g.root_path + 'user.getAll',
            dataSrc: 'data'
        },
        columns: [
            {
                data: null,
                render: function (data) {
                    return data.firstName + ' ' + data.lastName;
                }
            },
            {data: 'email'},
            {data: 'status'}
        ],
        createdRow: function (row, data) {
            $(row).addClass('userRow clicky')
                    .data('user.row.data', data);
        }
    });
    
    userAddBttn.on('click', function () {
        userAddModal.modal('show');
    });
    
    userTable.on('click', '.userRow', function() {
        var data = $(this).data('user.row.data');
        userUpdateModal.fillForm({
            source: data,
            filter: function(key, val, elem) {
                if (key === 'password') {
                    elem.val('');
                }
                
                if (key === 'status') {
                    val = val ? "1" : "0";
                    elem.val(val);
                }
            }
        }).modal('show');
    });

    userAddModal.standardDialog({
        title: 'Add User',
        ajax: function (fd) {
            return $.post($g.root_path + 'user.create', fd);
        },
        done: function (result, modal) {
            userTable.DataTable().ajax.reload();
            modal.modal('hide');
            sysAlert({
                text: result.response,
                delay: 2000
            });
        }
    });
    
    userUpdateModal.standardDialog({
        title: 'Update User',
        ajax: function (fd) {
            return $.post($g.root_path + 'user.update', fd);
        },
        done: function (result, modal) {
            userTable.DataTable().ajax.reload();
            modal.modal('hide');
            sysAlert({
                text: result.response,
                delay: 2000
            });
        }
    });
});