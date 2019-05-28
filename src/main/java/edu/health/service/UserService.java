package edu.health.service;

import edu.health.core.base.service.BaseService;
import edu.health.domain.User;


public interface UserService extends BaseService<User> {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
