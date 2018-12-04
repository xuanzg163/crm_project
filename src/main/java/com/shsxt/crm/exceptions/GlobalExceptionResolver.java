package com.shsxt.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 全局异常实现类
 * 实现HandlerExceptionResolver
 * @author zhangxuan
 * @date 2018/10/15
 * @time 9:32
 */

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView mv = createDefaultModelAndView(request, ex);

        /***
         * 1. 区分是什么异常
         * 2. 区分是页面请求还是json请求
         * */
        if (ex instanceof LoginException){
            mv.addObject("errorMsg", CrmConstant.USER_NOT_LOGIN_MSG);
            mv.setViewName("login_error");
            return mv;
        }

        /**
         * 区分请求
         */
        if (handler instanceof HandlerMethod) {
            //获取handlerMethod
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取method
            Method method = handlerMethod.getMethod();
            //获取ResponseBody
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);

            //2. 区分是页面请求还是json请求
            if (null == responseBody) {

                //普通页面跳转
                if (ex instanceof ParamsException) {
                    ParamsException e = (ParamsException) ex;
                    mv.addObject("errorMsg", e.getMsg());
                }
            } else {

                //json请求
                ResultInfo info = new ResultInfo();
                //设置错误参数
                info.setCode(300);
                info.setMsg("系统繁忙");

                /**
                 * 设置对应的参数错误信息参数
                 */
                if (ex instanceof ParamsException) {
                    ParamsException e = (ParamsException) ex;
                    info.setMsg(e.getMsg());
                }

                /**
                 * 设置相应的编码格式，避免乱码
                 */
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter pw = null;

                /**
                 * 通过流将数据写出
                 */
                try {
                    pw = response.getWriter();
                    pw.write(JSON.toJSONString(info));
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != pw) {
                        //关闭资源
                        pw.close();
                    }
                }

                return null;
            }
        }
        return mv;
    }

    private ModelAndView createDefaultModelAndView(HttpServletRequest request,
                                                   Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");//错误页面
        mv.addObject("errorMsg", "系统繁忙");//错误信息
        mv.addObject("errorCode", 300);//错误码
        mv.addObject("ctx", request.getContextPath());//上下文路径
        mv.addObject("uri", request.getRequestURI()); // 请求路径
        return mv;
    }
}
