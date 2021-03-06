package com.he.syslog.enums;

public enum LogMode implements EnumBase<Integer> {
    WEB(0, "WEB层日志"),
    FACE(1, "接口层日志"),
    SERVICE(2, "SERVICE层日志");

    private int code;
    private String message;

    LogMode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    public static LogMode get(Integer code) {
        for (LogMode ul : values()) {
            if (ul.getCode().equals(code)) {
                return ul;
            }
        }
        return null;
    }

}
