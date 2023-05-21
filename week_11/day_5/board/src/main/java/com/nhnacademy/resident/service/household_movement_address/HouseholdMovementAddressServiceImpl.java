package com.nhnacademy.resident.service.household_movement_address;


import com.nhnacademy.resident.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.resident.domain.household_movement_address.RegisterHouseholdMovementAddressDto;
import com.nhnacademy.resident.domain.household_movement_address.UpdateHouseholdMovementAddressDto;
import com.nhnacademy.resident.entity.Household;
import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import com.nhnacademy.resident.exception.NotFoundHouseholdException;
import com.nhnacademy.resident.exception.NotFoundHouseholdMovementAddressException;
import com.nhnacademy.resident.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.resident.repository.HouseholdRepository;
import com.nhnacademy.resident.utils.HouseholdMovementAddressUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service("householdMovementAddressService")
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {
    HouseholdRepository householdRepository;
    HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdMovementAddressServiceImpl(HouseholdRepository householdRepository, HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdRepository = householdRepository;
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }

    @Transactional
    public HouseholdMovementAddressDto addHouseholdMovementAddress(Long serialNumber, RegisterHouseholdMovementAddressDto dto) {
        //전 데이터 N 수정
        LocalDate maxHouseMovementReportDate = householdMovementAddressRepository.getMaxHouseMovementReportDate(serialNumber);
        householdMovementAddressRepository.updateLastAddressSetN(serialNumber, maxHouseMovementReportDate);

        Household household = householdRepository.findById(serialNumber).orElseThrow(NotFoundHouseholdException::new);
        HouseholdMovementAddress householdMovementAddress = HouseholdMovementAddressUtils.createHouseholdMovementAddressByDto(serialNumber, dto);
        householdMovementAddress.setHousehold(household);
        HouseholdMovementAddress save = householdMovementAddressRepository.save(householdMovementAddress);
        return householdMovementAddressRepository.findByPk(save.getPk());
    }

    @Transactional
    public HouseholdMovementAddressDto update(Long householdSerialNumber, String reportDate, UpdateHouseholdMovementAddressDto dto) {
        HouseholdMovementAddress.Pk pk = HouseholdMovementAddressUtils.createPk(householdSerialNumber, reportDate);
        HouseholdMovementAddress householdMovementAddress = householdMovementAddressRepository.findById(pk).orElseThrow(NotFoundHouseholdMovementAddressException::new);
        if (dto.getHouseholdMovementAddress() != null) {
            householdMovementAddress.setHouseMovementAddress(dto.getHouseholdMovementAddress());
        }
        if (dto.getLastAddressYn() != null) {
            householdMovementAddress.setLastAddressYn(dto.getLastAddressYn());
        }
        return householdMovementAddressRepository.findByPk(pk);
    }

    @Transactional
    public void delete(Long householdSerialNumber, String reportDate) {
        HouseholdMovementAddress.Pk pk = HouseholdMovementAddressUtils.createPk(householdSerialNumber, reportDate);
        householdMovementAddressRepository.deleteById(pk);
    }
}
