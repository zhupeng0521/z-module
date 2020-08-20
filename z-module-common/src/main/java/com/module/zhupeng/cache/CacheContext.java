package com.module.zhupeng.cache;

import com.module.zhupeng.util.SpringContextUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;


/**
 * CacheContext
 * <p>date : 2020-01-10</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
@Component("dwCacheContext")
public class CacheContext {

    private CacheManager cacheManager = null;

    @Autowired
    public CacheContext(@Qualifier(CacheConstant.MANAGER_NAME) EhCacheCacheManager cacheCacheManager) {
        cacheManager = cacheCacheManager.getCacheManager();
    }

    public static CacheContext getInstance() {
        return SpringContextUtil.getBean("dwCacheContext", CacheContext.class);
    }

    public static Cache getEhcache(String cacheName) {
        return getInstance().getCache(cacheName);
    }

    /**
     * 获取缓存
     *
     * @param cacheName 缓存名称
     * @return
     */
    public Cache getCache(String cacheName) {
        if (null == cacheManager) {
            return null;
        }
        return cacheManager.getCache(cacheName);
    }

    /**
     * 新增缓存记录
     *
     * @param cacheName 缓存名称
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = getCache(cacheName);
        if (null != cache) {
            cache.put(new Element(key, value));
        }
    }

    /**
     * 删除缓存记录
     *
     * @param cacheName 缓存名称
     * @param key
     * @return
     */
    public boolean remove(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        if (null == cache) {
            return false;
        }
        return cache.remove(key);
    }

    /**
     * 删除全部缓存记录
     *
     * @param cacheName 缓存名称
     */
    public void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        if (null != cache) {
            cache.removeAll();
        }
    }

    /**
     * 获取缓存记录
     *
     * @param cacheName 缓存名称
     * @param key       key
     * @return 对象
     */
    public Object get(String cacheName, String key) {
        Cache cache = this.getCache(cacheName);
        if (null == cache) {
            return null;
        }
        Element cacheElement = cache.get(key);
        if (null == cacheElement) {
            return null;
        }
        return cacheElement.getObjectValue();
    }

}
