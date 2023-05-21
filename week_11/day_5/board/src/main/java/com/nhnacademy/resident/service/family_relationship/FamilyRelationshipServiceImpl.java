package com.nhnacademy.resident.service.family_relationship;

import com.nhnacademy.resident.domain.family_relationship.RegisterFamilyRelationDto;
import com.nhnacademy.resident.domain.family_relationship.FamilyRelationDto;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.exception.NotFoundFamilyRelationshipException;
import com.nhnacademy.resident.repository.FamilyRelationshipRepository;
import com.nhnacademy.resident.utils.FamilyRelationshipUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("familyRelationshipService")
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {

    FamilyRelationshipRepository repository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public FamilyRelationDto addFamilyRelationship(Long serialNumber, RegisterFamilyRelationDto dto) {
        FamilyRelationship.Pk pk = FamilyRelationshipUtils.createPk(serialNumber, dto.getFamilySerialNumber());
        repository.save(serialNumber, dto.getFamilySerialNumber(), dto.getRelationShip());
        return FamilyRelationDto.createByFamilyRelationDto(repository.findByPk(pk));
    }
    @Transactional
    public void delete(Long serialNumber, Long familySerialNumber) {
        FamilyRelationship.Pk pk = FamilyRelationshipUtils.createPk(serialNumber, familySerialNumber);
        repository.deleteById(pk);
    }

    @Transactional
    public FamilyRelationDto update(Long serialNumber, Long familySerialNumber, String relationShip) {
        FamilyRelationship.Pk pk = FamilyRelationshipUtils.createPk(serialNumber, familySerialNumber);
        FamilyRelationship familyRelationship = repository.findById(pk).orElseThrow(NotFoundFamilyRelationshipException::new);
        familyRelationship.setFamilyRelationshipCode(relationShip);
        return FamilyRelationDto.createByEntity(repository.save(familyRelationship));
    }
}
