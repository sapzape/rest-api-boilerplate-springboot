package com.boilerplate.springboot.exception;

import com.boilerplate.springboot.util.response.ErrorResponse;
import com.boilerplate.springboot.util.response.ResponseCode;
import com.boilerplate.springboot.util.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Exception handler
 *
 * @author sapzape
 */
@RestControllerAdvice
@RestController
@Slf4j
public class CommonExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    log.error("handleMethodArgumentNotValidException", e);

    final ErrorResponse response =
        ErrorResponse.of(ResponseCode.INVALID_INPUT_VALUE, e.getBindingResult());
    return new ResponseEntity<>(ResponseDTO.fail(response), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    log.error("handleMethodArgumentTypeMismatchException", e);

    final ErrorResponse response = ErrorResponse.of(e);
    return new ResponseEntity<>(ResponseDTO.fail(response), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ResponseDTO> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    log.error("handleHttpRequestMethodNotSupportedException", e);

    final ErrorResponse response = ErrorResponse.of(ResponseCode.METHOD_NOT_ALLOWED);
    return new ResponseEntity<>(ResponseDTO.fail(response), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  protected ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    log.error("HttpMessageNotReadableException", e);

    final ErrorResponse response =
        ErrorResponse.of(ResponseCode.JSON_READING_ERROR, e.getLocalizedMessage());
    return new ResponseEntity<>(
        ResponseDTO.fail(response),
        HttpStatus.valueOf(ResponseCode.JSON_READING_ERROR.getStatus()));
  }

  @ExceptionHandler(value = {CommonException.class})
  protected ResponseEntity<ResponseDTO> handleCommonException(final CommonException e) {
    log.error("CommonException", e);

    final ResponseCode responseCode = e.getResponseCode();
    final ErrorResponse response = ErrorResponse.of(responseCode, e.getMessage());

    return new ResponseEntity<>(
        ResponseDTO.fail(response), HttpStatus.valueOf(responseCode.getStatus()));
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<ResponseDTO> handleException(final Exception e) {
    log.error("Exception", e);

    final ErrorResponse response =
        ErrorResponse.of(ResponseCode.COMMON_ERROR, e.getLocalizedMessage());

    return new ResponseEntity<>(
        ResponseDTO.fail(response), HttpStatus.valueOf(ResponseCode.COMMON_ERROR.getStatus()));
  }
}
