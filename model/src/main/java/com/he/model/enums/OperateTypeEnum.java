package com.he.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 操作模块
 */
public enum OperateTypeEnum implements IEnum{
    CREATE(0,"新增"),
    MODIFY(1,"修改"),
    DELETE(2,"删除"),
    INTERFACE(3,"接口");

    private int value;
    private String desc;

    OperateTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    public Serializable getDesc(){
        return this.desc;
    }
}
