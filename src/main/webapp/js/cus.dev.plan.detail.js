$(function () {
    var sid = $("#saleChanceId").val();

    //表格初始化
    $("#dg").edatagrid({
        url:ctx + "/cusDevPlan/queryCusDevPlansByParams?sid="+sid,
        saveUrl:"",
        updateUrl:"",
        destroyUrl:""
    });
});