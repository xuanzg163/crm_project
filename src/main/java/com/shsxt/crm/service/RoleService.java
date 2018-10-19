package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.ModuleMapper;
import com.shsxt.crm.dao.PermissionMapper;
import com.shsxt.crm.dao.RoleMapper;
import com.shsxt.crm.dao.UserRoleMapper;
import com.shsxt.crm.po.Permission;
import com.shsxt.crm.po.Role;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 删除角色
     * @param ids
     * @throws DataAccessException
     */
    public void deleteRoleBatch(Integer[] ids) throws DataAccessException {

        /***
         * 1. 每删除一个角色, 就要删除t_user_role的记录 和 t_permission
         * */
        if(null != ids && ids.length > 0){

            for (Integer roleId: ids) {

                //删除角色
                AssertUtil.isTrue(roleMapper.delete(roleId) < 1,
                        CrmConstant.OPS_FAILED_MSG);

                //删权限
                Integer num1 = permissionMapper.queryModulesByRoleId(roleId);

                if (num1 > 0){
                    AssertUtil.isTrue(permissionMapper.deleteModulesByRoleId(roleId)<num1,
                            CrmConstant.OPS_FAILED_MSG);
                }

                //删除用户角色
                Integer num2 = userRoleMapper.queryUserRolesByRoleId(roleId);
                if (num2 > 0){
                    AssertUtil.isTrue(userRoleMapper.deleteUserRolesByUserId(roleId)<num2,
                            CrmConstant.OPS_FAILED_MSG);
                }
            }

        }

    }


    /**
     *  角色授权
     * @param roleId
     * @param moduleIds
     */
    public void doGrant(Integer roleId, Integer[] moduleIds) {
        System.out.println(roleId);

        AssertUtil.isTrue(null == roleId,"角色ID为空");
        AssertUtil.isTrue(null == roleMapper.queryById(roleId),"角色不存在");

        if (null != moduleIds && moduleIds.length > 0){
            // 角色授权操作
            // 先查询权限,有先删除再添加
            // 先查询权限,无直接添加

            Integer num = permissionMapper.queryModulesByRoleId(roleId);
            if (num>0){
                AssertUtil.isTrue(permissionMapper.deleteModulesByRoleId(roleId) <num
                ,CrmConstant.OPS_FAILED_MSG);
            }

            /**
             * 参数补全
             */
            List<Permission> permissions = new ArrayList<>();
            for (Integer moduleId : moduleIds){
                Permission permission = new Permission();
                permission.setRoleId(roleId);
                permission.setModuleId(moduleId);
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());

                //获取权限码
                permission.setAclValue(moduleMapper.queryById(moduleId).getOptValue());
                permissions.add(permission);
            }

            AssertUtil.isTrue(permissionMapper.saveBatch(permissions)<permissions.size(),
                    CrmConstant.OPS_FAILED_MSG);
        }

    }



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
