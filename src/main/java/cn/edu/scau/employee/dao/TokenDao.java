package cn.edu.scau.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 16:50
 */
@Component
public class TokenDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${spring.redis.token.prefix}")
    private String prefix;

    @Value("${spring.token.name}")
    private String tokenName;

    /**
     * 保存用户信息到redis中
     *
     * @param token
     * @param userInfo
     */
    public void saveUser(String token, String userInfo) {
        redisTemplate.opsForValue().set(prefix + token, userInfo, 30 * 60 * 1000, TimeUnit.MILLISECONDS);
    }

    /**
     *  根据token检查用户是否存在
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        String value = redisTemplate.opsForValue().get(prefix + token);
        if (null == value) {
            return false;
        }
        saveUser(token, value);
        return true;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    public String getUserInfoByToken(String token) {
        String value = redisTemplate.opsForValue().get(prefix + token);
        return value;
    }

    /**
     * 清理token
     * @param token
     */
    public void clearToken(String token) {
        Boolean hasKey = redisTemplate.hasKey(prefix + token);
        if (hasKey) {
            redisTemplate.delete(prefix + token);
        }
    }

    public String getTokenName() {
        return tokenName;
    }
}
