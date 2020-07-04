package com.boilerplate.springboot.dto;

import com.boilerplate.springboot.domain.Address;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Consultation DTO
 *
 * @author sapzape
 */
public class CounselDTO implements Serializable {
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  public static class Request {

    private String type;

    private LocalDate applyDate;

    private String recommendCode;

    private String memberName;

    private String memberType;

    private String cellPhone;

    private String email;

    private String memo;

    private Address address;

    private String chargeManager;

    private String createUser;

    private String updateUser;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  public static class Response {
    private Long id;

    private String type;

    private LocalDate applyDate;

    private String memberName;

    private String cellPhone;

    private String email;

    private String memo;

    private String status;

    private Address address;

    private String chargeManager;
  }
}
