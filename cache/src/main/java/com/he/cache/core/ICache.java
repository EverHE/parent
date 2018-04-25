package com.he.cache.core;

import java.util.Collection;
import java.util.Map;

/**
 * cahce接口
 * @param <KeyType>
 */
public interface ICache<KeyType> {
    /**
     * 获取缓存对象
     * @param key
     * @return
     */
    Object get(KeyType key);

    /**
     * 批量获取缓存对象
     * @param keys
     * @return
     */
    Map<KeyType, Object> get(Collection<KeyType> keys);

    /**
     * 判断缓存是否存在
     * @param key
     * @return
     */
    default boolean exists(KeyType key) {
        return get(key) != null;
    }

    /**
     * 插入缓存数据
     * @param key
     * @param value
     */
    void put(KeyType key, Object value);

    /**
     * 批量插入数据
     * @param elements
     */
    void put(Map<KeyType, Object> elements);

    /**
     * 获取所有key值
     * @return 返回键的集合
     */
    Collection<KeyType> keys();

    /**
     * 从缓存中删除数据
     * @param keys 不定参数，key1,key2,key3...
     */
    void evict(KeyType...keys);

    /**
     * 清空缓存
     */
    void clear();
}
