package com.shsxt.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther zhangxuan
 * @date 2018/10/13
 * @time 10:15
 */

@Controller
public class MainController {

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
        return "main";
    }
}
