package com.module.zhupeng.cache;

import com.module.zhupeng.util.SpringContextUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import java.util.Objects;

/**
 * AbsBaseCache
 * <p>date : 2019-11-19</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class EhCacheBean {

    public EhCacheBean(EhCacheCacheManager ehCacheManager, String cacheName) {
        this.cacheName = cacheName;
        this.ehCache = Objects.requireNonNull(ehCacheManager.getCacheManager()).getCache(cacheName);
    }

    private String cacheName;
    private Cache ehCache;

    public String getCacheName() {
        return cacheName;
    }

    public Cache getEhCache() {
        return ehCache;
    }

    public Object get(String key) {
        Element element = this.ehCache.get(key);
        return element != null ? element.getObjectValue() : null;
    }

    public <T> T get(String key, Class<T> type) {
        Object value = get(key);
        if (value != null && type != null && !type.isInstance(value)) {
            return null;
        } else {
            return (T) value;
        }
    }

    public void put(String key, Object value) {
        this.ehCache.put(new Element(key, value));
    }

    public void remove(String key) {
        this.ehCache.remove(key);
    }

    public void clear() {
        this.ehCache.removeAll();
    }

    public void evictExpiredElements() {
        this.ehCache.evictExpiredElements();
    }

    public static EhCacheBean getInstance(String cacheBeanName) {
        return SpringContextUtil.getBean(cacheBeanName, EhCacheBean.class);
    }

    public static EhCacheBean getNewBean(String cacheName) {
        EhCacheCacheManager ehCacheCacheManager = SpringContextUtil.getBean(CacheConstant.MANAGER_NAME, EhCacheCacheManager.class);
        return new EhCacheBean(ehCacheCacheManager, cacheName);
    }
}
