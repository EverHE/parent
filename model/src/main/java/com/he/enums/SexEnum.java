package com.he.enums;

import java.io.Serializable;

public enum SexEnum implements BaseEnum {
    SEX_MALE(0,"男性"),
    SEX_FEMALE(1,"女性"),
    SEX_UNKNOWN(2,"未知");

    private int value;
    private String desc;

    SexEnum(final int value, final String desc) {
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
