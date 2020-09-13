package hello.goodgid.spring_template_cache_with_ehcache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class LocalCacheConfig {

    @Bean
    public EhCacheManagerFactoryBean cacheManager() {
        return new EhCacheManagerFactoryBean();
    }

    /*
    ## Default Value
    status = STATUS_UNINITIALISED
    eternal = false
    overflowToDisk = false
    maxEntriesLocalHeap = 1234 // Required value
    maxEntriesLocalDisk = 0
    memoryStoreEvictionPolicy = LRU
    timeToLiveSeconds = 0
    timeToIdleSeconds = 0
    persistence = none
    diskExpiryThreadIntervalSeconds = 120
    cacheEventListeners: ; orderedCacheEventListeners:
    maxBytesLocalHeap = 0
    overflowToOffHeap = false
    axBytesLocalOffHeap = 0
    maxBytesLocalDisk = 0
    pinned = false ]
     */

    @Bean
    public EhCacheCacheManager testEhCacheManager() {
        CacheManager cacheManager = cacheManager().getObject();
        cacheManager.addCache(getTempCacheConfig());
        return new EhCacheCacheManager(cacheManager);
    }

    private Cache getTempCacheConfig() {
        CacheConfiguration cacheConfiguration = new CacheConfiguration().name("tempConfig")
                                                                        .memoryStoreEvictionPolicy("LRU")
                                                                        .eternal(false)                 // if true, timeouts are ignored
                                                                        .maxEntriesLocalHeap(5000)
                                                                        .timeToIdleSeconds(0)           // total items that can be stored in cache
                                                                        .timeToLiveSeconds(30);
        return new Cache(cacheConfiguration);
    }

}