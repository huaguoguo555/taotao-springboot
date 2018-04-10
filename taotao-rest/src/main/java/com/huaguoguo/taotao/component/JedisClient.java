package com.huaguoguo.taotao.component;
/**   
 * @Title: JedisClient.java 
 * @Package com.taotao.rest.component 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月24日 上午11:12:39 
 * @version V1.0   
 */
public interface JedisClient {

	String get(String key);
	void set(String key, String value);
	void hset(String hkey, String key, String value);
	String hget(String hkey, String key);
	void del(String key);
	Long hdel(String hkey, String key);
	void expire(String key, int second);
}
