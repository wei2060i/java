package com.redis_study.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author sky
 * @date 2020/12/8 10:50
 * EnableConfigurationProperties作用: 使 使用@ConfigurationProperties 注解的类生效。
 */
@Configuration
@EnableCaching  //开启缓存功能
public class RedisConfig {

    @Value("${spring.cache.redis.time-to-live}")
    private Duration timeToLive;
    @Value("${spring.cache.redis.key-prefix}")
    private String keyPrefix;
    @Value("${spring.cache.redis.use-key-prefix}")
    private Boolean useKeyPrefix;
    @Value("${spring.cache.redis.cache-null-values}")
    private Boolean cacheNullValues;
    /**
     * redis key生成策略
     * target: 类
     * method: 方法
     * params: 参数
     * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
     * 如: @Cacheable(value = "key", keyGenerator = "cacheKeyGenerator")
     */
    @Bean
    public KeyGenerator cacheKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                //由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                //sb.append(Objects.requireNonNull(LnUtils.bean2Json(obj)).hashCode());
            }
            return sb.toString();
        };
    }

    @Bean
    public KeyGenerator cacheKeyGeneratorLocalDate() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                //sb.append(Objects.requireNonNull(LnUtils.bean2Json(obj)).hashCode());
            }
            sb.append(LocalDate.now());
            return sb.toString();
        };
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 使用StringRedisSerializer来序列化和反序列化redis的key值
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer()))
                .entryTtl(timeToLive)
                .prefixCacheNameWith(keyPrefix);
        if (!useKeyPrefix) {
            config.disableKeyPrefix();
        }
        if (!cacheNullValues) {
            config.disableCachingNullValues();
        }
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    /**
     * redisTemplate.opsForValue();//操作字符串
     * redisTemplate.opsForHash();//操作hash
     * redisTemplate.opsForList();//操作list
     * redisTemplate.opsForSet();//操作set
     * redisTemplate.opsForZSet();//操作有序set
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        // value序列化方式采用jackson
        template.setValueSerializer(serializer());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(serializer());

        template.afterPropertiesSet();
        return template;
    }

    private Jackson2JsonRedisSerializer<Object> serializer() {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        return jackson2JsonRedisSerializer;
    }
}
