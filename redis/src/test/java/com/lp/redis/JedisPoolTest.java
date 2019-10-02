package com.lp.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	
	private JedisPoolConfig config;
	private JedisPool pool;
	private Jedis jedis;
	
	
	@Before
	public void init() {
		JedisPoolConfig config = new JedisPoolConfig();
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(true);
		//是否启用后进先出, 默认true
		config.setLifo(true);
		//最大空闲连接数, 默认8个
		config.setMaxIdle(5);
		//最大连接数,默认8个
		config.setMaxTotal(10);
		//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
		config.setMaxWaitMillis(30000);
		//资源池中资源最小空闲时间(单位为毫秒)，达到此值后空闲资源将被移除  默认1800000毫秒(30分钟)
		config.setMinEvictableIdleTimeMillis(60000);
		//最小空闲连接数, 默认0
		config.setMinIdle(0);
		//每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		config.setNumTestsPerEvictionRun(3);
		//对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
		config.setSoftMinEvictableIdleTimeMillis(1800000);
		//在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(false);
		//是否开启空闲资源监测, 默认false
		config.setTestWhileIdle(true);
		//空闲资源的检测周期(单位为毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setTimeBetweenEvictionRunsMillis(-1);
		pool = new JedisPool("192.168.29.128");
	}
	
	@Test
	public void getResource() {
		jedis = pool.getResource();
		String string = jedis.ping();
		System.out.println(string);
	}
	
	@Test
	public void mset() {
		jedis = pool.getResource();
		String mset = jedis.mset("name","zs","age","12");
		System.out.println(mset);
	}

	@Test
	public void get() {
		jedis = pool.getResource();
		List<String> mget = jedis.mget("language");
		System.out.println(mget);
	}
	
	@Test
	public void hmset() {
		jedis = pool.getResource();
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "lisi");
		hash.put("age", "26");
		String mset = jedis.hmset("student", hash );
		System.out.println(mset);
	}
	@Test
	public void hmget() {
		jedis = pool.getResource();
		List<String> hmget = jedis.hmget("student", "name","age");
		System.out.println(hmget);
	}
}
