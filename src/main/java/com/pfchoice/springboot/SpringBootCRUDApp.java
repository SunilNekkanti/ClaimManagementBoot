package com.pfchoice.springboot;


import org.apache.commons.logging.LogFactory;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.pfchoice.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = { "com.pfchoice.springboot" }) // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableAsync
@EnableCaching
public class SpringBootCRUDApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCRUDApp.class, args);
	}
	
	 @Bean
	    public CacheManager cacheManager() {
	        long ttl = Long.parseLong("300");

	        double pctOfHeap = Double.valueOf("0.4");
	        long cacheSizeMB = new Double(Runtime.getRuntime().maxMemory() * pctOfHeap / 1048576.0).longValue();

	        LogFactory.getLog(getClass()).info(
	                String.format("Initializing cache TTL=%d secs, size=%d MB (%.2f percent of max heap)",
	                        ttl, cacheSizeMB, pctOfHeap * 100));

	        org.ehcache.config.CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
	                .newCacheConfigurationBuilder(Object.class, Object.class,
	                        ResourcePoolsBuilder.newResourcePoolsBuilder()
	                                .heap(cacheSizeMB, MemoryUnit.MB))
	                .withExpiry(Expirations.timeToLiveExpiration(new org.ehcache.expiry.Duration(ttl, TimeUnit.SECONDS)))
	                .build();

	        Map<String, CacheConfiguration<?, ?>> caches = new HashMap<>();
	        caches.put("claims", cacheConfiguration);

	        EhcacheCachingProvider provider = (EhcacheCachingProvider) javax.cache.Caching.getCachingProvider();

	        // when our cacheManager bean is re-created several times for
	        // diff test configurations, this provider seems to hang on to state
	        // causing cache settings to not be right. so we always close().
	        provider.close();

	        DefaultConfiguration configuration = new DefaultConfiguration(
	                caches, provider.getDefaultClassLoader());

	        return new JCacheCacheManager(
	                provider.getCacheManager(provider.getDefaultURI(), configuration));
	    }
	    
	
}
