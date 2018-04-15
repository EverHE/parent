package com.he.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.he.model.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SuperEntity<PK>{
    private PK id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private PK createUserId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private PK updateUserId;
    private StatusEnum status;
}
