package com.he.dao.mapper.sys;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.he.dao.mapper.SuperMapper;
import com.he.model.entity.sys.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User 表数据库控制层接口
 * @author HL
 */
@Repository
public interface UserMapper extends SuperMapper<SysUser,Long> {
    /**
     * 删除全部
     * @return
     */
    int deleteAll();

    /**
     * 注解 @SqlParser(filter = true) 过滤多租户解析
     * @return
     */
    @SqlParser(filter = true)
    @Select("select id, username, birthday, sex,address from user")
    List<SysUser> selectListBySQL();

}