package com.he.syslog.enums;

public enum OperationType implements EnumBase<Integer> {
    GET(1, "查看"), ADD(100, "添加"), UPDATE(101, "修改"), SAVE(102, "保存"), DELETE(103, "删除"), IMPORT(104, "导入"),
    EXPORT(2, "导出"), PRINT(3, "打印"), LOGIN(110, "登录"), LOGOUT(111, "登出");

    private int code;
    private String message;

    OperationType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public static OperationType get(Integer code) {
        for (OperationType ul : values()) {
            if (ul.getCode().equals(code)) {
                return ul;
            }
        }
        return null;
    }

}
