
//查询
function queryCustomersByParams() {
    $("#dg").datagrid("load",{
        khno: $('#khno').val(),
        name: $('#cusName').val(),
        fr: $('#fr').val()
    });
}

//添加
function openAddCustomerDialog() {
    openAddOrUpdateDlg("dlg","添加客户");
}

//添加或更新
function saveOrUpdateCustomer() {
    saveOrUpdateData("fm",ctx + '/customer/saveOrUpdateCustomer','dlg',queryCustomersByParams);
}

//用户更新
function openModifyCustomerDialog() {
    openModifyDialog("dg","fm","dlg",'客户更新')
}