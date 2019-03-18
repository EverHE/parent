package com.he.log;

import com.alibaba.fastjson.JSON;
import com.he.aop.SuperAspect;
import com.he.base.ISuperService;
import com.he.syslog.enums.LogLevel;
import com.he.syslog.enums.OperationType;
import org.aspectj.lang.ProceedingJoinPoint;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

public class SysLogAspect extends SuperAspect {

    /**
     * 保存系统操作日志
     *
     * @param joinPoint
     *            连接点
     * @return 方法执行结果
     * @throws Throwable
     *             调用出错
     */
    public Object autoLog(ProceedingJoinPoint joinPoint) throws Throwable {
        LogLevel level = getLogLevl();
        if (level.equals(LogLevel.NONE)) {
            return joinPoint.proceed();
        } else {
            return  null;
//            if (operateLogService != null
//                    && Mirror.me(operateLogService.getClass()).is(joinPoint.getTarget().getClass())) {
//                return joinPoint.proceed();
//            } else {
//                Stopwatch sw = Stopwatch.begin();
//                Object result = null;
//                Object[] beforeInfo = executeTemplateFreeMarker(joinPoint);
//                try {
//                    sw.start();
//                    result = joinPoint.proceed();
//                    sw.stop();
//                    try {
//                        if (isNeedLog(joinPoint)) {
//                            autoDealLogs(sw.getDuration(), result, beforeInfo, joinPoint, null);
//                        }
//                    } catch (Throwable e1) {
//                        log.error(e1.getMessage(), e1);
//                    }
//                } catch (Throwable e) {
//                    sw.stop();
//                    try {
//                        autoDealLogs(sw.getDuration(), result, beforeInfo, joinPoint, e);
//                    } catch (Throwable e1) {
//                        log.error(e1.getMessage(), e1);
//                    }
//                    throw e;
//                }
//                return result;
//            }
        }
    }

    @SuppressWarnings("unchecked")
    private void autoDealLogs(long duration, Object result, Object[] beforeInfo, ProceedingJoinPoint joinPoint,
            Throwable e) {
//        Class<?> clzz = joinPoint.getTarget().getClass();
//        SysLog<?> operatelog = makeSysLog();
//
//        Mirror<?> cmirror = Mirror.me(clzz);
//        if (cmirror.isOf(RcController.class)) {
//            RcController bc = (RcController) joinPoint.getTarget();
//            Method method = getMethod(joinPoint);
//            AppVisitor av = (AppVisitor) beforeInfo[0];
//            if (av == null || Strings.isBlank(av.getOperatorId())) {
//                av = getAppVisitor(joinPoint, method, bc);
//                if (av == null) {
//                    av = getAppVisitorByUnloginedUser(joinPoint, method, bc);
//                }
//            }
//            operatelog.setOperator(av);
//
//            RcLog logAnnot = method.getAnnotation(RcLog.class);
//
//            operatelog.setModule(getModuleName(clzz));
//            operatelog.setOperation(logAnnot.operation());
//            operatelog.setOperationType(logAnnot.type().getCode());
//            operatelog.setClassName(clzz.getName());
//            operatelog.setMethodName(joinPoint.getSignature().getName());
//            // executeTemplateFreeMarker(logAnnot.desc(), joinPoint, operatelog, av);
//            operatelog.setParameters((String) beforeInfo[1]);
//            operatelog.setOperateDesc((String) beforeInfo[2]);
//
//            operatelog.setIp(WebUtils.getIpAddress(bc.getHttpRequest()));
//            operatelog.setUrl(bc.getHttpRequest().getRequestURI());
//            operatelog.setOperateTime(new Date());
//            operatelog.setCostTime(duration);
//            operatelog.setOperateResult(e == null ? ILog.SUCCESS : ILog.FAILURE);
//            operatelog.setResult(getMethodExcuteResult(method, result));
//            if (e != null) {
//                operatelog.setError(JSON.toJSONString(e, true));
//                log.error(JSON.toJSONString(operatelog, true));
//            }else {
//            	log.info(JSON.toJSONString(operatelog, true));
//            }
//
//            if (operateLogService != null && logAnnot.db()) {
//                operatelog.fitFieldLength();
//                operateLogService.insert(av, (SysLog<Serializable>) operatelog);
//            }
//        }
    }



    private String getMethodExcuteResult(Method method, Object result) {
        // if (method.isAnnotationPresent(ResponseBody.class)) {
        // ResponseBody rb = method.getAnnotation(ResponseBody.class);
        // if (rb != null) {
        // return "AJAX请求，返回JSON对象。";
        // }
        // }
        if (result != null) {
            // return Castors.me().castToString(result);
            return JSON.toJSONString(result, true);
        }
        return null;
    }

    /**
     * 判断是否需要记录此日志
     * 
     * @param joinPoint
     * @return
     */
    private boolean isNeedLog(ProceedingJoinPoint joinPoint) {
        LogLevel level = getLogLevl();
        if (level.equals(LogLevel.ALL)) {
            return true;
        } else if (level.equals(LogLevel.NONE)) {
            return false;
        } else {
            Method method = getMethod(joinPoint);
            Log logAnnot = method.getAnnotation(Log.class);
            OperationType type = logAnnot.type();
            if (level.equals(LogLevel.UPDATE)) {
                if (type.getCode().intValue() >= OperationType.ADD.getCode().intValue()) {
                    return true;
                }
            }
        }
        return false;
    }






    private int logLevl;
    private ISuperService logService;


    public LogLevel getLogLevl() {
        LogLevel ll = LogLevel.get(logLevl);
        if (ll != null) {
            return ll;
        } else {
            return LogLevel.ALL;
        }
    }

    public void setLogLevl(int logLevl) {
        this.logLevl = logLevl;
    }

}
