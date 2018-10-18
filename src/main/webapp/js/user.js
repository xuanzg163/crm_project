//查询
function queryUsersByParams() {
    $("#dg").datagrid("load",{
        userName:$("#userName").val(),
        email:$("#email").val(),
        phone:$("#phone").val(),
    })
}

//添加
function openAddUserDailog() {
    openAddUserDailog("dig","添加用户");
}

$(function () {
    $("#dlg").dialog({
        "onClose":function () {
            //关闭清空表单
            $("#fm").form("clear");
        }
    })
});

function saveOrUpdateUser() {
    saveOrUpdateUser("fm",ctx +"/user/saveOrUpdateUser","dlg"
    ,queryUsersByParams());
}