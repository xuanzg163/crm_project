package com.shsxt.crm.interceptors;

import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.po.User;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxuan
 * @date 2018/10/15
 * @time 10:02
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //获取用户id(cookie)
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        //校验用户是否登陆
        AssertUtil.isNotLogin(null == userId || null == userService.queryById(userId), CrmConstant.USER_NOT_LOGIN_MSG);

        return true;
    }

}
