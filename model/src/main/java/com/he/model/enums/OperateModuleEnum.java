package com.he.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 操作模块
 */
public enum OperateModuleEnum implements IEnum{
    SYS_MANAGER(1,"系统管理");

    private int value;
    private String desc;

    OperateModuleEnum(int value, String desc) {
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
