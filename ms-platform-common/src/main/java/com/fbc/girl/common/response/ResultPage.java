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
    private T rows;
    /**
     * 数据总数
     */
    private long total;

    public ResultPage(T rows) {
        this.rows = rows;
    }
}
