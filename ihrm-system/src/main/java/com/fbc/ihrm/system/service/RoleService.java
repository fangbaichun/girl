package com.fbc.ihrm.system.service;

import cn.hutool.core.lang.Snowflake;
import com.fbc.ihrm.entity.system.Permission;
import com.fbc.ihrm.entity.system.Role;
import com.fbc.ihrm.entity.system.constants.PermissionConstants;
import com.fbc.ihrm.system.dao.PermissionDao;
import com.fbc.ihrm.system.dao.RoleDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@SuppressWarnings("all")
public class RoleService {
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 添加角色
     */
    public void save(Role role) {
        //填充其他参数
        role.setId(snowflake.nextIdStr());
        roleDao.save(role);
    }

    /**
     * 更新角色
     */
    public void update(Role role) {
        Role targer = roleDao.getOne(role.getId());
        targer.setDescription(role.getDescription());
        targer.setName(role.getName());
        roleDao.save(targer);
    }

    /**
     * 根据ID查询角色
     * 北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-9090
     */
    public Role findById(String id) {
        return roleDao.findById(id).get();
    }

    /**
     * 删除角色
     */
    public void delete(String id) {
        roleDao.deleteById(id);
    }

    public Page<Role> findSearch(String companyId, int page, int size, Map<String, String> map) {
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                ArrayList<Predicate> list = new ArrayList();
                list.add(cb.equal(root.get("companyId").as(String.class), companyId));
                if (StringUtils.isNotBlank(map.get("name"))) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + map.get("name") + "%"));
                }
                Predicate[] p = list.toArray(new Predicate[list.size()]);
                return cb.and(p);
            }
        };
        return roleDao.findAll(specification, PageRequest.of(page - 1, size));
    }

    public List<Role> findAll(String parseCompanyId) {
        return roleDao.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class), parseCompanyId);
            }
        });
    }

    public void assignPerms(String roleId, List<String> permIds) {
        //1.获取分配的角色对象
        Role role = roleDao.findById(roleId).get();
        //2.构造角色的权限集合
        Set<Permission> perms = new HashSet<>();
        for (String permId : permIds) {
            Permission permission = permissionDao.findById(permId).get();
            //需要根据父id和类型查询API权限列表
            List<Permission> apiList =
                    permissionDao.findByTypeAndPid(PermissionConstants.PERMISSION_API, permission.getId());
            perms.addAll(apiList);//自定赋予API权限
            perms.add(permission);//当前菜单或按钮的权限
        }
        System.out.println(perms.size());
        //3.设置角色和权限的关系
        role.setPermissions(perms);
        //4.更新角色
        roleDao.save(role);
    }
}
