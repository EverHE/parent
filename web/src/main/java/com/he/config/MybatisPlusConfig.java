package com.he.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.he.datasource.handler.MyMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.he.mapper.*")
//@MapperScan({"com.he.dao.mapper.sys*","com.he.dao.mapper.biz*"})
public class MybatisPlusConfig {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    //@Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }

    /**
     * 乐观锁插件，需要实体对象有@version
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor OptimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        /*
//         * 【测试多租户】 SQL 解析处理拦截器<br>
//         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
//         */
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId() {
//                return new LongValue(1L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                // 这里可以判断是否过滤表
//                /*
//                if ("user".equals(tableName)) {
//                    return true;
//                }*/
//                return false;
//            }
//        });
//
//
//        sqlParserList.add(tenantSqlParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);

        // 以下过滤方式与 @SqlParser(filter = true) 注解等效
//        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//            @Override
//            public boolean doFilter(MetaObject metaObject) {
//                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
//                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
//                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
//                    return true;
//                }
//                return false;
//            }
//        });
        return paginationInterceptor;
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MyMetaObjectHandler();
    }

    /**
     * 注入主键生成器
     */
//    //@Bean
//    public IKeyGenerator keyGenerator(){
//        return new H2KeyGenerator();
//    }

    /**
     * 注入sql注入器
     */
    //@Bean
//    public ISqlInjector sqlInjector(){
//        return new LogicSqlInjector();
//    }

//    /**
//     * druid注入
//     */
//    @Bean
//    @ConfigurationProperties("spring.datasource" )
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
