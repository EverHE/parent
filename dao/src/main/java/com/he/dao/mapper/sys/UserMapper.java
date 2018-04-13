package com.he.dao.mapper.sys;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.he.dao.mapper.SuperMapper;
import com.he.model.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    /**
     * 注解 @SqlParser(filter = true) 过滤多租户解析
     */
    @SqlParser(filter = true)
    @Select("select id, username, birthday, sex,address from user")
    List<User> selectListBySQL();

}