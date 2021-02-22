package com.fbc.frame.gateway.service;

import com.fbc.frame.gateway.dao.UserDao;
import com.fbc.ihrm.entity.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByMobileAndPassword(String mobile, String password) {
        User user = userDao.findByMobile(mobile);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 根据ID查询用户
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }
}
