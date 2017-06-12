package com.huaao.sunejwapi.common.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class RedisTool {

	/**
	 * 获取自增id
	 * @param tableName 表名
	 * @param factory
	 * @return
	 */
	public static long generatorId(String tableName, RedisConnectionFactory factory){
		RedisAtomicLong atomicLong = new RedisAtomicLong(tableName, factory);
		return atomicLong.incrementAndGet();
	}
	
}
