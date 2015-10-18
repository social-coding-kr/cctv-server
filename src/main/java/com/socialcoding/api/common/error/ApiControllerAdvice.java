package com.socialcoding.api.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ApiControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, BindException.class})
    public Map<String, Object> resolveArgumentException(HttpServletRequest request, Exception exception) {
        log.error("Parameter exception occurred", exception);

        return new HashMap<String, Object>() {
            {
                put("status", com.socialcoding.api.common.ResponseStatus.FAILURE);
                put("errorCode", HttpStatus.BAD_REQUEST.value());
                put("errorMessage", exception.getMessage());
            }
        };
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, Object> resolveException(HttpServletRequest request, Exception exception) {
        log.error("Exception occurred", exception);

        return new HashMap<String, Object>() {
            {
                put("status", com.socialcoding.api.common.ResponseStatus.FAILURE);
                put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
                put("errorMessage", exception.getMessage());
            }
        };
    }
}
