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

//授权
function openRelationPermissionDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0){
        $.messager.alert("来自Crm", "请选择一条数据进行授权");
        return;
    }
    if (rows.length > 1){
        $.messager.alert("来自Crm", "只能选择一条数据进行授权");
        return;
    }

    //打开弹窗
    $("#permissionDlg").dialog("open");
    //加载树
    loadTree(rows[0].id);
}

var zTreeObj;

function loadTree(roleId) {
    $.ajax({
        url: ctx + "/module/queryAllModuleByRoleId?roleId="+roleId,
        success:function (data) {
            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };
            var zNodes = data;
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
    });
}