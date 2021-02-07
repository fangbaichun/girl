package com.fbc.ihrm.company.model;

import com.fbc.ihrm.entity.company.Company;
import lombok.Data;

import java.util.List;

@Data
public class DeptListResult {

    private Company company;

    private List list;

    public DeptListResult(Company company, List list) {
        this.company = company;
        this.list = list;
    }
}
