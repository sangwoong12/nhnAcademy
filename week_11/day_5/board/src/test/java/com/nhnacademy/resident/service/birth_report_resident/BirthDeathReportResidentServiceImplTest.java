package com.nhnacademy.resident.service.birth_report_resident;

import com.nhnacademy.resident.domain.birth_death_report_resident.ReportResidentDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.UpdateReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.AccessDeniedException;
import com.nhnacademy.resident.exception.NotFoundBirthDeathReportResidentException;
import com.nhnacademy.resident.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.utils.BirthDeathReportResidentUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BirthDeathReportResidentServiceImplTest {
    @Mock
    private BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    @Mock
    private ResidentRepository residentRepository;

    @InjectMocks
    private BirthDeathReportResidentServiceImpl birthDeathReportResidentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        birthDeathReportResidentService = new BirthDeathReportResidentServiceImpl(birthDeathReportResidentRepository, residentRepository);
    }

    @Test
    void update() {
        // given
        Long serialNumber = 1L;
        Long targetSerialNumber = 2L;
        UpdateReportResidentDto dto = new UpdateReportResidentDto();
        ReflectionTestUtils.setField(dto, "birthDeathReportDate", LocalDate.parse("2020-05-02"));
        ReflectionTestUtils.setField(dto, "reportQualificationsCode", "테스트");
        ReflectionTestUtils.setField(dto, "emailAddress", null);
        ReflectionTestUtils.setField(dto, "phoneNumber", "010-2345-6789");

        BirthDeathReportResident.BirthDeathType type = BirthDeathReportResident.BirthDeathType.DEATH;

        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(type, targetSerialNumber);

        // when
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(pk);
        Resident resident = new Resident();
        resident.setResidentSerialNumber(1L);
        birthDeathReportResident.setReportResident(resident);

        when(birthDeathReportResidentRepository.findById(any())).thenReturn(Optional.of(birthDeathReportResident));
        when(birthDeathReportResidentRepository.save(any())).thenReturn(new BirthDeathReportResident());
        when(birthDeathReportResidentRepository.findByPk(any())).thenReturn(
                // 가상 dto
                () -> null);

        // then
        ReportResidentDto update = birthDeathReportResidentService.update(serialNumber, targetSerialNumber, dto, type);
        Assertions.assertThat(update).isNotNull();
    }

    @Test
    @DisplayName("update AccessDeniedException 발생 확인")
    void update2() {
        // given
        Long serialNumber = 1L;
        Long targetSerialNumber = 2L;
        UpdateReportResidentDto dto = new UpdateReportResidentDto();
        ReflectionTestUtils.setField(dto, "birthDeathReportDate", LocalDate.parse("2020-05-02"));
        ReflectionTestUtils.setField(dto, "reportQualificationsCode", "테스트");
        ReflectionTestUtils.setField(dto, "emailAddress", null);
        ReflectionTestUtils.setField(dto, "phoneNumber", "010-2345-6789");

        BirthDeathReportResident.BirthDeathType type = BirthDeathReportResident.BirthDeathType.DEATH;

        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(type, targetSerialNumber);

        // when
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(pk);
        Resident resident = new Resident();
        resident.setResidentSerialNumber(2L);
        birthDeathReportResident.setReportResident(resident);

        when(birthDeathReportResidentRepository.findById(any())).thenReturn(Optional.of(birthDeathReportResident));

        //then
        try {
            birthDeathReportResidentService.update(serialNumber, targetSerialNumber, dto, type);
        } catch (Exception e) {
            Assertions.assertThat(e.getClass()).isEqualTo(AccessDeniedException.class);
        }
    }

    @Test
    void update3() {
        // given
        Long serialNumber = 1L;
        Long targetSerialNumber = 2L;
        UpdateReportResidentDto dto = new UpdateReportResidentDto();
        ReflectionTestUtils.setField(dto, "birthDeathReportDate", LocalDate.parse("2020-05-02"));
        ReflectionTestUtils.setField(dto, "reportQualificationsCode", "테스트");
        ReflectionTestUtils.setField(dto, "emailAddress", null);
        ReflectionTestUtils.setField(dto, "phoneNumber", "010-2345-6789");

        BirthDeathReportResident.BirthDeathType type = BirthDeathReportResident.BirthDeathType.DEATH;

        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(type, targetSerialNumber);

        // when
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(pk);
        Resident resident = new Resident();
        resident.setResidentSerialNumber(1L);
        birthDeathReportResident.setReportResident(resident);

        when(birthDeathReportResidentRepository.findById(any())).thenReturn(Optional.empty());

        // then
        try {
            birthDeathReportResidentService.update(serialNumber, targetSerialNumber, dto, type);
        } catch (Exception e) {
            Assertions.assertThat(e.getClass()).isEqualTo(NotFoundBirthDeathReportResidentException.class);
        }
    }

    @Test
    void addReportResident() {
    }

    @Test
    void delete() {
        Long serialNumber = 1L;
        Long targetSerialNumber = 2L;
        BirthDeathReportResident.BirthDeathType type = BirthDeathReportResident.BirthDeathType.DEATH;

        // 테스트 실행
        birthDeathReportResidentService.delete(serialNumber, targetSerialNumber, type);

        // 삭제 메서드가 제대로 호출되었는지 확인
        verify(birthDeathReportResidentRepository).deleteByPkAndReportResident_ResidentSerialNumber(any(), any());
    }

    @Test
    void findBirthReport() {
        // given
        Long serialNumber = 1L;
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.BIRTH, serialNumber);

        // when
        birthDeathReportResidentService.findBirthReport(serialNumber);

        // then
        verify(birthDeathReportResidentRepository).findBirthByPk(pk);
    }

    @Test
    void findDeathReport() {
        // given
        Long serialNumber = 1L;
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.DEATH, serialNumber);

        // when
        birthDeathReportResidentService.findDeathReport(serialNumber);

        // then
        verify(birthDeathReportResidentRepository).findDeathByPk(pk);
    }
}