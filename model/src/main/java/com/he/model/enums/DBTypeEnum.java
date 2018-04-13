package com.he.model.enums;

/**
 * 数据库枚举
 */
public enum DBTypeEnum {
    datasource1("datasource1"),
    datasource2("datasource2");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}