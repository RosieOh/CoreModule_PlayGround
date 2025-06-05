package com.core.common.model.modules;

import java.util.Arrays;

import com.core.common.model.ModuleInfo;

public class CacheModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Cache",
            Arrays.asList(
                "메모리 캐시 관리",
                "캐시 만료 정책",
                "캐시 통계",
                "분산 캐시 지원",
                "캐시 무효화",
                "캐시 동기화",
                "캐시 모니터링"
            ),
            """
            // 1. 캐시 설정
            @Configuration
            @EnableCaching
            public class CacheConfig {
                @Bean
                public CacheManager cacheManager() {
                    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
                    cacheManager.setCaffeine(Caffeine.newBuilder()
                        .expireAfterWrite(60, TimeUnit.MINUTES)
                        .maximumSize(100));
                    return cacheManager;
                }
            }
            
            // 2. 캐시 사용
            @Service
            public class UserService {
                @Cacheable(value = "users", key = "#id")
                public User getUser(Long id) {
                    return userRepository.findById(id);
                }
                
                @CacheEvict(value = "users", key = "#id")
                public void updateUser(Long id, User user) {
                    userRepository.save(user);
                }
            }
            
            // 3. Redis 캐시 설정
            @Configuration
            public class RedisConfig {
                @Bean
                public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
                    RedisTemplate<String, Object> template = new RedisTemplate<>();
                    template.setConnectionFactory(factory);
                    template.setKeySerializer(new StringRedisSerializer());
                    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
                    return template;
                }
            }
            
            // 4. 캐시 동기화
            @Service
            public class CacheSyncService {
                @CachePut(value = "users", key = "#user.id")
                public User syncUser(User user) {
                    return userRepository.save(user);
                }
            }
            
            // 5. 캐시 모니터링
            @Service
            public class CacheMonitorService {
                public CacheStats getCacheStats(String cacheName) {
                    return cacheManager.getCache(cacheName).getNativeCache();
                }
            }
            """,
            """
            # Cache 설정
            cache.type=memory
            cache.expiration=3600
            cache.max-size=1000
            cache.redis.host=localhost
            cache.redis.port=6379
            cache.redis.password=
            cache.redis.database=0
            cache.sync.enabled=true
            cache.monitor.enabled=true
            cache.stats.enabled=true
            """,
            Arrays.asList("logging"),
            Arrays.asList(
                "캐시 크기는 메모리 사용량을 고려하여 설정해야 합니다.",
                "캐시 만료 시간은 데이터 특성에 맞게 설정해야 합니다.",
                "분산 환경에서는 Redis와 같은 분산 캐시를 사용해야 합니다.",
                "캐시 무효화 전략을 신중하게 설계해야 합니다.",
                "캐시 히트율을 모니터링하고 최적화해야 합니다.",
                "캐시 동기화는 일관성을 유지하도록 구현해야 합니다.",
                "캐시 모니터링을 통해 성능 문제를 조기에 발견해야 합니다."
            )
        );
    }
} 