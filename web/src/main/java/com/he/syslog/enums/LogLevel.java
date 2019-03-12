package com.he.syslog.enums;

public enum LogLevel implements EnumBase<Integer> {
    NONE(0, "不记录日志"), ERROR(1, "记录错误日志"), UPDATE(2, "记录数据更新日志"), ALL(3, "记录所有日志");

    private int code;
    private String message;

    LogLevel(int code, String message) {
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

    public static LogLevel get(Integer code) {
        for (LogLevel ul : values()) {
            if (ul.getCode().equals(code)) {
                return ul;
            }
        }
        return null;
    }

}
