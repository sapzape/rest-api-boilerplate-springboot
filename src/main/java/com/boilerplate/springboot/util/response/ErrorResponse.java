package com.boilerplate.springboot.util.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Error response processing
 *
 * @author sapzape
 */
@Getter
@NoArgsConstructor
public class ErrorResponse {

  private Integer status;
  private String code;
  private String message;
  private List<String> errors;

  private ErrorResponse(final ResponseCode code, final List<String> errors) {
    this.status = code.getStatus();
    this.code = code.getCode();
    this.message = code.getMessage();
    this.errors = errors;
  }

  private ErrorResponse(final ResponseCode code, final String message) {
    this.status = code.getStatus();
    this.code = code.getCode();
    this.message = code.getMessage();
    this.errors = Arrays.asList(message);
  }

  private ErrorResponse(final ResponseCode code) {
    this.status = code.getStatus();
    this.code = code.getCode();
    this.message = code.getMessage();
    this.errors = new ArrayList<>();
  }

  public static ErrorResponse of(final ResponseCode code, final BindingResult bindingResult) {
    List<String> errors =
        bindingResult.getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());
    return new ErrorResponse(code, errors);
  }

  public static ErrorResponse of(final ResponseCode code, final String error) {
    return new ErrorResponse(code, error);
  }

  public static ErrorResponse of(final ResponseCode code, final List<String> errors) {
    return new ErrorResponse(code, errors);
  }

  public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
    return new ErrorResponse(
        ResponseCode.PARAMETER_NOT_ALLOWED,
        e.getErrorCode() + ": " + e.getName() + ", " + e.getParameter());
  }

  public static ErrorResponse of(final ResponseCode code) {
    return new ErrorResponse(code);
  }
}
