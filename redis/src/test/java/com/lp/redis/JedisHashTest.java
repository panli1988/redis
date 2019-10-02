package com.lp.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisHashTest {

	private Jedis jedis;

	@Before
	public void init() {
		/*
		 * 此处的IP需要和redis.conf中配置的保持一致
		 * protect-mode no
		 */
		jedis = new Jedis("192.168.29.128",6379);
		String ping = jedis.ping();
	}
	
	@Test
	public void hset() {
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "张三");
		hash.put("age", "26");
		Long hset = jedis.hset("zs", hash);
		System.out.println(hset);
	}
	
	@Test
	public void hget() {
		String hget = jedis.hget("zs", "name");
		System.out.println(hget);
	}
	
	@Test
	public void hgetAll() {
		Map<String, String> hgetAll = jedis.hgetAll("zs");
		System.out.println(hgetAll);
	}
	
	@Test
	public void mhset() {
		Map<String, String> hash= new HashMap<String, String>();
		hash.put("name", "lisi");
		hash.put("age", "28");
		String hmset = jedis.hmset("ls", hash);
		System.out.println(hmset);
	}
	
	@Test
	public void mhget() {
		List<String> hmget = jedis.hmget("ls", "name","age");
		System.out.println(hmget);
	}
}
