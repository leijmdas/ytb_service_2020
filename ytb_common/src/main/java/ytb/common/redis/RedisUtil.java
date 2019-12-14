package ytb.common.redis;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
	
	private RedisUtil() {
		
	}
	public static class Singleton{
		static RedisUtil instance;

		public static void instanceRedisTemplate(RedisTemplate<String,String> tpl) {
			instance = new RedisUtil(tpl);
		}
	}
	public static <T> RedisUtil getInstance() {
		return Singleton.instance;
	}
	public RedisUtil(RedisTemplate<String, String> tpl) {
		this.redisTemplate = tpl;
	}
	private RedisTemplate<String,String> redisTemplate;
    
	public void set(String key,String val) {
		BoundValueOperations<String,String> opt = redisTemplate.boundValueOps(key);
		opt.set(val);
	}
	/**
	 * 
	 * @param key
	 * @param val
	 * @param time s
	 */
	public void set(String key,String val,long time) {
		BoundValueOperations<String,String> opt = redisTemplate.boundValueOps(key);
		if(time<0) {
			opt.set(val);
			return ;
		}
		redisTemplate.boundValueOps(key).set(val, time, TimeUnit.SECONDS);
	}

	public Boolean setnx(String key, String val) {
		BoundValueOperations<String,String> opt = redisTemplate.boundValueOps(key);
		return opt.setIfAbsent(val);
	}

	public Map<Object,Object> getHash(String key) {
		BoundHashOperations<String, Object, Object> hashOpt = this.redisTemplate.boundHashOps(key);
		Map<Object,Object> obj = hashOpt.entries();
		return obj;
	}
	public String getValue(String key) {
		return redisTemplate.boundValueOps(key).get();
	}
	/**
	 * 设置Hash值
	 * @param key
	 * @param propName
	 * @param propValue
	 * @param
	 */
	public void putHash(String key, String propName, Object propValue) {
		BoundHashOperations<String, Object, Object> hashOpt = this.redisTemplate.boundHashOps(key);
		hashOpt.put(propName, propValue);

	}
	public void putHashs(String key, Map<Object,Object> values) {
		BoundHashOperations<String, Object, Object> hashOpt = this.redisTemplate.boundHashOps(key);
		hashOpt.putAll(values);
	}
	public BoundHashOperations<String,Object,Object> initHash(String key, long timeout) {
		BoundHashOperations<String, Object, Object> hashOpt = this.redisTemplate.boundHashOps(key);
		if(timeout > 0 ) {
			hashOpt.expire(timeout, TimeUnit.SECONDS);
		}
		return hashOpt;
	}
	public Object getHash(String key, String propName) {
		BoundHashOperations<String, Object, Object> hashOpt = this.redisTemplate.boundHashOps(key);
		Object obj = hashOpt.get(propName);
		return obj;
	}
	

	public void delete(String key) {
		this.redisTemplate.delete(key);
	}
	/**
	 * 设置超时
	 * @param key
	 * @param timeoutSeconds
	 */
	public void expireValue(String key,Long timeoutSeconds) {
		this.redisTemplate.boundValueOps(key).expire(timeoutSeconds, TimeUnit.SECONDS);
	}
	/**
	 * 获取超时时间
	 * @param key
	 * @return 超时时间（秒）
	 */
	public Long getExpireValue(String key) {
		return this.redisTemplate.boundValueOps(key).getExpire();
	}

	public RedisTemplate<String, String> cloneRedisTemplate() {
		RedisTemplate<String, String> tpl = new RedisTemplate<String, String>();
		tpl.setConnectionFactory(this.redisTemplate.getConnectionFactory());
		tpl.setDefaultSerializer(this.redisTemplate.getDefaultSerializer());
		tpl.setHashKeySerializer(this.redisTemplate.getHashKeySerializer());
		tpl.setHashValueSerializer(this.redisTemplate.getHashValueSerializer());
		tpl.setStringSerializer(this.redisTemplate.getStringSerializer());
		tpl.setValueSerializer(this.redisTemplate.getValueSerializer());
		tpl.afterPropertiesSet();
		return tpl;
	}

	/**
	 * 发送消息
	 * @param channel
	 * @param message
	 */
	public void convertAndSend(String channel,String message) {
		
		this.redisTemplate.convertAndSend(channel, message);
	}

	public boolean hasKey(String key) {
		return this.redisTemplate.hasKey(key);
	}

	public Boolean expire(String key, long timeout) {
		BoundValueOperations<String,String> opt = redisTemplate.boundValueOps(key);
		return opt.expire(timeout, TimeUnit.SECONDS);
	}

	public Long getExpire(String key) {
		BoundValueOperations<String,String> opt = redisTemplate.boundValueOps(key);
		return opt.getExpire();
	}

	public long incr(String key, long delta) {
		BoundValueOperations<String,String> opt = redisTemplate.boundValueOps(key);
		return opt.increment(delta);
	}
}
