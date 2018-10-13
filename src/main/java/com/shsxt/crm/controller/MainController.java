package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.po.User;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther zhangxuan
 * @date 2018/10/13
 * @time 10:15
 */

@Controller
public class MainController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        /**
         * 查询用户，存入request作用域
         * 完成回显操作
         */
        Integer userid = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.queryById(userid);
        request.setAttribute("user",user);
        return "main";
    }
}
