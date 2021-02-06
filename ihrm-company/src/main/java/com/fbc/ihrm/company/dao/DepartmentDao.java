package com.fbc.ihrm.company.dao;

import com.fbc.ihrm.entity.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 部门操作持久层
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, String>,
        JpaSpecificationExecutor<Department> {
}
