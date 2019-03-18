package com.he.aop;

import com.he.controller.SuperController;
import com.he.log.Log;
import com.he.log.Module;
import com.he.syslog.AppVisitor;
import com.he.syslog.enums.LogMode;
import com.he.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
public abstract class SuperAspect {
    protected static LocalVariableTableParameterNameDiscoverer parameterNameDiscovere =
            new LocalVariableTableParameterNameDiscoverer();

    /**
     * 获取当前执行的方法
     */
    protected Method getMethod(JoinPoint joinPoint) {
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        try {
            return target.getClass().getDeclaredMethod(msig.getName(), msig.getParameterTypes());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        } catch (SecurityException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    protected String getModuleName(Class<?> cl) {
        if (cl.isAnnotationPresent(Module.class)) {
            Module m = cl.getAnnotation(Module.class);
            if (m != null) {
                return m.name();
            }
        }
        return null;
    }

    private AppVisitor getAppVisitor(ProceedingJoinPoint joinPoint, Method method) {
        AppVisitor av = null;
        Log logAnnot = method.getAnnotation(Log.class);
        LogMode lm = logAnnot.mode();
        if (lm.equals(LogMode.WEB)) {
            av = getAppVisitorFromWeb(joinPoint);
        } else if (lm.equals(LogMode.FACE)) {
            av = getAppVisitorFromFace(joinPoint);
        } else if (lm.equals(LogMode.SERVICE)) {
            av = getAppVisitorFromService(joinPoint);
        }
        return av;
    }

    protected AppVisitor getAppVisitorFromWeb(JoinPoint joinPoint) {
        if (joinPoint.getTarget() instanceof SuperController) {
            return ((SuperController) joinPoint.getTarget()).getAuthUser();
        }
        return null;
    }

    protected AppVisitor getAppVisitorFromFace(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        final String requestHeader = request.getHeader(JwtTokenUtil.tokenHeader);
        if (requestHeader != null && requestHeader.startsWith(JwtTokenUtil.tokenHead)) {
            String authToken = requestHeader.substring(JwtTokenUtil.tokenHead.length());
            if (joinPoint.getTarget() instanceof SuperController) {
                return ((SuperController) joinPoint.getTarget()).getAuthUser(authToken);
            }
        }
        return null;
    }

    protected AppVisitor getAppVisitorFromService(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof AppVisitor) {
                    return (AppVisitor) arg;
                }
            }
        }
        return null;
    }
}
