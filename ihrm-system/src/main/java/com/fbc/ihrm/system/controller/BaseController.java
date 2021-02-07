package com.fbc.ihrm.system.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    //企业id，（暂时使用1,以后会动态获取）
    public String parseCompanyId() {
        //根据登陆名获取企业id，先查数据库，后续通过缓存获取
        return "1357629146712375296";
    }

    public String parseCompanyName() {
        return "江苏传智播客教育股份有限公司";
    }
}
