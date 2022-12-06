package com.example.apirestdemo.endpoint.advice;

import com.example.apirestdemo.endpoint.advice.response.BaseResponseError;
import com.example.apirestdemo.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomRestControllerAdvice {
  @ExceptionHandler({
      HttpMessageNotReadableException.class,
      MethodArgumentTypeMismatchException.class,
      MethodArgumentNotValidException.class,
      ConstraintViolationException.class,
      MissingServletRequestParameterException.class,
      BadRequestException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseResponseError catchBadRequestException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public BaseResponseError catchUnauthorizedException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.UNAUTHORIZED.value(),
        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseResponseError catchNotFoundException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public BaseResponseError catchMethodNotAllowedException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.METHOD_NOT_ALLOWED.value(),
        HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public BaseResponseError catchNotAcceptableException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.NOT_ACCEPTABLE.value(),
        HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public BaseResponseError catchConflictException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.CONFLICT.value(),
        HttpStatus.CONFLICT.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public BaseResponseError catchUnsupportedMediaTypeException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
        HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler(UnprocessableEntityException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public BaseResponseError catchUnprocessableEntityException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.UNPROCESSABLE_ENTITY.value(),
        HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
        t.getMessage() != null ? t.getMessage() : ExceptionUtils.getStackTrace(t)
    );
  }

  @ExceptionHandler({InternalServerErrorException.class, Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponseError catchInternalServerErrorException(Throwable t) {
    return new BaseResponseError(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        t.getMessage() != null ? t.toString() : ExceptionUtils.getStackTrace(t)
    );
  }
}
