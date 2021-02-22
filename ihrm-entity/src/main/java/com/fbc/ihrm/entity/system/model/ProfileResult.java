package com.fbc.ihrm.entity.system.model;

import com.fbc.ihrm.entity.system.Permission;
import com.fbc.ihrm.entity.system.Role;
import com.fbc.ihrm.entity.system.User;
import com.fbc.ihrm.entity.system.constants.UserConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProfileResult {
    private String mobile;
    private String username;
    private String company;
    private Map perms;
    private List<String> roles = new ArrayList<>();

    public ProfileResult(User user) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        //角色和权限数据
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        Map rolesMap = new HashMap<>();
        for (Role role : user.getRoles()) {
            this.roles.add(role.getName());
            for (Permission perm : role.getPermissions()) {
                String code = perm.getCode();
                if (perm.getType() == 1) {
                    menus.add(code);
                } else if (perm.getType() == 2) {
                    points.add(code);
                } else {
                    apis.add(code);
                }
            }
        }
        rolesMap.put("menus", menus);
        rolesMap.put("points", points);
        rolesMap.put("apis", points);
        this.perms = rolesMap;
    }

    public ProfileResult(User user, List<Permission> list) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        //角色和权限数据

        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        Map rolesMap = new HashMap<>();
        this.roles.addAll(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        for (Permission perm : list) {
            String code = perm.getCode();
            if (perm.getType() == 1) {
                menus.add(code);
            } else if (perm.getType() == 2) {
                points.add(code);
            } else {
                apis.add(code);
            }
        }
        rolesMap.put("menus", menus);
        rolesMap.put("points", points);
        rolesMap.put("apis", points);
        this.perms = rolesMap;
    }
}
