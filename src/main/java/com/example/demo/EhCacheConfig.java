package com.example.demo;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class EhCacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration configuration = new MutableConfiguration();
        configuration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 30)));
        cacheManager.createCache("bookCache", configuration);
        System.out.println(provider);
        System.out.println(cacheManager);
        return new JCacheCacheManager(cacheManager);
    }
}
