package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther zhangxuan
 * @date 2018/10/13
 * @time 11:49
 */

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 用户登陆
     * @param userName
     * @param userPwd
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserInfo userInfo = userService.login(userName, userPwd);
            return success("登陆成功",userInfo);
        }catch (ParamsException e) {
            e.printStackTrace();
            resultInfo.setCode(300);
            resultInfo.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(300);
            resultInfo.setMsg(e.getMessage());
        }
        return resultInfo;
    }

    /**
     *  更新用户密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param request
     * @return
     */
    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPassword,
                                    String newPassword,
                                    String confirmPassword,
                                    HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);// 获取id

        try {
            userService.updateUserPwd(oldPassword, newPassword,
                    confirmPassword, userId);
            return sucess("修改成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return success(e.getMsg(),300);
        } catch (Exception e) {
            e.printStackTrace();
            return success(e.getMessage(),300);
        }
    }
}
