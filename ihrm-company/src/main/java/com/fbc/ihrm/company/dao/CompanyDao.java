package com.fbc.ihrm.company.dao;

import com.fbc.ihrm.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *企业数据访问接口
 */
public interface CompanyDao extends JpaRepository<Company, String>, JpaSpecificationExecutor<Company> {
}
