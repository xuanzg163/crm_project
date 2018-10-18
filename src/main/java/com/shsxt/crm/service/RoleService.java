package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/18
 * @time 11:22
 */

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;

    public List<Map> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }
}
