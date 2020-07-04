package com.boilerplate.springboot.util.service;

import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Common CRUD function service interface
 *
 * @author sapzape
 */
public interface CrudService<DOMAIN, ID> {

  /**
   * Return the entire list (+paging processing)
   *
   * @param pageable
   * @return entity list
   */
  @NonNull
  Page<DOMAIN> list(@NonNull Pageable pageable);

  /**
   * Return single entity
   *
   * @param id
   * @return entity
   */
  @NonNull
  Optional<DOMAIN> fetchById(@NonNull ID id);

  /**
   * Return single entity. If not, an exception is thrown (+ Exception)
   *
   * @param id
   * @return entity
   */
  @NonNull
  DOMAIN getById(@NonNull ID id);

  /**
   * Create entity
   *
   * @param domain
   * @return entity
   */
  @NonNull
  @Transactional
  DOMAIN create(@NonNull DOMAIN domain);

  /**
   * Update entity
   *
   * @param domain
   * @return entity
   */
  @NonNull
  @Transactional
  DOMAIN update(@NonNull DOMAIN domain);

  /**
   * Remove entity
   *
   * @param id
   * @return entity
   */
  @NonNull
  @Transactional
  DOMAIN removeById(@NonNull ID id);

  /**
   * Remove entity (Not delete. update delete column)
   *
   * @param domain
   */
  @Transactional
  void remove(@NonNull DOMAIN domain);

  /**
   * Check existence of entity. If not, an exception is thrown (Only delYn=false)
   *
   * @param id
   */
  void existsByDelYnOrElseThrow(@NonNull ID id);

  /**
   * Check existence of entity (Only delYn=false)
   *
   * @param id
   */
  boolean existsByDelYn(@NonNull ID id);
}
