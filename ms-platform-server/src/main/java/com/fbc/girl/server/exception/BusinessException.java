package com.fbc.girl.server.exception;

import com.fbc.girl.common.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends RuntimeException{
    Logger logger = LoggerFactory.getLogger(BusinessException.class);
    public BusinessException(ResultCode resultCode) {
        super(resultCode.message());
        logger.error(resultCode.message(),resultCode);
    }
}
