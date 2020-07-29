package com.lijie.finalproject;

import com.lijie.finalproject.bean.Role;
import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.dao.roleDao;
import com.lijie.finalproject.service.loginService;
import com.lijie.finalproject.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class J2eeFinalProjectApplicationTests {

    @Autowired
    userService service;
    @Autowired
    roleDao rDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//key-value都是String的

    @Autowired
    RedisTemplate redisTemplate;//key-value 都是对象的

    //注入设定了JSON序列化的Redis操作实例
    @Autowired
    RedisTemplate<Object, Object> redisTemplateByJSON;
    /**
     *Redis常见的5大数据类型
     * String 字符串   list 列表   hash 散列    set 集合    zset 有序集合
     * 操作方法：分别对应5大数据类型
     *stringRedisTemplate.opsForValue();
     *stringRedisTemplate.opsForList();
     *stringRedisTemplate.opsForHash();
     *stringRedisTemplate.opsForSet();
     *stringRedisTemplate.opsForZSet();
     *
     */
    @Test
     void test(){

//        stringRedisTemplate.opsForValue().append("1","李杰");
        String s = stringRedisTemplate.opsForValue().get("1");
        System.out.println(s);

    }

    @Test
    void test2(){
        User user = service.getUserByName("TEST");
        System.out.println(user);
        redisTemplateByJSON.opsForList().leftPush("user",user);
    }

}
