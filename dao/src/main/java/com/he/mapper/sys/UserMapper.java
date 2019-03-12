package com.he.mapper.sys;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.he.mapper.SuperMapper;
import com.he.entity.sys.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<SysUser> {

    /**
     * 注解 @SqlParser(filter = true) 过滤多租户解析
     * @return
     */
    @SqlParser(filter = true)
    @Select("select id, username, birthday, sex,address from user")
    List<SysUser> selectListBySQL();

}