package com.fbc.ihrm.company.model;

import com.fbc.ihrm.entity.Company;
import com.fbc.ihrm.entity.company.Department;

import java.util.List;

public class DeptListResult {

    private Company company;

    private List<Department> list;

    public DeptListResult(Company company, List<Department> list) {
        this.company = company;
        this.list = list;
    }
}
