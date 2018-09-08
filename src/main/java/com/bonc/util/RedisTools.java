/*
package com.bonc.util;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @Author:ChenZhiXiang
 * @Description: Redis工具类
 * @Date:Created in 11:35 2018/9/6
 * @Modified By:
 *//*

public class RedisTools {

    private Jedis jedis = JedisUtil.getInstance().getJedis("127.0.0.1", 6379);//定义jedis对象，用来操作redis
    Map<String, String> resultMap = new HashMap<>();//定义一个HashMap，放入传回给客户端的数据和一个token
    Map<String, String> jedisMap = new HashMap<>();//定义一个HashMap，放入我们想存储的数据
    jedisMap.put("accountId", loginAccount.getId().toString());
    jedisMap.put("accountName", loginAccount.getName().toString());//把账户ID、name等我们需要的信息存储在map中

    String token = Base58Helper.compressedUUID();//定义一个token字符串，Base58Helper.compressedUUID是自定义的生成的是唯一的字符串的方法
    jedis.hmset(token, jedisMap);//向redis中存入键值对，键名：token；值：jedisMap。即把我们想要的数据以map形式存入redis，标记为token。
    jedis.expire(token, 6000);//定义token的失效时间
}
*/
