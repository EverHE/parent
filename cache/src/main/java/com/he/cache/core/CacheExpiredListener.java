package com.he.cache.core;

/**
 * When cached data expired in ehcache, this listener will be invoked.
 *
 * @author Winter Lau(javayou@gmail.com)
 */
public interface CacheExpiredListener {

	void notifyElementExpired(String region, String key) ;

}
