package com.fbc.girl.common.response;

import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{

    /**
     * 操作成功
     */
    SUCCESS(true,10000,"操作成功！"),
    /**
     * 操作失败
     */
    FAIL(false,11111,"操作失败！"),
    /**
     * 未认证
     */
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    /**
     * 权限不足
     */
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    /**
     * 无效参数
     */
    INVALID_PARAM(false,10003,"非法参数"),
    /**
     * 系统错误
     */
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
    /**
     * 未知错误
     */
    UNKNOW_ERROR(false,77777,"未知错误，请联系开发人员！"),
//    private static ImmutableMap<Integer, CommonCode> codes ;
    /**
     * 账号或密码错误
     */
    MOBILE_OR_PASSWORD_ERROR(false,10004,"账号或密码错误"),
    /**
     * token失效
     */
    TOKEN_EXPIRED(false,10005,"token已失效"),
    USER_NOT_EXISTS(false,10006,"用户不存在");
    /**
     * 操作是否成功
     */
    boolean success;
    /**
     * 操作代码
     */
    int code;
    /**
     * 提示信息
     */
    String message;

    /**
     * 私有化构造方法禁止创建对象
     */
    CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
