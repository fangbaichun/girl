package com.fbc.girl.common.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryResult<T> extends Result {

    private T data;

    public QueryResult(ResultCode resultCode,T data){
        super(resultCode);
        this.data = data;
    }
}
