package com.fbc.ihrm.entity.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pe_permission_menu")
@Getter
@Setter
public class PermissionMenu implements Serializable {
  private static final long serialVersionUID = -1002411490113957485L;
  @Id
  private String id; //主键
  private String menuIcon; //展示图标
  private String menuOrder; //排序号
}
