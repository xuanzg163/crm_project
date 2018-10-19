package com.shsxt.crm.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * @author zhangxuan
 * @date 2018/10/19
 * @time 9:26
 */

public class RoleQuery extends BaseQuery {

    private String roleName;
    private String createDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
