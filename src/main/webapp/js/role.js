//查询
function queryRolesByParams() {
    $("#dg").datagrid("load",{
        roleName:$("#roleName").val(),
            createDate: $("#time").datebox("getValue")
    })
}

//添加
function openAddRoleDailog() {
    openAddOrUpdateDlg("dlg","添加角色");
}
function saveOrUpdateRole() {
    saveOrUpdateData("fm", ctx + "/role/saveOrUpdateRole","dlg",queryRolesByParams);
}
function openModifyRoleDialog() {
    openModifyDialog("dg","fm","dlg","更新角色");
}