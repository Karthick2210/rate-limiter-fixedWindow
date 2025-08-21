package com.assignment.rateLimiter.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisTestController {
    private final StringRedisTemplate redisTemplate;
    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @GetMapping("/set")
    public String setKey(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Saved key=" + key + " value=" + value;
    }
    @GetMapping("/get")
    public String getKey(@RequestParam String key) {
        return "Value=" + redisTemplate.opsForValue().get(key);
    }
}