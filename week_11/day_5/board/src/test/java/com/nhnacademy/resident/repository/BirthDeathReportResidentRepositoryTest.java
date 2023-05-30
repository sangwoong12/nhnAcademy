package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.domain.birth_death_report_resident.BirthReportDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.DeathReportDto;
import com.nhnacademy.resident.domain.birth_death_report_resident.ReportResidentDto;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.utils.BirthDeathReportResidentUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class BirthDeathReportResidentRepositoryTest {
    @Autowired
    BirthDeathReportResidentRepository repository;
    @Test
    void findByPk() {
        // given
        Long serialNumber = 7L;
        // when
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.BIRTH, serialNumber);
        ReportResidentDto byPk = repository.findByPk(pk);
        // then
        assertThat(byPk.getPk()).isEqualTo(pk);

    }

    @Test
    void deleteByPkAndReportResident_ResidentSerialNumber() {
        // given
        Long TargetSerialNumber = 7L;
        Long serialNumber = 4L;
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.BIRTH, TargetSerialNumber);
        // when
        repository.deleteByPkAndReportResident_ResidentSerialNumber(pk,serialNumber);
        ReportResidentDto byPk = repository.findByPk(pk);

        // then
        assertThat(byPk).isNull();
    }

    @Test
    @DisplayName("신고사의 이름이 일치하는지 확인")
    void findBirthByPk() {
        // given
        Long TargetSerialNumber = 7L;
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.BIRTH, TargetSerialNumber);

        // when
        BirthReportDto birthByPk = repository.findBirthByPk(pk);

        // then
        String name = "남기석";
        assertThat(birthByPk.getResident().getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("신고사의 이름이 일치하는지 확인")
    void findDeathByPk() {
        // given
        Long TargetSerialNumber = 1L;
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.DEATH, TargetSerialNumber);

        // when
        DeathReportDto deathByPk = repository.findDeathByPk(pk);

        // then
        String name = "남길동";
        assertThat(deathByPk.getResident().getName()).isEqualTo(name);
    }
}