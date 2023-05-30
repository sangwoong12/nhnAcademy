package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.domain.certficate_issue.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CertificateIssueRepositoryTest {

    @Autowired
    CertificateIssueRepository repository;

    @Test
    void findByCertificateConfirmationNumber() {
        // given
        Long certificateConfirmationNumber = 9876543210987654L;
        // when
        CertificateIssueDto byCertificateConfirmationNumber = repository.findByCertificateConfirmationNumber(certificateConfirmationNumber);
        // then
        assertThat(byCertificateConfirmationNumber.getCertificateConfirmationNumber()).isEqualTo(certificateConfirmationNumber);
    }

    @Test
    @DisplayName("등록자로 찾았을때 총 길이가 일치하는지 테스트")
    void findAllByResidentResidentSerialNumber() {
        // given
        Long serialNumber = 4L;
        // when
        List<CertificateIssueListDto> allByResidentResidentSerialNumber = repository.findAllByResidentResidentSerialNumber(serialNumber);
        //then
        assertThat(allByResidentResidentSerialNumber.size()).isEqualTo(2);
    }

    @Test
    void getResidentCertificateTop() {
        // given
        Long certificateConfirmationNumber = 9876543210987654L;
        // when
        CertificateTopDto residentCertificateTop = repository.getResidentCertificateTop(certificateConfirmationNumber);
        // then
        assertThat(residentCertificateTop.getCertificateConfirmationNumber()).isEqualTo(certificateConfirmationNumber);
    }

    @Test
    void getResidentCertificateMiddle() {
        // given
        Long certificateConfirmationNumber = 9876543210987654L;
        // when
        List<CertificateMiddleDto> residentCertificateMiddle = repository.getResidentCertificateMiddle(certificateConfirmationNumber);
        // then
        assertThat(residentCertificateMiddle.size()).isEqualTo(3);
    }

    @Test
    void getResidentCertificateBottom() {
        // given
        Long certificateConfirmationNumber = 9876543210987654L;
        // when
        List<CertificateBottomDto> residentCertificateBottom = repository.getResidentCertificateBottom(certificateConfirmationNumber);
        // then
        assertThat(residentCertificateBottom.size()).isEqualTo(4);
    }
}