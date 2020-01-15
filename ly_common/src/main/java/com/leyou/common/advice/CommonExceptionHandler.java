package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptionx.LyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/14 15:59
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<String> handleException(LyException e){
        ExceptionEnum em = e.getExceptionEnums();
        return ResponseEntity.status(em.getCode()).body(em.getMsg());
    }
}
