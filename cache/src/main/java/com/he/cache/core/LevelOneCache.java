package com.he.cache.core;

public interface LevelOneCache extends ICache {
    /**
     *返回该缓存区域的TTL设置(单位:秒)
     * @return
     */
    long ttl();

    /**
     *返回该缓存区域中,内存存储对象的最大数量
     * @return
     */
    long size();
}
