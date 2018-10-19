package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 添加或更新
     * @param role
     */
    public void saveOrUpdateRole(Role role) {

        String roleName = role.getRoleName();
        AssertUtil.isTrue(StringUtils.isBlank(roleName),"角色名称为空");

        /**
         * 角色名唯一校验
         * 参数补全
         */
        role.setUpdateDate(new Date());
        Integer id = role.getId();

        if (null == id){
            /**
             * 添加
              */
            AssertUtil.isTrue(null!=roleMapper.queryRoleByName(roleName),"角色已存在");
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role)<1, CrmConstant.OPS_FAILED_MSG);
        } else {

            /**
             * 更新
             */
            Role role1 = roleMapper.queryById(id);
            if (roleName.equals(role1.getRoleName())){

                AssertUtil.isTrue(null != roleMapper.queryRoleByName(roleName),"角色已存在");
            }

            /**
             *  只有当名字不一致时,才做唯一校验
             */
            AssertUtil.isTrue(roleMapper.update(role) <1,CrmConstant.OPS_FAILED_MSG);
        }
    }

    /**
     * 查询所有角色
     * @return
     */
    public List<Map> queryAllRoles() {
        return roleMapper.queryAllRoles();
    }


}
