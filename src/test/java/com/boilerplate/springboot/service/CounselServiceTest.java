package com.boilerplate.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.boilerplate.springboot.domain.Address;
import com.boilerplate.springboot.domain.Counsel;
import com.boilerplate.springboot.dto.CounselDTO.Request;
import com.boilerplate.springboot.dto.CounselDTO.Response;
import com.boilerplate.springboot.exception.CommonException;
import com.boilerplate.springboot.repository.CounselRepository;
import com.boilerplate.springboot.util.mapper.CounselMapper;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.boilerplate.springboot.util.service.CounselServiceImpl;

/**
 * Consultation service layer test
 *
 * @author sapzape
 */
@RunWith(MockitoJUnitRunner.class)
public class CounselServiceTest {

  private static final Long COUNSEL_ID = 999999L;

  @Spy
  CounselMapper counselMapper = Mappers.getMapper(CounselMapper.class);

  @Mock private CounselRepository counselRepository;

  @InjectMocks @Autowired CounselServiceImpl counselService;

  @Before
  public void setUp() {
    Request request =
        Request.builder()
            .type("diagnosis")
            .memberName("member kim")
            .address(
                Address.builder()
                    .zipCode("123456")
                    .streetNameAddress1("Somewhere in Gangnam-gu, Seoul")
                    .streetNameAddress2("What Apartment No. 101, 1st floor No. 101")
                    .build())
            .chargeManager("manager kim")
            .createUser("creator kim")
            .build();
    Counsel counsel = counselMapper.toEntity(request);
    counsel.setId(COUNSEL_ID);

    when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(counsel);
    when(counselRepository.findByIdAndDelYn(COUNSEL_ID, false))
        .thenReturn(Optional.of(counsel));
  }

  @Test
  public void doRegisteredConsultation() {
    Long id = counselService.create(Request.builder().type("diagnosis").build());

    assertThat(id).isEqualTo(COUNSEL_ID);
  }

  @Test
  public void doFindTheRightConsultation() {
    Response response = counselService.get(COUNSEL_ID);

    assertThat(response.getType()).isEqualTo("diagnosis");
    assertThat(response.getAddress().getStreetNameAddress1())
        .isEqualTo("Somewhere in Gangnam-gu, Seoul");
    assertThat(response.getAddress().getStreetNameAddress2())
        .isEqualTo("What Apartment No. 101, 1st floor No. 101");
  }

  @Test
  public void throwExceptionWhenConsultationIdNotExist() {
    assertThatThrownBy(() -> counselService.get(9L)).isInstanceOf(CommonException.class);
  }
}
