package com.he.cache.core;

import com.he.cache.exception.CacheException;
import com.he.cache.util.SerializationUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface LevelTwoCache<KeyType> extends ICache<KeyType> {
    /**
     * 是否支持缓存 TTL 的设置
     * @return
     */
    default boolean supportTTL() {
        return false;
    }

    /**
     * 读取缓存数据字节数组
     * @param key
     * @return
     */
    byte[] getBytes(KeyType key);

    /**
     * 同时读取多个 Key
     * @param keys
     * @return
     */
    List<byte[]> getBytes(Collection<KeyType> keys);

    /**
     * 设置缓存数据字节数组
     * @param key
     * @param bytes
     */
    void setBytes(KeyType key, byte[] bytes);

    /**
     * 同时设置多个数据
     * @param bytes
     */
    void setBytes(Map<KeyType,byte[]> bytes);

    /**
     * 设置缓存数据字节数组（带有效期）
     * @param key
     * @param bytes
     * @param timeToLiveInSeconds
     */
    void setBytes(KeyType key, byte[] bytes, long timeToLiveInSeconds);

    /**
     * 批量设置带 TTL 的缓存数据
     * @param bytes
     * @param timeToLiveInSeconds
     */
    void setBytes(Map<KeyType,byte[]> bytes, long timeToLiveInSeconds);

    /**
     * 判断缓存数据是否存在
     * @param key cache key
     * @return
     */
    @Override
    default boolean exists(KeyType key) {
        return getBytes(key) != null;
    }

    @Override
    default Object get(KeyType key) {
        byte[] bytes = getBytes(key);
        try {
            return SerializationUtils.deserialize(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CacheException(e);
        }
    }

    @Override
    default Map<KeyType, Object> get(Collection<KeyType> keys) {
        Map<KeyType, Object> results = new HashMap<>();
        if(keys.size() > 0) {
            List<byte[]> bytes = getBytes(keys);
            int i = 0;
            try {
                for (KeyType key : keys)
                    results.put(key, SerializationUtils.deserialize(bytes.get(i++)));
            } catch (IOException e) {
                e.printStackTrace();
                throw new CacheException(e);
            }
        }
        return results;
    }

    @Override
    default void put(KeyType key, Object value) {
        try {
            setBytes(key, SerializationUtils.serialize(value));
        } catch (IOException e) {
            e.printStackTrace();
            throw new CacheException(e);
        }
    }

    /**
     * 设置缓存数据(带有效期)
     * @param key
     * @param value
     * @param timeToLiveInSeconds
     */
    default void put(KeyType key, Object value, long timeToLiveInSeconds) {
        try {
            setBytes(key, SerializationUtils.serialize(value), timeToLiveInSeconds);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CacheException(e);
        }
    }

    @Override
    default void put(Map<KeyType, Object> elements) {
        if(elements.size() > 0)
            setBytes(elements.entrySet().stream().collect(Collectors.toMap(p -> p.getKey(), p->SerializationUtils.serializeWithoutException(p.getValue()))));
    }

    default void put(Map<KeyType, Object> elements, long timeToLiveInSeconds) {
        if(elements.size() > 0)
            setBytes(elements.entrySet().stream().collect(Collectors.toMap(p -> p.getKey(), p->SerializationUtils.serializeWithoutException(p.getValue()))), timeToLiveInSeconds);
    }
}
