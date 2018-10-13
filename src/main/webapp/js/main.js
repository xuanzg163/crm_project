function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("select", text);
    } else {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        });
    }
}

/**
 * 安全退出
 */

function logout() {
    /***
     * 1. 清除cookie
     * 2. 跳转登陆页
     * */
    $.messager.confirm("来自系统", "确定要退出吗", function (r) {
        if (r) {
            $.removeCookie("userIdStr");
            $.removeCookie("userName");
            $.removeCookie("realName");

            window.location.href = ctx + "/index";
        }
    });
}


/**
 * 修改密码
 */
function openPasswordModifyDialog() {
    $("#dlg").dialog("open");

}

function modifyPassword() {
    $("#fm").form("submit", {
        url: ctx + "/user/updateUserPwd",
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (data) {

            data = JSON.parse(data);
            if (data.code == 200) {
                $.messager.alert("来自系统", data.msg, "info", function () {
                    $.removeCookie("userIdStr");
                    $.removeCookie("userName");
                    $.removeCookie("realName");

                    window.location.href = ctx + "/index";
                });
            } else {
                $.messager.alert('来自crm', data.msg, 'error');
            }
        }
    });
}


