package com.boilerplate.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Address entity (embeddable)
 *
 * @author sapzape
 */
@Embeddable
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

  @NotBlank
  @Column(name = "ST_NM_ADDR1", nullable = false)
  private String streetNameAddress1;

  @NotBlank
  @Column(name = "ST_NM_ADDR2", nullable = false)
  private String streetNameAddress2;

  @NotBlank
  @Size(max = 5)
  @Column(name = "ZIP_CD", length = 5, nullable = false)
  private String zipCode;

  @NotBlank
  @Column(name = "OLD_ADDR1", nullable = false)
  private String oldAddress1;

  @NotBlank
  @Column(name = "OLD_ADDR2", nullable = false)
  private String oldAddress2;
}
