package com.he.dao.datasource.interceptor;

import com.he.dao.datasource.DbContextHolder;
import com.he.dao.datasource.enums.DBTypeEnum;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceInterceptor {
    private Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);

    //表示匹配com.he.dao.mapper.biz包及其子包下的所有方法
    @Pointcut("execution(* com.he.dao.mapper.biz..*(..))")
    private void datasource1ServicePointcut() {
    }

    @Pointcut("execution(* com.he.dao.mapper.sys..*(..))")
    private void datasource2ServicePointcut() {
    }

    //TODO 待修改。需要将aop切换数据源的时机改到service
    /**
     * 切换数据源是不能放在 dao层的, 除非你不加事物,或者把事物放在dao层.
     * 如果按照正常的把事物放在service层, 通过aop 扫描mapper层切换数据源是不会成功的 ,因为切换数据源是在事物之前执行的.
     * 而实际中肯定要加事物,并且事物肯定是放在service层的.
     */

    /**
     * 切换数据源1
     */
    @Before("datasource1ServicePointcut()")
    public void dataSource1Interceptor() {
        logger.debug("切换到数据源{}..............................", "datasource1");
        DbContextHolder.setDbType(DBTypeEnum.datasource1);
    }

    /**
     * 切换数据源2
     */
    @Before("datasource2ServicePointcut()")
    public void dataSource2Interceptor() {
        logger.debug("切换到数据源{}.......................", "datasource2");
        DbContextHolder.setDbType(DBTypeEnum.datasource2);
    }
}