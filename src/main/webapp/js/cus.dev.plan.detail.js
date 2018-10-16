$(function () {
    var sid = $("#saleChanceId").val();

    //表格初始化
    $("#dg").edatagrid({
        url:ctx + "/cusDevPlan/queryCusDevPlansByParams?sid="+sid,
        saveUrl: ctx + "/cusDevPlan/saveOrUpdateCusDevPlan?sid="+sid,
        updateUrl:ctx + "/cusDevPlan/saveOrUpdateCusDevPlan?sid="+sid,
    });
});

//添加
function addRow() {
    $("#dg").edatagrid("addRow");
}

function saveOrUpdateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    
}

function loadEdatagrid() {
    $("#dg").edatagrid("load");
}

//删除
function delCusDevPlan() {
    deleteData("dg",ctx + '/cusDevPlan/deleteCusDevPlanBatch',loadEdatagrid);

}