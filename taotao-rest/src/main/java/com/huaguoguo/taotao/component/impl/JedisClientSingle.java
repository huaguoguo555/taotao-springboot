package com.huaguoguo.taotao.component.impl;

import com.huaguoguo.taotao.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;


/**
 * @Title: JedisClientSingle.java 
 * @Package com.taotao.rest.component.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月24日 上午11:18:24 
 * @version V1.0   
 */
@Component
public class JedisClientSingle implements JedisClient {

	@Autowired
	private RedisTemplate<String,String> stringRedisTemplate;

	public String get(String key) {
		String result = stringRedisTemplate.opsForValue().get(key);
		return result;
	}

	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key,value);
	}



	public void hset(String hkey, String key, String value) {
		stringRedisTemplate.opsForHash().put(hkey,key,value);
	}

	public String hget(String hkey, String key) {
		return ((String) stringRedisTemplate.opsForHash().get(hkey, key));
	}



	public void del(String key) {
		stringRedisTemplate.delete(key);
	}

	public Long hdel(String hkey, String key) {
		Long result = stringRedisTemplate.opsForHash().delete(hkey, key);
		return result;
	}

	public void expire(String key, int second) {
		stringRedisTemplate.expire(key,second, TimeUnit.SECONDS);
	}




}
