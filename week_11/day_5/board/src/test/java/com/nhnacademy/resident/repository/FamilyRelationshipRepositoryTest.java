package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.domain.family_relationship.FamilyRelationshipDto;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.utils.FamilyRelationshipUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;

import java.security.Security;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class FamilyRelationshipRepositoryTest {

    @Autowired
    private FamilyRelationshipRepository repository;

    @Test
    void findByPk() {
        Long familySerialNumber = 1L;
        Long serialNumber = 2L;
        FamilyRelationship.Pk pk = FamilyRelationshipUtils.createPk(familySerialNumber, serialNumber);
        FamilyRelationshipDto byPk = repository.findByPk(pk);

        Assertions.assertThat(byPk.getPk().getBaseResidentSerialNumber()).isEqualTo(familySerialNumber);
        Assertions.assertThat(byPk.getPk().getFamilyResidentSerialNumber()).isEqualTo(serialNumber);
    }

    @Test
    void save() {
        //given
        Long familySerialNumber = 1L;
        String relationShip = "이루어 질수없는 사랑";
        Long serialNumber = 7L;
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(serialNumber);
        pk.setFamilyResidentSerialNumber(familySerialNumber);

        //when
        repository.save(serialNumber,familySerialNumber,relationShip);
        repository.flush();

        FamilyRelationship familyRelationship = repository.findById(pk).orElseThrow();
        //then
        Assertions.assertThat(familyRelationship.getPk().getFamilyResidentSerialNumber()).isEqualTo(familySerialNumber);
        Assertions.assertThat(familyRelationship.getPk().getBaseResidentSerialNumber()).isEqualTo(serialNumber);
        Assertions.assertThat(familyRelationship.getFamilyRelationshipCode()).isEqualTo(relationShip);
    }
}