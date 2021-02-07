package com.fbc.ihrm.company.controller;

import com.fbc.girl.common.pageable.PageArg;
import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.QueryResult;
import com.fbc.girl.common.response.Result;
import com.fbc.ihrm.company.service.CompanyService;
import com.fbc.ihrm.entity.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 添加企业
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Company company) throws Exception {
        companyService.add(company);
        return new Result(CommonCode.SUCCESS);
    }

    /**
     * 根据id更新企业信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Company
            company) throws Exception {
        Company one = companyService.findById(id);
        one.setName(company.getName());
        one.setRemarks(company.getRemarks());
        one.setState(company.getState());
        one.setAuditState(company.getAuditState());
        companyService.update(one);
        return new Result(CommonCode.SUCCESS);
    }

    /**
     * 根据id删除企业信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        companyService.deleteById(id);
        return new Result(CommonCode.SUCCESS);
    }

    /**
     * 根据ID获取公司信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(name = "id") String id) throws Exception {
        Company company = companyService.findById(id);
        return new QueryResult(CommonCode.SUCCESS,company);
    }

    /**
     * 分页获取企业列表
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public QueryResult findPage(PageArg pageArg,Company company) throws Exception {
        List<Company> companyList = companyService.findAll();
        return new QueryResult(CommonCode.SUCCESS,companyList);
    }

    /**
     * 获取企业列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public QueryResult findAll() throws Exception {
        List<Company> companyList = companyService.findAll();
        return new QueryResult(CommonCode.SUCCESS,companyList);
    }
}
