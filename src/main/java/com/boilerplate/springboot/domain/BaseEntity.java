package com.boilerplate.springboot.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Base entity
 *
 * @author sapzape
 */
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @CreatedDate
  @Column(name = "CREATE_DT", nullable = false, updatable = false)
  private LocalDateTime createDate;

  @Column(name = "CREATE_USER", length = 6, updatable = false)
  private String createUser;

  @LastModifiedDate
  @Column(name = "UPDATE_DT")
  private LocalDateTime updateDate;

  @Column(name = "UPDATE_USER", length = 6)
  private String updateUser;

  @Column(name = "DEL_YN", nullable = false)
  @Builder.Default
  private boolean delYn = false;
}
