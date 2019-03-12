package com.he.syslog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;

public abstract class RcAspect {

    protected static Logger log = LoggerFactory.getLogger(RcAspect.class);
    protected static LocalVariableTableParameterNameDiscoverer parameterNameDiscovere =
            new LocalVariableTableParameterNameDiscoverer();

    /**
     * get AppVisitor form web.
     * 
     * @param joinPoint
     * @return
     */
    protected AppVisitor getAppVisitorFromWeb(JoinPoint joinPoint) {
//        Mirror<?> cmirror = Mirror.me(joinPoint.getTarget().getClass());
//        if (cmirror.isOf(RcController.class)) {
//            RcController bc = (RcController) joinPoint.getTarget();
//            return bc.getAppVisitor();
//        }
        return null;
    }

    /**
     * get AppVisitor form face.
     * 
     * @param joinPoint
     * @return
     */
    protected AppVisitor getAppVisitorFromFace(JoinPoint joinPoint) {
        int sessionIdIdx = 0;
        Method method = getMethod(joinPoint);
        String[] parameterNames = parameterNameDiscovere.getParameterNames(method);

        String sessionIdValue = null;
        if (parameterNames != null && parameterNames.length > 0) {
            if (parameterNames.length == 1) {
                Object arg = joinPoint.getArgs()[0];
//                if (arg instanceof IRequestFaceMessage) {
//                    IRequestFaceMessage actArg = (IRequestFaceMessage) arg;
//                    sessionIdValue = actArg.getSessionId();
//                }
            } else {
                for (String parameterName : parameterNames) {
                    if ("sessionId".equals(parameterName)) {
                        Object[] args = joinPoint.getArgs();
                        if (args != null && args.length > sessionIdIdx) {
                            sessionIdValue = (String) args[sessionIdIdx];
                        }
                    }
                    sessionIdIdx++;
                }
            }
        }

//        if (!Strings.isBlank(sessionIdValue)) {
//            Mirror<?> cmirror = Mirror.me(joinPoint.getTarget().getClass());
//            if (cmirror.isOf(RcController.class)) {
//                RcController bc = (RcController) joinPoint.getTarget();
//                return bc.getAppVisitor(sessionIdValue);
//            }
//        }

        return null;
    }

    /**
     * get AppVisitor form face when user is no login,ex:register.
     * 
     * @param joinPoint
     * @return
     */
    protected AppVisitor getAppVisitorByUnloginedUserFromFace(ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        String[] parameterNames = parameterNameDiscovere.getParameterNames(method);
        if (parameterNames != null && parameterNames.length > 0) {
            if (parameterNames.length == 1) {
                Object arg = joinPoint.getArgs()[0];
//                if (arg instanceof PersonFaceRequest) {
//                    PersonFaceRequest actArg = (PersonFaceRequest) arg;
//                    AppVisitor av = new AppVisitor(actArg.getOrgId(), actArg.getOrgType(), actArg.getId(),
//                            actArg.getOperatorName(), actArg.getOperatorLoginName(), actArg.getOperatorType());
//                    if (Strings.isBlank(av.getOperatorId())) {
//                        av.setOperatorId("-1");
//                    }
//                    return av;
//                }
            }
        }
        return null;
    }

    /**
     * get AppVisitor form service.
     * 
     * @param joinPoint
     * @return
     */
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

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint
     *            连接点
     * @return 方法
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
}
