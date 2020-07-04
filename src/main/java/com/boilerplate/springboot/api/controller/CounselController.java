package com.boilerplate.springboot.api.controller;

import com.boilerplate.springboot.dto.CounselDTO.Request;
import com.boilerplate.springboot.util.response.ResponseDTO;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boilerplate.springboot.util.service.CounselService;

/**
 * Consultation controller
 *
 * @author sapzape
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/counsel")
public class CounselController {

  private final CounselService counselService;

  @GetMapping
  public ResponseDTO getAll(
      @PageableDefault(
              page = 0,
              size = 10,
              sort = {})
          @SortDefault.SortDefaults({
            @SortDefault(
                sort = {"id"},
                direction = Direction.DESC)
          })
          Pageable pageable) {

    return ResponseDTO.success(counselService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseDTO get(@PathVariable("id") @Valid long id) {
    return ResponseDTO.success(counselService.get(id));
  }

  @GetMapping("/{id}/status")
  public ResponseDTO getStatuses(@PathVariable("id") @Valid long id) {
    return ResponseDTO.success(counselService.getStatuses(id));
  }

  @PostMapping
  public ResponseDTO create(@RequestBody @Valid Request request) {
    return ResponseDTO.success(counselService.create(request));
  }

  @PutMapping("/{id}")
  public ResponseDTO update(
      @PathVariable("id") @Valid Long id, @RequestBody @Valid Request request) {
    return ResponseDTO.success(counselService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseDTO delete(@PathVariable("id") @Valid Long id) {
    counselService.delete(id);

    return ResponseDTO.success();
  }
}
