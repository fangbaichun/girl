package com.fbc.ihrm.system.dao;

import com.fbc.ihrm.entity.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission, String>,
        JpaSpecificationExecutor<Permission> {
    List<Permission> findByTypeAndPid(int type, String pid);
}
