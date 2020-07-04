package com.boilerplate.springboot.exception;

import com.boilerplate.springboot.util.response.ResponseCode;

/**
 * Common Exception
 *
 * @author sapzape
 */
public class CommonException extends RuntimeException {

  private ResponseCode responseCode;

  public CommonException(String message, ResponseCode responseCode) {
    super(message);
    this.responseCode = responseCode;
  }

  public CommonException(ResponseCode responseCode) {
    this.responseCode = responseCode;
  }

  public CommonException() {};

  public ResponseCode getResponseCode() {
    return this.responseCode;
  }
}
