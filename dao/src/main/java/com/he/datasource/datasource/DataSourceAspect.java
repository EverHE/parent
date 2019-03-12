package com.he.datasource.datasource;

import com.he.datasource.datasource.annotation.DataSourceChange;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 有{@link DataSourceChange}注解的方法，调用时会切换到指定的数据源
 *
 */
@Aspect
@Component
public class DataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Around("@annotation(dataSourceChange)")
    public Object doAround(ProceedingJoinPoint pjp, DataSourceChange dataSourceChange) throws Throwable {
        Object retVal = null;
        boolean selectedDataSource = false;
        try {
            if (null != dataSourceChange) {
                selectedDataSource = true;
                if (dataSourceChange.slave()) {
                    DynamicDataSource.useSlave();
                } else {
                    DynamicDataSource.useMaster();
                }
            }
            retVal = pjp.proceed();
        } catch (Throwable e) {
            LOGGER.warn("数据源切换错误", e);
            throw e;
        } finally {
            if (selectedDataSource) {
                DynamicDataSource.reset();
            }
        }
        return retVal;
    }
}