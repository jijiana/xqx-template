package com.xqx.user.data.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 缓存配置类
 *
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
	@Autowired
	private Environment env;

	
	@Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
 
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
 
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
 
        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
	
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        
        // 默认过期时间
        Duration defaultTtl = Duration.ofSeconds(Long.parseLong(env.getProperty("spring.cache.redis.ttl","60")));
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(defaultTtl)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
        
        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames =  new HashSet<>();
        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        // 获取配置的缓存名称并根据配置设置每个缓存的过期时间
        String cacheNamesStr = env.getProperty("spring.cache.cache-names");
        if(cacheNamesStr != null) {
        	String[] cacheNamesArray = cacheNamesStr.split(",");
        	for (int i = 0; i < cacheNamesArray.length; i++) {
				String cacheName = cacheNamesArray[i];
				cacheNames.add(cacheName);
				String ttlStr = env.getProperty("spring.cache.redis.ttl","0");
				if("0".equals(ttlStr)) {
					configMap.put(cacheName, defaultConfig);
				}else {
					configMap.put(cacheName, defaultConfig.entryTtl(Duration.ofSeconds(Long.parseLong(ttlStr))));
				}
			}
        }

        // 使用自定义的缓存配置初始化一个cacheManager
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
        		.initialCacheNames(cacheNames)				// 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
        		.withInitialCacheConfigurations(configMap)
                .cacheDefaults(defaultConfig)				// 设置默认的缓存配置
                .build();
        return cacheManager;
    }
	


	/*
	 * 定义缓存数据 key 生成策略的bean 包名+类名+方法名+所有参数
	 */
	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append("_");
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append("_");
					sb.append(obj.toString());
				}
				//System.out.println("Key生成策略=========="+sb.toString());
				return sb.toString();
			}
		};
	}
}
