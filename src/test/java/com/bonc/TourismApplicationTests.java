package com.bonc;


import com.bonc.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TourismApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
        redisService.set("wt","哈哈");
        System.out.println(redisService.get("wt"));
    }

}
