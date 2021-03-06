package com.fbc.ihrm.entity.company;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "co_department")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private static final long serialVersionUID = -9084332495284489553L;
    //ID
    @Id
    private String id;
    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门编码，同级部门不可重复
     */
    private String code;
    /**
     * 部门类别
     */
    private String category;
    /**
     * 负责人ID
     */
    private String managerId;
    /**
     * 负责人名称
     */
    private String manager;
    /**
     * 城市
     */
    private String city;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 创建时间
     */
    private Date createTime;
}
