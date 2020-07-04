package com.boilerplate.springboot.util.service;

import com.boilerplate.springboot.domain.BaseEntity;
import com.boilerplate.springboot.exception.EntityNotFoundException;
import com.boilerplate.springboot.repository.BaseRepository;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Common CRUD function service
 *
 * @author sapzape
 */
@RequiredArgsConstructor
public abstract class AbstractCrudService<DOMAIN extends BaseEntity, ID>
    implements CrudService<DOMAIN, ID> {

  private final String domainName;

  private final BaseRepository<DOMAIN, ID> repository;

  protected AbstractCrudService(BaseRepository<DOMAIN, ID> repository) {
    this.repository = repository;

    Class<DOMAIN> domainClass = (Class<DOMAIN>) fetchType();
    domainName = domainClass.getSimpleName();
  }

  private Type fetchType() {
    return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  /**
   * Return the entire list (+paging processing)
   *
   * @param pageable
   * @return
   */
  @Override
  public @NonNull Page<DOMAIN> list(@NonNull Pageable pageable) {
    return repository.findAllByDelYn(false, pageable);
  }

  /**
   * Return single entity
   *
   * @param id
   * @return
   */
  @Override
  public @NonNull Optional<DOMAIN> fetchById(@NonNull ID id) {
    return repository.findByIdAndDelYn(id, false);
  }

  /**
   * Return single entity. If not, an exception is thrown (+ Exception)
   *
   * @param id
   * @return
   */
  @Override
  public DOMAIN getById(@NonNull ID id) {
    return fetchById(id).orElseThrow(() -> new EntityNotFoundException(domainName, id));
  }

  /**
   * Create entity
   *
   * @param domain
   * @return
   */
  @Override
  public DOMAIN create(@NonNull DOMAIN domain) {
    return repository.save(domain);
  }

  /**
   * Update entity
   *
   * @param domain
   * @return
   */
  @Override
  public DOMAIN update(@NonNull DOMAIN domain) {
    return repository.saveAndFlush(domain);
  }

  /**
   * Remove entity
   *
   * @param id
   * @return
   */
  @Override
  public DOMAIN removeById(@NonNull ID id) {
    DOMAIN domain = getById(id);

    remove(domain);

    return domain;
  }

  /**
   * Remove entity (Not delete. update delete column)
   *
   * @param domain
   */
  @Override
  public void remove(@NonNull DOMAIN domain) {
    domain.setDelYn(true);
    update(domain);
  }

  /**
   * Check existence of entity. If not, an exception is thrown (Only delYn=false)
   *
   * @param id
   */
  @Override
  public void existsByDelYnOrElseThrow(@NonNull ID id) {
    if (!repository.existsByIdAndDelYn(id, false)) {
      throw new EntityNotFoundException(domainName, id);
    }
  }

  /**
   * Check existence of entity (Only delYn=false)
   *
   * @param id
   * @return
   */
  @Override
  public boolean existsByDelYn(@NonNull ID id) {
    return repository.existsByIdAndDelYn(id, false);
  }
}
