package com.energizeglobal.itpm.api;

import com.energizeglobal.itpm.util.ApiError;
import com.energizeglobal.itpm.util.exceptions.AlreadyExistsException;
import com.energizeglobal.itpm.util.exceptions.FileSystemException;
import com.energizeglobal.itpm.util.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundExceptions(NotFoundException exception) {
        return new ResponseEntity<>(new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAlreadyExistsException(AlreadyExistsException exception) {
        return new ResponseEntity<>(new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSystemException.class)
    public ResponseEntity<ApiError> handleFileSystemException(FileSystemException exception) {
        return new ResponseEntity<>(new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}

