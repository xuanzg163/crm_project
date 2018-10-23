$(function () {
    var lossId = $("#lossId").val();
    $("#dg").edatagrid({
        url:ctx + "/customerReprieve/queryCustomerReprievesByParams?lossId="+
            lossId,
        saveUrl: ctx + "/customerReprieve/saveOrUpdateCustomerReprieve?lossId="+lossId,
        updateUrl: ctx + "/customerReprieve/saveOrUpdateCustomerReprieve?lossId="+lossId,
        destroyUrl: ctx+ "/customerReprieve/delReprieve"
    });
});

//添加
function  addRow() {
    $("#dg").edatagrid("addRow");
}

//保存或更新

function saveOrUpdateCusDevPlan() {
    $('#dg').edatagrid("saveRow");
}

//删除
function delCusDevPlan() {
    $('#dg').edatagrid("destroyRow");
}

//确认流失
function confirmLoss() {
    var lossId = $('#lossId').val();
    $.messager.confirm('来自Crm',"是否确认流失",function (r) {
        if(r){
            // 发送ajax请求
            $.ajax({
                url: ctx + "/customerLoss/updateCustomerLoss",
                data:{
                    id: lossId,
                    state: 1
                },
                success:function (data) {
                    if (data.code == 200) {
                        $.messager.alert('来自Crm', data.msg, 'info', function () {
                            // 隐藏工具条
                            $('#toolbar').hide();
                            // 使表格不可编辑
                            $('#dg').edatagrid("disableEditing")
                        });
                    } else {
                        $.messager.alert('来自Crm', data.msg, 'error');
                    }
                }
            });
        }
    })
}