package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther zhangxuan
 * @date 2018/10/13
 * @time 11:38
 */

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;


    /**
     * 修改用户密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param userId
     */
    public void updateUserPwd(String oldPassword,
                              String newPassword,
                              String confirmPassword,
                              Integer userId) {

        //1、参数非空检验，旧密码是否正确
        checkUpdateUserPwdParams(oldPassword,newPassword,confirmPassword);

        //2、更新密码
        User user = userMapper.queryById(userId);
        AssertUtil.isTrue(null == user,"用户不存在或者已经注销");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()),"旧密码不正确");

        //2、1加密新密码
        String encodeNewPassword = Md5Util.encode(newPassword);
        AssertUtil.isTrue(userMapper.updateUserPwd(encodeNewPassword,userId) < 1,
                "用户更新密码失败");


    }

    /**
     * 两次密码参数校验
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkUpdateUserPwdParams(String oldPassword, String newPassword,
                                          String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"旧密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"两次密码不一致");
    }


    /**
     *  用户登陆
     * @param userName
     * @param userPwd
     */
    public UserInfo login(String userName, String userPwd){

        //1、参数校验
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码为空");

        //2、通过用户名查询用户信息
        User user = userMapper.queryUserByName(userName);

        //2、1判断用户是否存在
        AssertUtil.isTrue(null == user,"用户不存在或已经注销");

        //3、匹配密码是否一致
        //3、1 数据库中存在的用户密码是加密之后的值
        AssertUtil.isTrue(!Md5Util.encode(userPwd).equals(user.getUserPwd()),"用户名或密码不正确");

        return createUserInfo(user);
    }

    private UserInfo createUserInfo(User user){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userInfo.setUserName(user.getUserName());
        userInfo.setUserPwd(user.getTrueName());
        return userInfo;
    }
}
