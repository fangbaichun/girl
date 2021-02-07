package com.fbc.girl.server.exception;

import com.fbc.girl.common.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemException extends RuntimeException{
    Logger logger = LoggerFactory.getLogger(BusinessException.class);
    public SystemException(ResultCode resultCode) {
        super(resultCode.message());
        logger.error(resultCode.message(),resultCode);
    }
}
