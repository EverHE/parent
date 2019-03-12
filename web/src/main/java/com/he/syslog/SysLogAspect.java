package com.he.syslog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.alibaba.fastjson.JSON;
import com.he.syslog.enums.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.lang.reflect.Method;

public class SysLogAspect extends RcAspect {

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
//            if (operateLogService != null
//                    && Mirror.me(operateLogService.getClass()).is(joinPoint.getTarget().getClass())) {
//                return joinPoint.proceed();
//            } else {
                Object result = null;
                Object[] beforeInfo = executeTemplateFreeMarker(joinPoint);
                TimeInterval timer = DateUtil.timer();
                try {
                    result = joinPoint.proceed();
                    try {
                        if (isNeedLog(joinPoint)) {
                            autoDealLogs(timer.interval(), result, beforeInfo, joinPoint, null);
                        }
                    } catch (Throwable e1) {
                        log.error(e1.getMessage(), e1);
                    }
                } catch (Throwable e) {
                    try {
                        autoDealLogs(timer.interval(), result, beforeInfo, joinPoint, e);
                    } catch (Throwable e1) {
                        log.error(e1.getMessage(), e1);
                    }
                    throw e;
                }
                return result;
//            }
        }
    }

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

//    private AppVisitor getAppVisitorByUnloginedUser(ProceedingJoinPoint joinPoint, Method method, RcController bc) {
//        AppVisitor av = null;
//        RcLog logAnnot = method.getAnnotation(RcLog.class);
//        LogMode lm = logAnnot.mode();
//        if (lm.equals(LogMode.WEB)) {
//            // TODO
//        } else if (lm.equals(LogMode.FACE)) {
//            av = getAppVisitorByUnloginedUserFromFace(joinPoint);
//        } else if (lm.equals(LogMode.SERVICE)) {
//            // TODO
//        }
//        return av;
//    }

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
//            RcLog logAnnot = method.getAnnotation(RcLog.class);
//            OperationType type = logAnnot.type();
//            if (level.equals(LogLevel.UPDATE)) {
//                if (type.getCode().intValue() >= OperationType.ADD.getCode().intValue()) {
//                    return true;
//                }
//            }
        }
        return false;
    }

    /**
     * 解析执行SystemLog的value模板得到操作描述，同时取得方法参数和描述。
     * 
     */
    private Object[] executeTemplateFreeMarker(ProceedingJoinPoint joinPoint) {
        Object[] ret = new Object[3];
//        Class<?> clzz = joinPoint.getTarget().getClass();
//        Mirror<?> cmirror = Mirror.me(clzz);
//        if (cmirror.isOf(RcController.class)) {
//            RcController bc = (RcController) joinPoint.getTarget();
//            Method method = getMethod(joinPoint);
//            RcLog logAnnot = method.getAnnotation(RcLog.class);
//            AppVisitor av = getAppVisitor(joinPoint, method, bc);
//            ret[0] = av;
//
//            String[] parameterNames = parameterNameDiscovere.getParameterNames(method);
//            Object[] args = joinPoint.getArgs();
//
//            if (args != null && args.length > 0) {
//                List<Object> margs = new ArrayList<Object>();
//                for (Object arg : args) {
//                    if (arg == null || arg instanceof ServletRequest || arg instanceof HttpSession
//                            || arg instanceof ServletResponse) {
//                        //
//                    } else {
//                        margs.add(arg);
//                    }
//                }
//                ret[1] = JSON.toJSONString(margs, true);
//            }
//
//            if (isForbidFreeMarkerParser()) {
//                ret[2] = "";
//            } else if (!Strings.isBlank(logAnnot.desc())) {
//                Configuration configuration = freeMarkerConfigurer.getConfiguration();
//                configuration.setTemplateLoader(stringTemplateLoader);
//                stringTemplateLoader.addTemplate(logAnnot.desc(), logAnnot.desc());
//                StringWriter writer = new StringWriter();
//                try {
//                    Template freeMarkerTemplate = configuration.getTemplate(logAnnot.desc());
//                    Map<String, Object> context = new HashMap<String, Object>();
//                    for (int i = 0; i < parameterNames.length; i++) {
//                        context.put(parameterNames[i], args[i]);
//                    }
//                    if (av != null) {
//                        context.put("operator", av);
//                    }
//                    freeMarkerTemplate.process(context, writer);
//                } catch (IOException e) {
//                    log.error(e.getMessage(), e);
//                } catch (TemplateException e) {
//                    log.error(e.getMessage(), e);
//                }
//                String desc = writer.toString();
//                ret[2] = desc;
//            }
//
//        }
        return ret;
    }

//    private AppVisitor getAppVisitor(ProceedingJoinPoint joinPoint, Method method, RcController bc) {
//        AppVisitor av = null;
//        RcLog logAnnot = method.getAnnotation(RcLog.class);
//        LogMode lm = logAnnot.mode();
//        if (lm.equals(LogMode.WEB)) {
//            av = bc.getAppVisitor();
//        } else if (lm.equals(LogMode.FACE)) {
//            av = getAppVisitorFromFace(joinPoint);
//        } else if (lm.equals(LogMode.SERVICE)) {
//            av = getAppVisitorFromService(joinPoint);
//        }
//        return av;
//    }
//
//    private String getModuleName(Class<?> cl) {
//        if (cl.isAnnotationPresent(Module.class)) {
//            Module m = cl.getAnnotation(Module.class);
//            if (m != null) {
//                return m.name();
//            }
//        }
//        return null;
//    }

//    private class RcSysLog extends SysLog<String> {
//        private static final long serialVersionUID = -4707746643026485209L;
//
//        public RcSysLog() {
//            super();
//        }
//    }
//
//    private SysLog<?> makeSysLog() {
//        if (operateLogService != null) {
//            return Mirror.me(operateLogService.getEntityClass()).born();
//        } else {
//            return new RcSysLog();
//        }
//    }

    private FreeMarkerConfigurer freeMarkerConfigurer;
    private FreeMarkerConfigurationFactoryBean freeMarkerConfiguration;
//    private StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();

    private int logLevl;
    private boolean forbidFreeMarkerParser;
//    private IService<Serializable, SysLog<Serializable>> operateLogService;
//
//    public IService<Serializable, SysLog<Serializable>> getOperateLogService() {
//        return operateLogService;
//    }

//    public void setOperateLogService(IService<Serializable, SysLog<Serializable>> operateLogService) {
//        this.operateLogService = operateLogService;
//    }

    public boolean isForbidFreeMarkerParser() {
        return forbidFreeMarkerParser;
    }

    public void setForbidFreeMarkerParser(boolean forbidFreeMarkerParser) {
        this.forbidFreeMarkerParser = forbidFreeMarkerParser;
    }

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

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        return freeMarkerConfiguration;
    }

    public void setFreeMarkerConfiguration(FreeMarkerConfigurationFactoryBean freeMarkerConfiguration) {
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

}
