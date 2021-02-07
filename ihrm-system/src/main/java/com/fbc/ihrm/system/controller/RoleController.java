package com.fbc.ihrm.system.controller;

import com.fbc.girl.common.response.*;
import com.fbc.ihrm.entity.system.Role;
import com.fbc.ihrm.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

//1.解决跨域
@CrossOrigin
@RestController
@RequestMapping("/sys")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    //添加角色
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Result add(@RequestBody Role role) throws Exception {
        String companyId = "1";
        role.setCompanyId(companyId);
        roleService.save(role);
        return Result.SUCCESS();
    }

    //更新角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Role role)
            throws Exception {
        roleService.update(role);
        return Result.SUCCESS();
    }

    //删除角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        roleService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID获取角色信息
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(name = "id") String id) throws Exception {
        Role role = roleService.findById(id);
        return new QueryResult(CommonCode.SUCCESS, role);
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page, int pagesize, @RequestParam Role role) throws Exception {
        Page<Role> searchPage = roleService.findSearch(parseCompanyId(), page, pagesize);
        return new QueryResult(CommonCode.SUCCESS, new ResultPage(searchPage.getContent(),searchPage.getTotalElements()));
    }
}
