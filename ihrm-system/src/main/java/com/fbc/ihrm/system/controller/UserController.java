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
    public Result add(@RequestBody User user) throws Exception {
        user.setCompanyId(parseCompanyId());
        user.setCompanyName(parseCompanyName());
        userService.save(user);
        return Result.SUCCESS();
    }

    //更新用户
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody User user)
            throws Exception {
        userService.update(user);
        return Result.SUCCESS();
    }

    //删除用户
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        userService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID查询用户
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public QueryResult findById(@PathVariable(name = "id") String id) throws Exception {
        User user = userService.findById(id);
        return new QueryResult(CommonCode.SUCCESS, user);
    }

    /**
     * 分页查询用户
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public QueryResult findByPage(int page, int pagesize, @RequestParam Map<String, Object>
            map) throws Exception {
        map.put("companyId", parseCompanyId());
        Page<User> searchPage = userService.findSearch(map, page, pagesize);
        return new QueryResult(CommonCode.SUCCESS, new ResultPage(searchPage.getContent(),searchPage.getTotalElements()));
    }
}
