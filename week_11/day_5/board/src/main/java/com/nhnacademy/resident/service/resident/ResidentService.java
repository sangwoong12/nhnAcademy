package com.nhnacademy.resident.service.resident;

import com.nhnacademy.resident.domain.resident.ParentDto;
import com.nhnacademy.resident.domain.resident.RegisterResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {
    ResidentDto addResident(RegisterResidentDto dto);

    ResidentDto modify(RegisterResidentDto dto, Long serialNumber);
    Page<ResidentDto> getResidentsByPage(Pageable pageable);
    ResidentPageDto getResidentReport(Long serialNumber);
    ParentDto getParent(Long serialNumber,String type);
    void deleteResident(Long serialNumber);
}
