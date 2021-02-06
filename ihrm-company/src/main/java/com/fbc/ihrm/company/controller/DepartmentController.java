package com.fbc.ihrm.company.controller;

import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.QueryResult;
import com.fbc.girl.common.response.Result;
import com.fbc.girl.common.response.ResultData;
import com.fbc.ihrm.company.model.DeptListResult;
import com.fbc.ihrm.company.service.CompanyService;
import com.fbc.ihrm.company.service.DepartmentService;
import com.fbc.ihrm.entity.Company;
import com.fbc.ihrm.entity.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;

    /**
     * 添加部门
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Department department)  {
        department.setCompanyId(parseCompanyId());
        departmentService.save(department);
        return Result.SUCCESS();
    }

    /**
     * 修改部门信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Department
            department)  {
        department.setCompanyId(parseCompanyId());
        department.setId(id);
        departmentService.update(department);
        return Result.SUCCESS();
    }

    /**
     * 删除部门
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id)  {
        departmentService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(name = "id") String id) {
        Department department = departmentService.findById(id);
        return new QueryResult(CommonCode.SUCCESS, new ResultData(department));
    }

    /**
     * 组织架构列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public QueryResult findAll() {
        Company company = companyService.findById(parseCompanyId());
        List<Department> list = departmentService.findAll(parseCompanyId());
        return new QueryResult(CommonCode.SUCCESS, new ResultData(new DeptListResult(company, list)));
    }
}
