package com.shsxt.crm.aop;

import com.shsxt.crm.annotations.RequestPermission;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.utils.AssertUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * AOP切自定义注解形成切面
 * 获取目标方法参数，判定权限码
 * @author zhangxuan
 * @date 2018/10/20
 * @time 19:05
 */
@Component
@Aspect
public class PermissionAdaptor {

    @Autowired
    private HttpSession session;

    /**
     * 切自定义注解，形成一个切面
     */
    @Pointcut("@annotation(com.shsxt.crm.annotations.RequestPermission)")
    public void cut() {}

    /**
     *  环绕通知
     *  只有环绕通知可以获得目标方法
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequestPermission permission = method.getAnnotation(RequestPermission.class);
        String aclValue = permission.aclValue();

        /***
         * 1. 获取目标方法的权限码
         * 2. 获取当前用户的权限列表
         * */
        List<String> permissions = (List<String>) session.
                getAttribute(CrmConstant.USER_PERMISSIONS);

        AssertUtil.isTrue(CollectionUtils.isEmpty(permissions) ||
                !permissions.contains(aclValue),"没有权限");

        result = pjp.proceed();

        return result;

    }
}
