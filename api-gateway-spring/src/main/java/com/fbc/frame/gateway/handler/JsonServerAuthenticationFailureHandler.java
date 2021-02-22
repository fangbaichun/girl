package com.fbc.frame.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.fbc.girl.common.response.CommonCode;
import com.fbc.girl.common.response.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

public class JsonServerAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        if (exception instanceof UsernameNotFoundException) {
            return writeErrorMessage(response, CommonCode.USER_NOT_EXISTS);
        } else if (exception instanceof BadCredentialsException) {
            return writeErrorMessage(response, CommonCode.MOBILE_OR_PASSWORD_ERROR);
        } else if (exception instanceof LockedException) {
            return writeErrorMessage(response, CommonCode.MOBILE_OR_PASSWORD_ERROR);
        }
        return writeErrorMessage(response, CommonCode.SERVER_ERROR);
    }

    private Mono<Void> writeErrorMessage(ServerHttpResponse response, CommonCode errorEnum) {
        Result baseResponse = new Result();
        baseResponse.setCode(errorEnum.code());
        baseResponse.setMessage(errorEnum.message());
        String result = JSONObject.toJSONString(baseResponse);
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes());
        return response.writeWith(Mono.just(buffer));
    }

}
