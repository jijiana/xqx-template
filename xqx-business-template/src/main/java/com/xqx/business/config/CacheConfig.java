package com.xqx.business.config;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.CacheLoader;

/**
 * 缓存配置
 */
@Configuration
public class CacheConfig {

	 /**
     * 必须要指定这个Bean，refreshAfterWrite=5s这个配置属性才生效
     *
     * @return
     */
    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
     
        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
     
            @Override
            public Object load(Object key) throws Exception {
                return null;
            }
     
            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue;
            }
        };
        return cacheLoader;
    }
    
    /**
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
				System.out.println("keyname === "+sb.toString());
				return sb.toString();
			}
		};
	}
}
