package com.fbc.girl.common.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryResult<T> extends Result {

    private ResultData resultData;

    public QueryResult(ResultCode resultCode,ResultData resultData){
        super(resultCode);
        this.resultData = resultData;
    }
}
