package com.he.dao.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.he.dao.datasource.DynamicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;
import java.util.HashMap;


//@Configuration
//@MapperScan(value = {"com.he.dao.mapper.sys*","com.he.dao.mapper.biz*"})
//@MapperScan(value = {"com.zhangshuo.test1.mapper", "com.zhangshuo.test2.mapper"})
public class DataSourceConfig {

    @ConfigurationProperties("spring.datasource")
    @Bean(name = "datasource1")
    public DruidDataSource dataSource1() {
        return new DruidDataSource();
    }

    @ConfigurationProperties("spring.datasource2")
    @Bean(name = "datasource2")
    public DruidDataSource dataSource2() {
        return new DruidDataSource();
    }

    @Bean(name = "datasource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier(value = "datasource1") DataSource dataSource1,
                                               @Qualifier(value = "datasource2") DataSource dataSource2) {
        DynamicDataSource bean = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("datasource1", dataSource1);
        targetDataSources.put("datasource2", dataSource2);
        bean.setTargetDataSources(targetDataSources);
        bean.setDefaultTargetDataSource(dataSource1);
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