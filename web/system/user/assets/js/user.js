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
            {data: 'username'},
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
        scrollable: true,
        ajax: function (fd) {
            
            if (fd.password !== fd.confirmPassword) {
                return {status: 1, response: 'Password does not match!'};
            }
            
            return $.post($g.root_path + 'user.create', fd);
        },
        done: function (result, modal) {
            userTable.DataTable().ajax.reload();
            if (result.status === 0) {
                sysAlert({
                    text: result.response,
                    delay: 2000
                });
                modal.modal('hide');
            }else if (result.status === 1) {
                sysAlert({
                    text: result.response,
                    delay: 4000,
                    type: 'danger'
                });
            }
        }
    });
    
    userUpdateModal.standardDialog({
        title: 'Update User',
        scrollable: true,
        ajax: function (fd) {
            console.log('fd', fd);
            if (fd.password !== fd.confirmPassword) {
                return {status: 1, response: 'Password does not match!'};
            }
            
            return $.post($g.root_path + 'user.update', fd);
        },
        done: function (result, modal) {
            userTable.DataTable().ajax.reload();
            
            if (result.status === 0) {
                sysAlert({
                    text: result.response,
                    delay: 2000
                });
                modal.modal('hide');
            } else if (result.status === 1) {
                sysAlert({
                    text: result.response,
                    delay: 4000,
                    type: 'danger'
                });
            }
        }
    });
});