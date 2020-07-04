package com.boilerplate.springboot.util.service;

import com.boilerplate.springboot.domain.Counsel;
import com.boilerplate.springboot.domain.Status;
import com.boilerplate.springboot.dto.CounselDTO.Request;
import com.boilerplate.springboot.dto.CounselDTO.Response;
import com.boilerplate.springboot.repository.CounselRepository;
import com.boilerplate.springboot.util.mapper.CounselMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Consultation service
 *
 * @author sapzape
 */
@Service
public class CounselServiceImpl extends AbstractCrudService<Counsel, Long>
    implements CounselService {

  private static final String COUNSEL_REGISTRATION = "COUNSEL_REGISTRATION";

  private final CounselMapper counselMapper;

  public CounselServiceImpl(
      CounselRepository counselRepository, CounselMapper counselMapper) {
    super(counselRepository);
    this.counselMapper = counselMapper;
  }

  /**
   * Return single consultation entity
   *
   * @param id
   * @return consultation DTO
   */
  @Override
  public Response get(long id) {
    Counsel counsel = super.getById(id);

    return counselMapper.toDto(counsel);
  }

  /**
   * Return the consultation entire list
   *
   * @param pageable
   * @return consultation entity list
   */
  @Override
  public List<Response> findAll(Pageable pageable) {
    Page<Counsel> counsels = super.list(pageable);

    return counsels.stream().map(counselMapper::toDto).collect(Collectors.toList());
  }

  /**
   * Return consultation change status history inquiry
   *
   * @param id
   * @return consultation status list
   */
  @Override
  public List<Status> getStatuses(long id) {
    Counsel entity = super.getById(id);

    return entity.getStatuses();
  }

  /**
   * Create consultation entity
   *
   * @param request
   * @return consultation entity id
   */
  @Override
  public long create(Request request) {
    Counsel counsel = counselMapper.toEntity(request);
    counsel.addStatus(COUNSEL_REGISTRATION);

    return super.create(counsel).getId();
  }

  /**
   * Update consultation entity
   *
   * @param id
   * @param request
   * @return consultation entity id
   */
  @Override
  public long update(long id, Request request) {
    Counsel exist = super.getById(id);
    Counsel update = counselMapper.toEntity(request);
    update.setId(exist.getId());

    return super.update(update).getId();
  }

  /**
   * Remove consultation entity
   *
   * @param id
   */
  @Override
  public void delete(long id) {
    super.removeById(id);
  }
}
