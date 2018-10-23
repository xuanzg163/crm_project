//添加服务
function saveCustomerServe() {
    $("#fm").form("submit",{
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.code == 200) {
                $.messager.alert('来自Crm', data.msg, "info", function () {
                    // 清空数据
                    $("#fm").form("clear");
                });
            } else {
                $.messager.alert('来自Crm', data.msg, "error");
            }
        }
    });
}