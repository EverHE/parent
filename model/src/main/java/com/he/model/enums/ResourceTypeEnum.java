package com.he.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum ResourceTypeEnum implements IEnum {
    RESOURCE_TYPE_MENU(1,"菜单"),
    RESOURCE_TYPE_BTN(0,"按钮");

    private int value;
    private String desc;

    ResourceTypeEnum(final int value, final String desc) {
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
