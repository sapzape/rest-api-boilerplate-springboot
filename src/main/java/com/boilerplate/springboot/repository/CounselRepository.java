package com.boilerplate.springboot.repository;

import com.boilerplate.springboot.domain.Counsel;
import org.springframework.stereotype.Repository;

/**
 * Consultation repository
 *
 * @author sapzape
 */
@Repository
public interface CounselRepository extends BaseRepository<Counsel, Long> {}
