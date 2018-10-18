package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhangxuan
 * @date 2018/10/13
 * @time 11:49
 */

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "user";
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd){
        UserInfo userInfo = userService.login(userName, userPwd);
        return success("登陆成功", userInfo);
    }

    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPassword,
                                    String newPassword,
                                    String confirmPassword,
                                    HttpServletRequest request){
        // 获取id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updateUserPwd(oldPassword, newPassword, confirmPassword, userId);
        return success("修改成功");
    }

    @RequestMapping("queryUsersByParams")
    @ResponseBody
    public Map<String, Object> queryUsersByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            UserQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return userService.queryForPage(query);
    }

}
