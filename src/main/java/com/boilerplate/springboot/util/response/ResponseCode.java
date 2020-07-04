package com.boilerplate.springboot.util.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * Response Code
 *
 * @author sapzape
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ResponseCode {
  OK(200, "0000", "OK"),
  CREATED(201, "0001", "Created"),
  COMMON_ERROR(400, "4000", "An error has occurred"),
  PARAMETER_NOT_ALLOWED(405, "4001", "This parameter is not allowed"),
  INVALID_INPUT_VALUE(400, "4002", "The input value is not valid"),
  METHOD_NOT_ALLOWED(405, "4003", "Method not allowed"),
  JSON_READING_ERROR(400, "4004", "JSON format parsing failed"),
  ALREADY_COMPLETED(400, "4005", "It is already completed"),
  ENTITY_NOT_FOUND(400, "4006", "Entity not found"),
  SAME_STATUS(400, "4007", "Same as current");

  private final Integer status;
  private final String code;
  private final String message;

  ResponseCode(Integer status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
