package com.sjy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 傻傻的远
 * @date 2021 11 17:53
 * @Description:
 */

@ResponseStatus(HttpStatus.NOT_FOUND)   //资源找不到异常
public class NotFindException extends  RuntimeException{
    public NotFindException() {
    }

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
