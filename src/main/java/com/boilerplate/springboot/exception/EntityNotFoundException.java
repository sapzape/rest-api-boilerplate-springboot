package com.boilerplate.springboot.exception;

import com.boilerplate.springboot.util.response.ResponseCode;

/**
 * Entity query failure exception handling
 *
 * @author sapzape
 */
public class EntityNotFoundException extends CommonException {

  public EntityNotFoundException(String domain, Object id) {
    super("Not found in " + domain + " entity: " + id, ResponseCode.ENTITY_NOT_FOUND);
  }
}
