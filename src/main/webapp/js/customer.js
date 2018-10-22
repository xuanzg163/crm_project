
//查询
function queryCustomersByParams() {
    $("#dg").datagrid("load",{
        khno: $('#khno').val(),
        name: $('#cusName').val(),
        fr: $('#fr').val()
    });
}