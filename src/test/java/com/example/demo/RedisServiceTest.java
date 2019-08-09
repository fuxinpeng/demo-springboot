package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author
 * @date 2019/8/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        redisTemplate.opsForValue().set("123456","kjdhgjhkjsdg");
        System.out.println(redisTemplate.opsForValue().get("123456"));
    }

    @Test
    public void testSet2(){
        stringRedisTemplate.opsForValue().set("654321","kjdhgjhkjsdg");
        System.out.println(stringRedisTemplate.opsForValue().get("654321"));
    }
}
