package com.fbc.girl.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
@AllArgsConstructor
public class ResultPage<T> {

    /**
     * 单个对象
     */
    private T data;
    /**
     * 数据总数
     */
    private long total;

    public ResultPage(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
