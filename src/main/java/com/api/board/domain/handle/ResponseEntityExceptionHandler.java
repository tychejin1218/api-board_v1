package com.api.board.domain.handle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.board.exception.ResourceNotFoundException;
import com.api.board.exception.domain.ResponseError;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseError handleResourceNotFound(ResourceNotFoundException e) {
        ResponseError responseError = new ResponseError(404, "Resource가 존재하지 않습니다.");
        return responseError;
    }
}