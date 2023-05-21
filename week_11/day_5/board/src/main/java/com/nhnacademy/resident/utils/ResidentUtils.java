package com.nhnacademy.resident.utils;

import com.nhnacademy.resident.domain.resident.RegisterResidentDto;
import com.nhnacademy.resident.entity.Resident;

public class ResidentUtils {
    private ResidentUtils(){}

    public static Resident createByRegisterResidentDto(RegisterResidentDto dto){
        Resident resident = new Resident();
        create(resident, dto);
        return resident;
    }
    public static Resident modifyByRegisterResidentDto(Resident resident, RegisterResidentDto dto){
        create(resident, dto);
        return resident;
    }

    private static void create(Resident resident, RegisterResidentDto dto) {
        resident.setName(dto.getName());
        resident.setResidentRegistrationNumber(dto.getResidentRegistrationNumber());
        resident.setGender(dto.getGenderCode());
        resident.setBirthDate(dto.getBirthDate());
        resident.setBirthPlaceCode(dto.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(dto.getRegistrationBaseAddress());
        resident.setDeathDate(dto.getDeathDate());
        resident.setDeathPlaceCode(dto.getDeathPlaceCode());
        resident.setDeathPlaceAddress(dto.getDeathPlaceAddress());
    }
}
