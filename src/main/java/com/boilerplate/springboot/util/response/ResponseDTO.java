package com.boilerplate.springboot.util.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO
 *
 * @author sapzape
 */
@Getter
@Setter
public class ResponseDTO<T> {

  private T data;
  private ErrorResponse error;

  public ResponseDTO() {
    this.data = null;
    this.error = null;
  }

  public ResponseDTO(ErrorResponse error) {
    this.error = error;
  }

  public ResponseDTO(T data) {
    this.data = data;
  }

  public static ResponseDTO success() {
    return new ResponseDTO(ResponseCode.OK);
  }

  public static <T> ResponseDTO success(T data) {
    return new ResponseDTO<>(data);
  }

  public static ResponseDTO fail(ErrorResponse error) {
    return new ResponseDTO<>(error);
  }
}
