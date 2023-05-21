package com.nhnacademy.resident.domain.resident;

import com.nhnacademy.resident.entity.Resident;

public interface ResidentDto {
    Long getResidentSerialNumber();
    String getName();
    String getResidentRegistrationNumber();
    Resident.Gender getGender();
}
