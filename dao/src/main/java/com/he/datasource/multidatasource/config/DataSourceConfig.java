package com.he.datasource.multidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.he.datasource.multidatasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;
import java.util.HashMap;

/**
 * spring 多数据源切换 AbstractRoutingDataSource
 */
//@Configuration
//@MapperScan(value = {"com.he.dao.mapper.sys*","com.he.dao.mapper.biz*"})
//@MapperScan(value = {"com.zhangshuo.test1.mapper", "com.zhangshuo.test2.mapper"})
public class DataSourceConfig {

    @ConfigurationProperties("spring.datasource")
    @Bean(name = "master")
    public DruidDataSource master() {
        return new DruidDataSource();
    }

    @ConfigurationProperties("spring.datasource2")
    @Bean(name = "slave")
    public DruidDataSource slave() {
        return new DruidDataSource();
    }

    @Bean(name = "datasource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier(value = "master") DataSource master,
                                               @Qualifier(value = "slave") DataSource slave) {
        DynamicDataSource bean = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("MASTER", master);
        targetDataSources.put("SLAVE", slave);
        bean.setTargetDataSources(targetDataSources);
        bean.setDefaultTargetDataSource(slave);
        return bean;
    }

    @Bean(name = "sessionFactory1")
    @ConfigurationProperties(prefix = "mybatis-plus")
    @ConfigurationPropertiesBinding()
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactory1(@Qualifier(value = "datasource") DataSource dataSource) {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

//    @Bean(name = "sessionFactory2")
//    @ConfigurationProperties(prefix = "mybatis-plus2")
//    @ConfigurationPropertiesBinding()
//    public MybatisSqlSessionFactoryBean sqlSessionFactory2(@Qualifier(value = "datasource") DataSource dataSource) {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean;
//    }
}