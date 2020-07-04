package com.boilerplate.springboot.repository;

import com.boilerplate.springboot.domain.BaseEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository
 *
 * @author sapzape
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID>
    extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

  /**
   * Find entity by id (Check DelYn column value)
   *
   * @param id
   * @param yn
   * @return
   */
  Optional<T> findByIdAndDelYn(ID id, Boolean yn);

  /**
   * Find all entity (Check DelYn column value)
   *
   * @param yn
   * @param pageable
   * @return
   */
  Page<T> findAllByDelYn(Boolean yn, Pageable pageable);

  /**
   * Check existence of entity (Check DelYn column value)
   *
   * @param id
   * @param yn
   * @return
   */
  boolean existsByIdAndDelYn(ID id, Boolean yn);
}
