package com.lp.redis;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisStringTest {

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
	
	/**
	 * 新增或更新key为city,value为nanjing
	 */
	@Test
	public void set() {
		String set = jedis.set("city", "nanjing");
		System.out.println(set);
	}
	
	@Test
	public void get() {
		String string = jedis.get("city");
		System.out.println(string);
	}
	
	/**
	 * 当key不存在时，设置value为无锡
	 * 0 失败 1成功
	 */
	@Test
	public void setne() {
		Long set = jedis.setnx("city", "wuxi");
		System.out.println(set);
	}
	
	/**
	 * 删除key
	 */
	@Test
	public void del() {
		Long del = jedis.del("city");
		System.out.println(del);
	}
	
	/**
	 * 多值
	 */
	@Test
	public void mset() {
		String mset = jedis.mset("AA","1111","BB","2222","CC","333");
		System.out.println(mset);
	}
	
	/**
	 * 多值获取
	 */
	@Test
	public void mget() {
		List<String> mget = jedis.mget("AA","BB","CC");
		System.out.println(mget);
	}
}
