package com.he.cache.core;

import com.he.cache.enums.CacheLevelEnum;

import java.util.Collection;
import java.util.Properties;

/**
 * Support for pluggable caches.
 * @author Winter Lau(javayou@gmail.com)
 */
public interface CacheProvider {

	/**
	 * 缓存的标识名称
	 * @return return cache provider name
	 */
	String name();

	/**
	 * 缓存的层级
	 * @return
	 */
	CacheLevelEnum level();

	default boolean isLevel(CacheLevelEnum level) {
		//TODO ??
		//return (level() & level) == level;
		return level() == level;
	}
	
	/**
	 * Configure the cache
	 *
	 * @param regionName the name of the cache region
	 * @param listener listener for expired elements
	 * @return return cache instance
	 */
	ICache buildCache(String regionName, CacheExpiredListener listener);

	/**
	 * Configure the cache with timeToLiveInMills
	 * @param region cache region name
	 * @param timeToLiveInSeconds time to live in second
	 * @param listener listener for expired elements
	 * @return return cache instance
	 */
	ICache buildCache(String region, long timeToLiveInSeconds, CacheExpiredListener listener);

	/**
	 * Return all channels defined in first level cache
	 * @return
	 */
	Collection<CacheChannel.Region> regions();

	/**
	 * Callback to perform any necessary initialization of the underlying cache implementation
	 * during SessionFactory construction.
	 *
	 * @param props current configuration settings.
	 */
	void start(Properties props);

	/**
	 * Callback to perform any necessary cleanup of the underlying cache implementation
	 * during SessionFactory.close().
	 */
	void stop();

}
