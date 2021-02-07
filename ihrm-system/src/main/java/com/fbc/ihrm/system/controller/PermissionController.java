package com.fbc.ihrm.system.controller;

import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.QueryResult;
import com.fbc.girl.common.response.Result;
import com.fbc.ihrm.entity.system.Permission;
import com.fbc.ihrm.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//1.解决跨域
@CrossOrigin
@RestController
@RequestMapping("/sys")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 保存
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public Result save(@RequestBody Map<String, Object> map) throws Exception {
        permissionService.save(map);
        return new Result(CommonCode.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody
            Map<String, Object> map) throws Exception {
        //构造id
        map.put("id", id);
        permissionService.update(map);
        return new Result(CommonCode.SUCCESS);
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) throws Exception {
        permissionService.deleteById(id);
        return new Result(CommonCode.SUCCESS);
    }

    /**
     * 查询列表
     */
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public QueryResult findAll(@RequestParam Map map) {
        List<Permission> list = permissionService.findAll(map);
        return new QueryResult(CommonCode.SUCCESS, list);
    }

    /**
     * 根据ID查询
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(value = "id") String id) throws Exception {
        Map map = permissionService.findById(id);
        return new QueryResult(CommonCode.SUCCESS, map);
    }
}
