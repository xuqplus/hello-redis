package com.github.xuqplus2.hello.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("")
    public Object a(String k, String v) {
        redisTemplate.boundValueOps(k).set(v);
        return "ok";
    }

    @GetMapping("get")
    public Object get(String k) {
        return redisTemplate.boundValueOps(k).get();
    }
}
