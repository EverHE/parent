package com.he.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum StatusEnum implements IEnum {
    STATUS_NORMAL(0,"正常"),
    STATUS_DELETE(1,"删除");

    private int value;
    private String desc;

    StatusEnum(final int value,final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue(){
        return this.value;
    }

    public Serializable getDesc(){
        return this.desc;
    }
}
