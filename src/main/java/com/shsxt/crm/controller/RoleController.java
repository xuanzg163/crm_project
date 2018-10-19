package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/18
 * @time 11:24
 */

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 显示角色管理
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "role";
    }

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map> queryAllRoles() {

        return roleService.queryAllRoles();
    }

    /**
     * 根据条件查询角色
     * 分页
     * @param page
     * @param rows
     * @param query
     * @return
     */
    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String, Object> queryRolesByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            RoleQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return roleService.queryForPage(query);
    }
}
