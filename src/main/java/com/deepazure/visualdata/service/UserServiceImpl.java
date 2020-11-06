package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.TrainHistory;
import com.deepazure.visualdata.entity.User;
import com.deepazure.visualdata.repository.TrainHistoryRepository;
import com.deepazure.visualdata.repository.UserRepository;
import com.deepazure.visualdata.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    private final TrainHistoryRepository trainHistoryRepository;

    @Value("${spring.application.token-expire}")
    private long expire;

    public UserServiceImpl(UserRepository userRepository, TrainHistoryRepository trainHistoryRepository) {
        this.userRepository = userRepository;
        this.trainHistoryRepository = trainHistoryRepository;
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public JsonResult login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            JsonResult.error("用户名或密码不能为空");
        }
        var user = userRepository.findByUsername(username);
        if (user == null) {
            return JsonResult.error("用户名不存在");
        }
        if (!user.getPassword().equals(password)) {
            return JsonResult.error("密码错误");
        }
        user.setAccessToken(UUID.randomUUID().toString());
        user.setExpire(new Date(System.currentTimeMillis() + expire));
        user = userRepository.saveAndFlush(user);
        user.setPassword(null);
        return JsonResult.success("登陆成功", user);
    }

    @Override
    public JsonResult login(String accessToken) {
        if (StringUtils.isEmpty(accessToken)) {
            return JsonResult.error("无效的令牌，请重新登录");
        }
        User user = userRepository.findByAccessToken(accessToken);
        if (user == null) {
            return JsonResult.error("令牌认证异常，请重新登录");
        }
        if (user.getExpire().compareTo(new Date(System.currentTimeMillis())) < 0) {
            return JsonResult.error("令牌已过期，请重新登录");
        }
        user.setPassword(null);
        return JsonResult.success(user);
    }

    @Override
    public User findByAccessToken(String accessToken) {
        if (StringUtils.isEmpty(accessToken)) {
            log.warn("access_token should not be null or empty.");
        }
        return userRepository.findByAccessToken(accessToken);
    }

    @Override
    public JsonResult logout(String accessToken) {
        User user = userRepository.findByAccessToken(accessToken);
        if (user != null) {
            user.setAccessToken(null);
            user.setExpire(null);
            userRepository.save(user);
        }
        return JsonResult.success("注销完成");
    }

    @Override
    public void beforeSave(User item) {
        if (StringUtils.isEmpty(item.getUsername())) {
            throw new IllegalArgumentException("用户名不能为空");
        }
    }

    @Override
    public void beforeDelete(Long id) {
        TrainHistory history = new TrainHistory();
        history.setUserId(id);
        List<TrainHistory> histories = trainHistoryRepository.findAll(Example.of(history));
        if (histories.size() == 0) return;
        for (TrainHistory his: histories) {
            trainHistoryRepository.delete(his);
        }
    }
}
