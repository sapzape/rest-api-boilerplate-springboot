package com.boilerplate.springboot.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Consultation entity
 *
 * @author sapzape
 */
@Entity(name = "COUNSEL")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counsel extends BaseEntity {

  @Column(name = "TYPE")
  private String type;

  @Column(name = "APPL_DT")
  private LocalDate applyDate;

  @Column(name = "RECOMMEND_CD")
  private String recommendCode;

  @Column(name = "MEMBER_NM")
  private String memberName;

  @Column(name = "MEMBER_TYPE")
  private String memberType;

  @Column(name = "CELL_PHONE")
  private String cellPhone;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "MEMO", columnDefinition = "text")
  private String memo;

  @Column(name = "STATUS")
  private String status;

  @Embedded private Address address;

  @Column(name = "CHARGE_MANAGER")
  private String chargeManager;

  @OneToMany(
      mappedBy = "counsel",
      cascade = CascadeType.PERSIST,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  @Builder.Default
  private List<Status> statuses = new ArrayList<>();

  public void addStatus(String status) {
    this.status = status;
    this.statuses.add(createLog(status));
  }

  private Status createLog(String status) {
    return Status.builder().status(status).counsel(this).build();
  }
}
