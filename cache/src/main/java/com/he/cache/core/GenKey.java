package com.he.cache.core;

/**
 * 缓存key统一生产
 */
public class GenKey {
    public static String genKey(String key,String region){
        return key + '@' + region;
    }
}
