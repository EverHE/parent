package com.he.datasource.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 *  注入公共字段自动填充,任选注入方式即可
 */
//@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createTime";
    private final static String CREATE_USERID = "createUserId";
    private final static String UPDATE_TIME = "updateTime";
    private final static String UPDATE_USERID = "updateUserId";

    private final static Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("新增的时候干点不可描述的事情");

        setFieldValueByName(CREATE_TIME,new Date(),metaObject);//mybatis-plus版本2.0.9+
        setFieldValueByName(CREATE_USERID,1l,metaObject);//mybatis-plus版本2.0.9+

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新的时候干点不可描述的事情");
        setFieldValueByName(UPDATE_TIME,new Date(),metaObject);//mybatis-plus版本2.0.9+
        setFieldValueByName(UPDATE_USERID,1l,metaObject);//mybatis-plus版本2.0.9+
    }

    private void setFieldValueByName(String fieldName,Object fieldValue,MetaObject metaObject){
        Object updateTime = getFieldValByName(fieldName, metaObject);
        if (updateTime == null) {
            setFieldValByName(fieldName, fieldValue, metaObject);
        }
    }
}
