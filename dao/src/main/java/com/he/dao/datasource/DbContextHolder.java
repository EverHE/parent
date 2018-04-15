package com.he.dao.datasource;

import com.he.dao.datasource.enums.DBTypeEnum;

/**
 * 用来设置或获取线程相关的数据源别名
 */
public class DbContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = ThreadLocal.withInitial(() -> DBTypeEnum.MASTER);

    /**
     * 设置数据源
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static DBTypeEnum getDbType() {
        return contextHolder.get();
    }

    /**
     * 重置
     */
    public static void reset(){
        contextHolder.set(DBTypeEnum.MASTER);
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}