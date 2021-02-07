package com.fbc.girl.common.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryResult<T> extends Result {

    private T result;

    public QueryResult(ResultCode resultCode,T result){
        super(resultCode);
        this.result = result;
    }
}
