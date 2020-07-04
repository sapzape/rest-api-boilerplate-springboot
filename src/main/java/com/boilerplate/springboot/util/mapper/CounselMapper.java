package com.boilerplate.springboot.util.mapper;

import com.boilerplate.springboot.domain.Counsel;
import com.boilerplate.springboot.dto.CounselDTO.Request;
import com.boilerplate.springboot.dto.CounselDTO.Response;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Consultation entity & DTO conversion mapper
 *
 * @author sapzape
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CounselMapper {

  /**
   * Convert DTO to entity
   *
   * @param dto
   * @return entity
   */
  Counsel toEntity(Request dto);

  /**
   * Convert entity to DTO
   *
   * @param entity
   * @return dto
   */
  Response toDto(Counsel entity);
}
