package com.lp.redis;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisSetTest {

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
	public void sadd() {
		Long sadd = jedis.sadd("citys", "nanjing","wuxi","xuzhou");
		System.out.println(sadd);
	}
	
	@Test
	public void srandmember() {
		String string = jedis.srandmember("citys");
		System.out.println(string);
		List<String> srandmember = jedis.srandmember("citys", 10);
		System.out.println(srandmember);
	}
}
