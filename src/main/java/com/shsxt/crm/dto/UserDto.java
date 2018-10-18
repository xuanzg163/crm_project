package com.shsxt.crm.dto;

import com.shsxt.crm.po.User;

/**
 * @author zhangxuan
 * @date 2018/10/18
 * @time 10:09
 */

public class UserDto extends User {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
