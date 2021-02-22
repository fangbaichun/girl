package com.fbc.ihrm.system.controller;

import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.QueryResult;
import com.fbc.girl.common.response.Result;
import com.fbc.girl.common.response.ResultPage;
import com.fbc.ihrm.entity.system.User;
import com.fbc.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//1.解决跨域
@CrossOrigin
@RestController
@RequestMapping("/sys")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    //保存用户
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        user.setCompanyId(parseCompanyId());
        user.setCompanyName(parseCompanyName());
        userService.save(user);
        return Result.SUCCESS();
    }

    /**
     * 分配角色
     */
    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String, Object> map) {
        //1.获取被分配的用户id
        String userId = (String) map.get("id");
        //2.获取到角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        //3.调用service完成角色分配
        userService.assignRoles(userId, roleIds);
        return new Result(CommonCode.SUCCESS);
    }

    //更新用户
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody User user) {
        userService.update(user);
        return Result.SUCCESS();
    }

    //删除用户
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) {
        userService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID查询用户
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(name = "id") String id) {
        User user = userService.findById(id);
        return new QueryResult(CommonCode.SUCCESS, user);
    }

    /**
     * 分页查询用户
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result findByPage(int page, int size, @RequestParam Map<String, Object>
            map) {
        map.put("companyId", parseCompanyId());
        Page<User> searchPage = userService.findSearch(map, page, size);
        return new QueryResult(CommonCode.SUCCESS, new ResultPage(searchPage.getContent(), searchPage.getTotalElements()));
    }

}
