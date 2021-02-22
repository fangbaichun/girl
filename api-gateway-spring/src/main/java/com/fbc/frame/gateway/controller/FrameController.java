package com.fbc.frame.gateway.controller;

import com.fbc.frame.gateway.service.PermissionService;
import com.fbc.frame.gateway.service.UserService;
import com.fbc.frame.gateway.utils.JwtUtil;
import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.QueryResult;
import com.fbc.girl.common.response.Result;
import com.fbc.ihrm.entity.system.Permission;
import com.fbc.ihrm.entity.system.User;
import com.fbc.ihrm.entity.system.constants.UserConstants;
import com.fbc.ihrm.entity.system.model.ProfileResult;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class FrameController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/fallback")
    public String fallback() {
        return "fallback";
    }

    /**
     * 用户登录
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {
        return new Result();
//        String mobile = loginMap.get("mobile");
//        String password = loginMap.get("password");
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken uptoken = new
//                    UsernamePasswordToken(mobile, password);
//            subject.login(uptoken);
//            return new Result(new CustomerCode("登录成功"));
//        } catch (Exception e) {
//            return new Result(CommonCode.MOBILE_OR_PASSWORD_ERROR);
//        }
//        User user = userService.findByMobile(mobile);
//        //登录失败
//        if (user == null || !user.getPassword().equals(password)) {
//            return new Result(CommonCode.MOBILE_OR_PASSWORD_ERROR);
//        } else {
//            //登录成功
//            Map<String, Object> map = new HashMap<>();
//            map.put("companyId", user.getCompanyId());
//            map.put("companyName", user.getCompanyName());
//            map.put("username",user.getUsername());
//            String token = jwtUtil.createJwt(user.getId(), user.getUsername(), map);
//            map.put("token",token);
//            return new QueryResult(CommonCode.SUCCESS, map);
//        }
    }

    /**
     * 获取个人信息
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public Result profile(ServerHttpRequest request) throws Exception {
        //请求中获取key为Authorization的头信息
        String authorization = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new RuntimeException(CommonCode.UNAUTHENTICATED.message());
        }
        //前后端约定头信息内容以 Bearer+空格+token 形式组成
        String token = authorization.replace("Bearer ", "");
        //比较并获取claims
        Claims claims = jwtUtil.parseJwt(token);
        if (claims == null) {
            throw new RuntimeException(CommonCode.UNAUTHENTICATED.message());
        }
        String userId = claims.getId();
        User user = userService.findById(userId);
        ProfileResult result = null;
        if (UserConstants.LEVEL_USER.equals(user.getLevel())) {
            result = new ProfileResult(user);
        } else {
            Map map = new HashMap();
            if (UserConstants.LEVEL_ADMIN.equals(user.getLevel())) {
                map.put("enVisible", "1");
            }
            List<Permission> list = permissionService.findAll(new HashMap(0));
            result = new ProfileResult(user, list);
        }
        return new QueryResult(CommonCode.SUCCESS, result);
    }
}
