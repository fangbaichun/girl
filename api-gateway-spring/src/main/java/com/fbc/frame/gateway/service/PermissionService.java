package com.fbc.frame.gateway.service;

import com.fbc.frame.gateway.dao.PermissionDao;
import com.fbc.ihrm.entity.system.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionService {
    @Autowired
    private PermissionDao permissionDao;



    /**
     * 4.查询全部
     * type   : 查询全部权限列表type：0：菜单 + 按钮（权限点） 1：菜单2：按钮（权限点）3：API接 口
     * enVisible : 0：查询所有saas平台的最高权限，1：查询企业的权限
     * pid ：父id
     */
    public List<Permission> findAll(Map<String, String> map) {
        //1.需要查询条件
        Specification<Permission> spec = new Specification<Permission>() {
            /**
             * 动态拼接查询条件
             * @return
             */
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //根据父id查询
                if (!StringUtils.isEmpty(map.get("pid"))) {
                    list.add(criteriaBuilder.equal(root.get("pid").as(String.class),
                            (String) map.get("pid")));
                }
                //根据enVisible查询
                if (!StringUtils.isEmpty(map.get("enVisible"))) {

                    list.add(criteriaBuilder.equal(root.get("enVisible").as(String.class),
                            (String) map.get("enVisible")));
                }
                //根据类型 type
                if (!StringUtils.isEmpty(map.get("type"))) {
                    String ty = (String) map.get("type");
                    CriteriaBuilder.In<Object> in =
                            criteriaBuilder.in(root.get("type"));
                    if ("0".equals(ty)) {
                        in.value(1).value(2);
                    } else {
                        in.value(Integer.parseInt(ty));
                    }
                    list.add(in);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return permissionDao.findAll(spec);
    }
}
