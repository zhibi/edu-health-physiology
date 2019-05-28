package edu.health.service.impl;

import edu.health.core.base.service.BaseServiceImpl;
import edu.health.domain.User;
import edu.health.mapper.UserMapper;
import edu.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 执笔
 * @date 2019/5/28 18:07
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        return userMapper.selectOne(user);
    }
}
