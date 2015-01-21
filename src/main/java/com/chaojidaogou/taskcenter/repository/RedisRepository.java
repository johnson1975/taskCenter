package com.chaojidaogou.taskcenter.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/20.
 */
@Component(value = "redisRepository")
public class RedisRepository {
    @Inject
    private RedisTemplate<String, String> stringRedisTemplate;

    public void add(String key, String value, double score) {
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    public void remove(String key, Long value) {
        stringRedisTemplate.opsForZSet().remove(key, value);
    }

    public Set<ZSetOperations.TypedTuple<String>> members(String key) {
        return stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, 0, -1);
    }

    public Long count(String key) {
        return stringRedisTemplate.opsForZSet().size(key);
    }

    public Long increment(String key) {
        return stringRedisTemplate.opsForValue().increment(key, 1l);
    }
}
