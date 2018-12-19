package com.gaia.configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;


@Configuration
public class CacheConfigurtion {

	
	@Bean
	public CacheManager cacheManager(Ticker ticker) {
	    CaffeineCache otpCache = buildCache("otp", ticker, 30);
	    SimpleCacheManager manager = new SimpleCacheManager();
	    manager.setCaches(Arrays.asList(otpCache));
	    return manager;
	}
	 
	private CaffeineCache buildCache(String name, Ticker ticker, int minutesToExpire) {
	    return new CaffeineCache(name, Caffeine.newBuilder()
	                .expireAfterWrite(minutesToExpire, TimeUnit.MINUTES)
	                .maximumSize(100)
	                .ticker(ticker)
	                .build());
	}
	 
	@Bean
	public Ticker ticker() {
	    return Ticker.systemTicker();
	}
	 

	 
}
