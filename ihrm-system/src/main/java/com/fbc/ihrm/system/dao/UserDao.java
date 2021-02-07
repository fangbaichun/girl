package com.fbc.ihrm.system.dao;

import com.fbc.ihrm.entity.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User, String>,
        JpaSpecificationExecutor<User> {
    User findByMobile(String mobile);
}
