package com.he.dao.datasource.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.he.dao.DaoApplication;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  注入公共字段自动填充,任选注入方式即可
 */
//@Component
public class MyMetaObjectHandler extends MetaObjectHandler {

    private final static Logger logger = LoggerFactory.getLogger(DaoApplication.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+
        System.out.println("createTime=" + createTime);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        }
        Object createUserId = getFieldValByName("createUserId", metaObject);//mybatis-plus版本2.0.9+
        System.out.println("createUserId=" + createUserId);
        if (createUserId == null) {
            setFieldValByName("createUserId", 1l, metaObject);//mybatis-plus版本2.0.9+
        }
        logger.info("新增的时候干点不可描述的事情");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新的时候干点不可描述的事情");
    }
}
