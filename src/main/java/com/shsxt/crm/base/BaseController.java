package com.shsxt.crm.base;

import com.shsxt.crm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther zhangxuan
 * @date 2018/10/13
 * @time 17:36
 */

public class BaseController {

    public ResultInfo success(Integer code, String msg, Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(String msg, Object result){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(Integer code, String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    @ModelAttribute
    public void preHandle(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }

}
