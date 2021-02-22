package com.fbc.ihrm.system.controller;

import com.fbc.girl.common.response.*;
import com.fbc.ihrm.entity.system.Role;
import com.fbc.ihrm.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//1.解决跨域
@CrossOrigin
@RestController
@RequestMapping("/sys")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    //添加角色
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Result add(@RequestBody Role role) {
        role.setCompanyId(parseCompanyId());
        roleService.save(role);
        return Result.SUCCESS();
    }

    //更新角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Role role) {
        roleService.update(role);
        return Result.SUCCESS();
    }

    //删除角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) {
        roleService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID获取角色信息
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(name = "id") String id) {
        Role role = roleService.findById(id);
        return new QueryResult(CommonCode.SUCCESS, role);
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page, int size, @RequestParam Map<String, String> map) {
        Page<Role> searchPage = roleService.findSearch(parseCompanyId(), page, size, map);
        return new QueryResult(CommonCode.SUCCESS, new ResultPage(searchPage.getContent(), searchPage.getTotalElements()));
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public Result findAll() {
        List<Role> list = roleService.findAll(parseCompanyId());
        return new QueryResult(CommonCode.SUCCESS, list);
    }

    /**
     * 分配权限
     */
    @RequestMapping(value = "/role/assignPrem", method = RequestMethod.PUT)
    public Result assignPrem(@RequestBody Map<String, Object> map) {
        //1.获取被分配的角色的id
        String roleId = (String) map.get("id");
        //2.获取到权限的id列表
        List<String> permIds = (List<String>) map.get("permIds");
        //3.调用service完成权限分配
        roleService.assignPerms(roleId, permIds);
        return new Result(CommonCode.SUCCESS);
    }
}
