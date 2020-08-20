package com.module.zhupeng.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CacheConfig
 * <p>date : 2019-11-23</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
@Configuration
public class ModuleCacheConfig {

    @Autowired
    @Qualifier(value = CacheConstant.MANAGER_NAME)
    EhCacheCacheManager ehCacheManager;

    @Bean(CacheConstant.SYSTEM_BEAN)
    public EhCacheBean getCacheSystem() {
        return new EhCacheBean(ehCacheManager, CacheConstant.SYSTEM);
    }

    @Bean(CacheConstant.SESSION_BEAN)
    public EhCacheBean getCacheSession() {
        return new EhCacheBean(ehCacheManager, CacheConstant.SESSION);
    }

    @Bean(CacheConstant.FILES_BEAN)
    public EhCacheBean getCacheFiles() {
        return new EhCacheBean(ehCacheManager, CacheConstant.FILES);
    }

    @Bean(CacheConstant.REPOSITORY_BEAN)
    public EhCacheBean getCacheRepository() {
        return new EhCacheBean(ehCacheManager, CacheConstant.REPOSITORY);
    }

    @Bean(CacheConstant.SERVICE_BEAN)
    public EhCacheBean getCacheService() {
        return new EhCacheBean(ehCacheManager, CacheConstant.SERVICE);
    }

    @Bean(CacheConstant.I18N_BEAN)
    public EhCacheBean getCacheLanguage() {
        return new EhCacheBean(ehCacheManager, CacheConstant.I18N);
    }


}
