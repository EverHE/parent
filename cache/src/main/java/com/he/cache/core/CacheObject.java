package com.he.cache.core;

import com.he.cache.enums.CacheLevelEnum;

public class CacheObject<KeyType> {
    private String region;
    private KeyType key;
    private Object value;
    private CacheLevelEnum level;

    public CacheObject(String region, KeyType key, Object value, CacheLevelEnum level) {
        this.region = region;
        this.key = key;
        this.value = value;
        this.level = level;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public KeyType getKey() {
        return key;
    }

    public void setKey(KeyType key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CacheLevelEnum getLevel() {
        return level;
    }

    public void setLevel(CacheLevelEnum level) {
        this.level = level;
    }
}
