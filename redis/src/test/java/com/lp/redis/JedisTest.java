package com.lp.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
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
	public void set() {
		String string = jedis.set("name", "张三");
		System.out.println(string);
	}
	
	@Test
	public void get() {
		String name = jedis.get("name");
		System.out.println(name);
	}
	
	@Test
	public void setExpire() {
		/*
		 * 设置10秒超期
		 */
		String string = jedis.setex("age", 10, "12");
		System.out.println(string);
	}
	
	@Test
	public void getExpire() {
		String age = jedis.get("age");
		System.out.println(age);
	}
}
