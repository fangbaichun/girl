package com.fbc.ihrm.company.service;

import cn.hutool.core.lang.Snowflake;
import com.fbc.ihrm.company.dao.DepartmentDao;
import com.fbc.ihrm.entity.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department> {

    @Autowired
    @SuppressWarnings("ALL")
    private Snowflake snowflake;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 添加部门
     */
    public void save(Department department) {
        //填充其他参数
        department.setId(snowflake.nextIdStr());
        department.setCreateTime(new Date());
        departmentDao.save(department);
    }

    /**
     * 更新部门信息
     */
    public void update(Department department) {
        Department sourceDepartment = departmentDao.findById(department.getId()).get();
        sourceDepartment.setName(department.getName());
        sourceDepartment.setPid(department.getPid());
        sourceDepartment.setManagerId(department.getManagerId());
        sourceDepartment.setIntroduce(department.getIntroduce());
        sourceDepartment.setManager(department.getManager());
        departmentDao.save(sourceDepartment);
    }

    /**
     * 根据ID获取部门信息
     *
     * @param id 部门ID
     * @return 部门信息
     */
    public Department findById(String id) {
        return departmentDao.findById(id).get();
    }

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    public void delete(String id) {
        departmentDao.deleteById(id);
    }

    /**
     * 获取部门列表
     */
    public List<Department> findAll(String companyId) {
        return departmentDao.findAll(getSpecification(companyId));
    }
}
