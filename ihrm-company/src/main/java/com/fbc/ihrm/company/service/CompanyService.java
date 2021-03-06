package com.fbc.ihrm.company.service;

import cn.hutool.core.lang.Snowflake;
import com.fbc.girl.common.pageable.PageArg;
import com.fbc.ihrm.company.dao.CompanyDao;
import com.fbc.ihrm.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    @SuppressWarnings("ALL")
    private Snowflake snowflake;

    /**
     * 添加企业
     *
     * @param company 企业信息
     */
    public Company add(Company company) {
        String id = snowflake.nextIdStr();
        company.setId(id);
        company.setCreateTime(new Date());
        company.setState(1);    //启用
        company.setAuditState("0"); //待审核
        company.setBalance(0d);
        return companyDao.save(company);
    }

    public Company update(Company company) {
        return companyDao.save(company);
    }

    public Company findById(String id) {
        return companyDao.findById(id).get();
    }

    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }

    public List<Company> findPage(Company company, PageArg pageArg) {
        return null;
    }
}
