package com.boilerplate.springboot.util.service;

import com.boilerplate.springboot.domain.Status;
import com.boilerplate.springboot.dto.CounselDTO.Request;
import com.boilerplate.springboot.dto.CounselDTO.Response;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Consultation service interface
 *
 * @author sapzape
 */
@Transactional
public interface CounselService {

  /**
   * Return single consultation entity
   *
   * @param id
   * @return DTO
   */
  Response get(long id);

  /**
   * Return the consultation entire list
   *
   * @param pageable
   * @return List DTO
   */
  List<Response> findAll(Pageable pageable);

  /**
   * Return consultation change status history inquiry
   *
   * @param id
   * @return Status
   */
  List<Status> getStatuses(long id);

  /**
   * Create consultation entity
   *
   * @param request
   * @return id
   */
  long create(Request request);

  /**
   * Update consultation entity
   *
   * @param id
   * @param request
   * @return id
   */
  long update(long id, Request request);

  /**
   * Remove consultation entity
   *
   * @param id
   */
  void delete(long id);
}
