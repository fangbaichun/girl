package com.fbc.girl.server.exception;

import com.fbc.girl.common.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemException extends RuntimeException{
    Logger logger = LoggerFactory.getLogger(BusinessException.class);
    private ResultCode resultCode;
    public SystemException(ResultCode resultCode) {
        super(resultCode.message());
        this.resultCode = resultCode;
        logger.error(resultCode.message(),resultCode);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
