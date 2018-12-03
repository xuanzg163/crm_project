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

    /**
     *  拦截器方法执行前执行
     * @param request 请求
     * @param response 返回
     * @param handler 预处理方法
     * @return 布尔值
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //获取用户id(cookie)
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        //校验用户是否登陆，想数据库发起查询验证cookie信息中的id对应的数据是否存在
        AssertUtil.isNotLogin(null == userId ||
                        null == userService.queryById(userId)
                , CrmConstant.USER_NOT_LOGIN_MSG);

        return true;
    }

}
