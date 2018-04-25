package com.he.rediscache.redis;

import com.he.cache.core.*;
import com.he.cache.enums.CacheLevelEnum;
import com.he.cache.exception.CacheException;
import net.oschina.j2cache.caffeine.CaffeineProvider;
import net.oschina.j2cache.ehcache.EhCacheProvider;
import net.oschina.j2cache.ehcache.EhCacheProvider3;
import net.oschina.j2cache.redis.RedisCacheProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 两级的缓存管理器
 * @author Winter Lau(javayou@gmail.com)
 */
public class RedisCacheProviderHolder {

	private final static Logger log = LoggerFactory.getLogger(RedisCacheProviderHolder.class);

	private static CacheProvider l1_provider;
	private static CacheProvider l2_provider;

	private static CacheExpiredListener listener;

	/**
	 * Initialize Cache Provider
	 * @param listener cache listener
	 */
	public static void init(J2CacheConfig config, CacheExpiredListener listener){
		CacheProviderHolder.listener = listener;
		CacheProviderHolder.l1_provider = loadProviderInstance(config.getL1CacheName());
		if (!l1_provider.isLevel(CacheLevelEnum.LEVEL_ONE))
			throw new CacheException(l1_provider.getClass().getName() + " is not level_1 cache provider");
		CacheProviderHolder.l1_provider.start(config.getL1CacheProperties());
		log.info("Using L1 CacheProvider : " + l1_provider.getClass().getName());

		CacheProviderHolder.l2_provider = loadProviderInstance(config.getL2CacheName());
		if (!l2_provider.isLevel(CacheLevelEnum.LEVEL_TWO))
			throw new CacheException(l2_provider.getClass().getName() + " is not level_2 cache provider");
		CacheProviderHolder.l2_provider.start(config.getL2CacheProperties());
		log.info("Using L2 CacheProvider : " + l2_provider.getClass().getName());
	}

	/**
	 * 关闭缓存
	 */
	public final static void shutdown() {
		l1_provider.stop();
		l2_provider.stop();
	}

	private final static CacheProvider loadProviderInstance(String cacheIdent) {
		if("ehcache".equalsIgnoreCase(cacheIdent))
			return new EhCacheProvider();
		if("ehcache3".equalsIgnoreCase(cacheIdent))
			return new EhCacheProvider3();
		if("caffeine".equalsIgnoreCase(cacheIdent))
			return new CaffeineProvider();
		if("redis".equalsIgnoreCase(cacheIdent))
			return new RedisCacheProvider();
		if("none".equalsIgnoreCase(cacheIdent))
			return new NullCacheProvider();
		try {
			return (CacheProvider) Class.forName(cacheIdent).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new CacheException("Failed to initialize cache providers", e);
		}
	}

	public final static CacheProvider getL1Provider() {
		return l1_provider;
	}

	public final static CacheProvider getL2Provider() {
		return l2_provider;
	}

	/**
	 * 一级缓存实例
	 * @param region
	 * @return
	 */
	public final static LevelOneCache getLevel1Cache(String region) {
		//TODO get缓存对象为什么是创建
		return (LevelOneCache)l1_provider.buildCache(region, listener);
	}

	/**
	 * 一级缓存实例
	 * @param region
	 * @param timeToLiveSeconds
	 * @return
	 */
	public final static LevelOneCache getLevel1Cache(String region, long timeToLiveSeconds) {
		return (LevelOneCache)l1_provider.buildCache(region, timeToLiveSeconds, listener);
	}

	/**
	 * 二级缓存实例
	 * @param region
	 * @return
	 */
	public final static LevelTwoCache getLevel2Cache(String region) {
		return (LevelTwoCache)l2_provider.buildCache(region, listener);
	}

	public final static Collection<CacheChannel.Region> regions() {
		return l1_provider.regions();
	}

}
