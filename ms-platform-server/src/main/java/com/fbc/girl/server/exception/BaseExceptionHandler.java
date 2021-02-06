package com.fbc.girl.server.exception;

import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result error(HttpServletRequest request, HttpServletResponse response,
                        Exception e) throws IOException {
        e.printStackTrace();
        if (e.getClass() == BusinessException.class) {
            BusinessException be = (BusinessException) e;
            return Result.FAIL();
        } else if (e.getClass() == SystemException.class) {
            SystemException se = (SystemException) e;
            return new Result(CommonCode.SERVER_ERROR);
        }
        return new Result(CommonCode.UNKNOW_ERROR);
    }
}
