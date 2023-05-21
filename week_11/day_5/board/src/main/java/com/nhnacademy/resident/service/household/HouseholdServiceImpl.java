package com.nhnacademy.resident.service.household;

import com.nhnacademy.resident.domain.household.HouseholdDto;
import com.nhnacademy.resident.domain.household.RegisterHouseholdDto;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.repository.HouseholdRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.utils.HouseholdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("householdService")
public class HouseholdServiceImpl implements HouseholdService {

    HouseholdRepository householdRepository;
    ResidentRepository residentRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    public HouseholdDto addHousehold(RegisterHouseholdDto dto) {
        Household household = HouseholdUtils.createHouseholdByDto(dto);
        household.setResident(residentRepository.getReferenceById(dto.getHouseholdResidentSerialNumber()));
        Household save = householdRepository.save(household);
        return householdRepository.findByHouseholdSerialNumber(save.getHouseholdSerialNumber());
    }

    @Transactional
    public void remove(Long householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
    }
}
