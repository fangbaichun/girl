package com.fbc.girl.common.response;

/**
 * @Description
 * @ClassName CustomerCode
 * @Author fangbc
 * @Date 2019/9/10 10:59
 * @Version 1.0
 */
public class CustomerCode implements ResultCode {

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
    public CustomerCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public CustomerCode(boolean success,String message){
        this.success = success;
        if (success) {
            this.code = 10000;
        } else {
            this.code = 99999;
        }
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
