package com.fbc.ihrm.entity.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pe_permission_api")
@Getter
@Setter
public class PermissionApi implements Serializable {
    private static final long serialVersionUID = -1803315043290784820L;
    @Id
    private String id;
    private String apiUrl;
    private String apiMethod;
    private String apiLevel;//权限等级，1为通用接口权限，2为需校验接口权限
}