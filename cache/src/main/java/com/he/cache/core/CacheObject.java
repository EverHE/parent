package com.he.cache.core;

public class CacheObject<KeyType> {
    private String region;
    private KeyType key;
    private Object value;
    private byte level;
}
