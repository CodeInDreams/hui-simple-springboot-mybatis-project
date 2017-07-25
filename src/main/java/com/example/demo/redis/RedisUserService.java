package com.example.demo.redis;

import com.example.demo.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUserService implements IRedisService<Long, User> {

    private final RedisTemplate<String, User> redisUserTemplate;
    private static Logger logger = LogManager.getLogger(RedisUserService.class);
    private final String hash = User.class.toString();

    @Autowired(required = false)
    public RedisUserService(RedisTemplate<String, User> redisUserTemplate) {
        this.redisUserTemplate = redisUserTemplate;
    }

    @Override
    public void put(User user) {
        logger.debug("Putting "+ user.toString());
        redisUserTemplate.opsForHash().put(hash, user.getId(), user);
    }

    @Override
    public User get(Long id) {
        logger.debug("Getting user with id"+ id);
        return (User) redisUserTemplate.opsForHash().get(hash, id);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Putting user with id"+ id);
        redisUserTemplate.opsForHash().delete(hash, id);
    }

    @Override
    public void flushAll() {
        logger.info("Flush all");
        redisUserTemplate.getConnectionFactory().getConnection().flushAll();
    }

}
