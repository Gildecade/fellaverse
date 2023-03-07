package com.fellaverse.backend;

import com.fellaverse.backend.bean.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ShopServer_7501.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void addValue() {
        redisTemplate.opsForValue().set("test", "Hello Redis!");
        System.out.println(redisTemplate.opsForValue().get("test"));
    }

    @Test
    public void addObject() {
        Role role = new Role(1L, "aaa", "aaaaa");
        redisTemplate.opsForValue().set("role", role);
        System.out.println(redisTemplate.opsForValue().get("role"));
    }
}
