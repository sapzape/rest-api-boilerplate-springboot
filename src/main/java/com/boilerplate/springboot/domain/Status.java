package com.boilerplate.springboot.domain;

import com.boilerplate.springboot.exception.CommonException;
import com.boilerplate.springboot.util.response.ResponseCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Consultation status manage entity
 *
 * @author sapzape
 */
@Entity(name = "COUNSEL_STATUS")
@Getter
@Setter
@NoArgsConstructor
public class Status extends BaseEntity {

  @Transient private static final String APPLY = "APPLY";

  @Transient private String lastCounselState;

  @Column(name = "STATUS", nullable = false, updatable = false)
  private String status;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ManyToOne
  @JoinColumn(name = "COUNSEL_NO", nullable = false, updatable = false)
  private Counsel counsel;

  @Builder
  public Status(final String status, final Counsel counsel) {
    checkStatus(status, counsel);
    this.status = status;
    this.counsel = counsel;
  }

  private void checkStatus(String status, Counsel counsel) {
    if (!counsel.getStatuses().isEmpty()) {
      this.lastCounselState = getLastStaus(counsel);
      checkLastStatus(status);
      checkCompleted();
    }
  }

  private void checkCompleted() {
    if (isCompleted()) throw new CommonException(ResponseCode.ALREADY_COMPLETED);
  }

  private boolean isCompleted() {
    return this.lastCounselState.equals(APPLY);
  }

  private void checkLastStatus(String status) {
    if (this.lastCounselState.equals(status)) throw new CommonException(ResponseCode.SAME_STATUS);
  }

  private String getLastStaus(Counsel counsel) {
    final int idx = counsel.getStatuses().size() - 1;

    return counsel.getStatuses().get(idx).getStatus();
  }
}
