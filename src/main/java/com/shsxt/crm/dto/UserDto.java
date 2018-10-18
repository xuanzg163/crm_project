package com.shsxt.crm.dto;

import com.shsxt.crm.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxuan
 * @date 2018/10/18
 * @time 10:09
 */


public class UserDto extends User {
    private String roleName;

    /**
     *roleIdsStr 接受字符串1，2，3
     *  List 存id[1,2,3]
     */
    private String roleIdsStr;
    private List<Integer> roleIds = new ArrayList<>();

    public String getRoleIdsStr() {
        return roleIdsStr;
    }

    public void setRoleIdsStr(String roleIdsStr) {
        this.roleIdsStr = roleIdsStr;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
