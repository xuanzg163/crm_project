//格式化层级
function formateGrade(val) {
    if (val == 0){
        return "根级节点";
    } else if (val == 1) {
        return "一级节点";
    } else if (val == 2) {
        return "二级节点";
    }
    
}

//查询
function queryModulesByParams() {
    $("#dg").datagrid("load",{
        moduleName: $("#moduleName").val(),
        parentId: $("#pid").val(),
        grade: $("#grade").combobox("getValue"),
        optValue: $("#optValue").val()
    });
}