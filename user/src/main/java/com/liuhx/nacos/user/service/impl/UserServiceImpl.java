package com.liuhx.nacos.user.service.impl;

import com.liuhx.nacos.common.config.exception.CommonException;
import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.common.entity.vo.request.LoginRequestVo;
import com.liuhx.nacos.common.entity.vo.response.LoginResponseVo;
import com.liuhx.nacos.common.repo.UserRepository;
import com.liuhx.nacos.user.service.UserService;
import com.liuhx.nacos.user.utils.MD5Utils;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program data-cleansing
 * @description: 用户实现类
 * @author: liuhx
 * @create: 2020/07/13 12:55
 */
@Service
@RefreshScope
public class UserServiceImpl implements UserService {
    @Resource
    MongoTemplate mongoTemplate;
    @Resource
    UserRepository userRepository;
    @Value("${key.user.token}")
    private String userTokenKey;
    @Value("${key.user.id}")
    private String userIdKey;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * @param loginRequestVo
     * @return
     * @description: 登录功能
     */
    @Override
    public LoginResponseVo login(LoginRequestVo loginRequestVo) {
        // 根据电话查询用户并查看是否有该用户
        User user = userRepository.findByPhone(loginRequestVo.getPhone());
        if (user == null || user.getDisplay() == 2) {
            throw new CommonException(HttpStatus.OK, "10001", "账号不正确");
        }
        // 验证密码
        if (!MD5Utils.getMd5String(loginRequestVo.getPassword()).equals(user.getPassword())) {
            throw new CommonException(HttpStatus.OK, "10002", "密码不正确");
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        stringRedisTemplate.opsForValue().set(userTokenKey + token, user.getId(), 3, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(userIdKey + user.getId(), token, 3, TimeUnit.DAYS);
        LoginResponseVo loginResponseVo = LoginResponseVo.builder().auth(user.getAuth()).name(user.getName()).token(token).build();
        return loginResponseVo;
    }

    /**
     * @param userId
     * @param token
     * @return
     * @description 注销账户
     */
    @Override
    public Boolean loginOut(String userId, String token) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            if (!userId.equals(stringRedisTemplate.opsForValue().get(userTokenKey + token))) {
                throw new CommonException(HttpStatus.OK, "10008", "userId与token不符");
            }
            return stringRedisTemplate.delete(userTokenKey + token);
        } else {
            throw new CommonException(HttpStatus.OK, "10008", "userId与token不符");
        }
    }

    /**
     * @param token
     * @return
     * @description 根据token 获取user信息
     */
    @Override
    public User getToken(String token) {
        String userId = stringRedisTemplate.opsForValue().get(userTokenKey + token);
        User user = null;
        if (userId != null) {
            user = userRepository.findById(userId).get();
        }
        return user;
    }

    /**
     * @param user
     * @return
     * @description 新增用户
     */
    @Override
    public User addUser(User user) {
        User validUser = userRepository.findByPhone(user.getPhone());
        if (validUser != null) {
            throw new CommonException(HttpStatus.OK, "10003", "该用户已存在");
        }
        String password = MD5Utils.getMd5String("123456");
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(password);
        return userRepository.save(user);
    }

    /**
     * @param user
     * @return
     * @description 修改用户
     */
    @Override
    public int updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        if (user.getPassword() != null) {
            String password = MD5Utils.getMd5String(user.getPassword());
            user.setPassword(password);
            update.set("password", user.getPassword());
        }
        update.set("name", user.getName());
        update.set("phone", user.getPhone());
        update.set("auth", user.getAuth());
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    /**
     * @param id
     * @param userId
     * @return
     * @description 删除用户
     */
    @Override
    public int deleteUser(String id, String userId) {
        if (id.equals(userId)) {
            throw new CommonException(HttpStatus.OK, "10004", "你不能删除你自己");
        }
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("display", 2);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        String token = stringRedisTemplate.opsForValue().get(userIdKey + id);
        this.loginOut(id, token);
        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }

    /**
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     * @description 修改密码
     */
    @Override
    public int updatePassword(String userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).get();
        if (user.getPassword().equals(MD5Utils.getMd5String(oldPassword))) {
            Query query = new Query(Criteria.where("id").is(userId));
            Update update = new Update().set("password", MD5Utils.getMd5String(newPassword));
            UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
            if (result != null)
                return (int) result.getMatchedCount();
            else
                return 0;
        } else {
            return 3;
        }
    }

    /**
     * 查询用户
     *
     * @return
     */
    @Override
    public List<User> findUser() {
        return userRepository.findByDisplay(1);
    }
}
