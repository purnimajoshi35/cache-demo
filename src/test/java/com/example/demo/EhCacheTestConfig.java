package com.example.demo;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;

@TestConfiguration
@EnableCaching
public class EhCacheTestConfig {
    @Bean
    public JCacheCacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration configuration = new MutableConfiguration();
        configuration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 5)));
        cacheManager.createCache("bookCache", configuration);
        System.out.println(provider);
        System.out.println(cacheManager);
        return new JCacheCacheManager(cacheManager);
    }
}
