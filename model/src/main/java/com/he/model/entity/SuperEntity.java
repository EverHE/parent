package com.he.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.he.model.enums.StatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SuperEntity<PK>{

    private PK id;

    /**
     * 乐观锁实现方式：
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = yourVersion+1 where version = yourVersion
     * 如果version不对，就更新失败
     */
    @JSONField(serialize = false)
    private Integer version;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //公共字段自动填充字段需要此注解
    @TableField(fill = FieldFill.INSERT)
    private PK createUserId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private PK updateUserId;

    @TableLogic//逻辑删除的字段注解
    private StatusEnum status;

    private Long sort;
}
