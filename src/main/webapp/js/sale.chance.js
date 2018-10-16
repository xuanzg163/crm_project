//查询方法
function querySaleChancesByParams() {
    $("#dg").datagrid("load",{
        customerName: $('#customerName').val(),
        state: $('#state').combobox('getValue'),
        devResult: $('#devResult').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    });
}

// 数据化格式显示
function formatterState(value,row,index) {

    if(0 == value){
        return "未分配";
    }
    if (1 == value){
        return "已分配";
    }
}

function formatterDevResult(value,row,index) {
    if(0 == value){
        return "未开发";
    }else if(1 == value){
        return "开发中";
    }else if(2 == value){
        return "开发成功";
    }else if(3 == value){
        return "开发失败";
    }
}

//修改背景颜色
$(function () {
    $("#dg").datagrid({
        rowStyler: function(index,row){

            var devResult = row.devResult;
            if (devResult == 0) {
                return "background-color:#5bc0de;"; // 蓝色
            }else if (devResult == 1) {
                return "background-color:#f0ad4e;"; // 黄色
            } else if (devResult == 2) {
                return "background-color:#5cb85c;"; // 绿色
            } else if (devResult == 3) {
                return "background-color:#d9534f;"; // 红色
            }
        }
    });

    $("#dlg").dialog({
        "onClose":function () {
            $("#fm").form("clear");

        }
    })
});

//打开弹窗
function openAddSaleChacneDialog() {
    $("#dlg").dialog("open");
    
}

//添加
//前台用表单提交
function saveOrUpdateSaleChance() {
    $("#fm").form("submit",{
        url: ctx + '/saleChance/saveOrUpdateSaleChance',
        onSubmit: function () {
            return $(this).form('validate');
        },
        success:function (data) {
            data = JSON.parse(data);//easy UI的表单提交，需要将数据转换成json
            if (data.code == 200){
                $.messager.alert('来自Crm', data.msg, 'info', function () {
                    // 关闭弹窗
                    $('#dlg').dialog('close');
                    // 刷新数据表格
                    $('#dg').datagrid('load');
                });
            } else {
                $.messager.alert('来自Crm', data.msg, 'error');
            }
        }
    });
}

//更新
function openModifySaleChanceDialog() {
    var rows = $("#dg").datagrid("getSelections");

    if (rows.length==0){
        $.messager.alert("来自CRM","清选择一条数据进行更新");
        return;
    }

    if (rows.length>1){
        $.messager.alert("来自CRM","只能选择一条数据进行更新");
        return;
    }

    /***
     * 1. 回填表单数据
     * 2. 显示弹窗
     * */
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","更新营销机会");
}

//删除
function deleteSaleChance() {
    var rows = $('#dg').datagrid("getSelections");
    if (rows.length <= 0) {
        $.messager.alert('来自Crm', "请选择一条数据进行删除");
        return;
    }

    $.messager.confirm('来自Crm', '确定删除'+rows.length+'条数据?',function (r) {
        if(r){
            //console.log('del ...');
            // 形式: ?ids=1&ids=2&
            var ids = '';
            for(var i=0; i<rows.length; i++){
                ids += 'ids='+rows[i].id+'&';
            }
            //console.log(ids);
            $.ajax({
                url: ctx + '/saleChance/deleteSaleChanceBatch?'+ids,//jQuery的ajax提交，自动将数据转换成了json
                type: 'post',
                success:function (data) {
                    if (data.code == 200) {
                        $.messager.alert('来自Crm', data.msg, 'info', function () {
                            // 关闭弹窗
                            $('#dlg').dialog('close');
                            // 刷新数据表格
                            $('#dg').datagrid('load');
                        });
                    } else {
                        $.messager.alert('来自Crm', data.msg, 'error');
                    }
                }
            })
        }
    });


}


