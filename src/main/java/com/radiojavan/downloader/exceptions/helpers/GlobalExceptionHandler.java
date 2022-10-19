package com.radiojavan.downloader.exceptions.helpers;

import com.radiojavan.downloader.exceptions.BadParamException;
import com.radiojavan.downloader.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            BadParamException.class,
    })
    public ResponseEntity<ResponseModel<Object>> badParam(Exception exception) {
        return new ResponseEntity<>(new ResponseModel<>(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            Exception.class,
    })
    public ResponseEntity<ResponseModel<Object>> exception(Exception ignored) {
        return new ResponseEntity<>(new ResponseModel<>(false, "internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
