package com.lp.redis;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisListTest {

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
	public void lpush() {
		Long lpush = jedis.lpush("language", "java","PHP");
		System.out.println(lpush);
	}
	
	@Test
	public void pop() {
		String lpop = jedis.lpop("language");
		System.out.println(lpop);
		String rpop = jedis.rpop("language");
		System.out.println(rpop);
	}
	
	@Test
	public void lrange() {
		List<String> lrange = jedis.lrange("language", 0, 10);
		System.out.println(lrange);
	}
}
