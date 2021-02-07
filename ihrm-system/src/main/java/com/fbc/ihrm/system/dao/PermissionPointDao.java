package com.fbc.ihrm.system.dao;

import com.fbc.ihrm.entity.system.PermissionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionPointDao extends JpaRepository<PermissionPoint, String>,
        JpaSpecificationExecutor<PermissionPoint> {
}
