package ytb.common.redis;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Bean
	@ConfigurationProperties(prefix="redis")
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		return jedisPoolConfig;
	}
	
    @SuppressWarnings("deprecation")
	@Bean
    @ConfigurationProperties(prefix="redis")
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
        factory.setUsePool(true);   
        factory.setPoolConfig(jedisPoolConfig());
        
        return factory; 
    }
	/**
	 * 设置数据存入 redis 的序列化方式,并开启事务
	 * 
	 * @param redisTemplate
	 * @param factory
	 */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		// 如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to
		// String！
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		 开启事务
//		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setConnectionFactory(factory);
	}

	/**
	 * 实例化 RedisTemplate 对象
	 *
	 * @return
	 */
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
	}
	
	
	@Bean(name = "stringRedisTemplate")
	public StringRedisTemplate sringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setStringSerializer(stringSerializer);
        redisTemplate.setDefaultSerializer(stringSerializer);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}


}
