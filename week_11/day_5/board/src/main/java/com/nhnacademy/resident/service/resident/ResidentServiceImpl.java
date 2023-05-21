package com.nhnacademy.resident.service.resident;

import com.nhnacademy.resident.domain.resident.ParentDto;
import com.nhnacademy.resident.domain.resident.RegisterResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentPageDto;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.NotFoundResidentException;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.utils.ResidentUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    ResidentRepository repository;

    public ResidentServiceImpl(ResidentRepository repository) {
        this.repository = repository;
    }

    public ResidentDto addResident(RegisterResidentDto dto) {
        Resident resident = ResidentUtils.createByRegisterResidentDto(dto);
        Resident save = repository.save(resident);
        return repository.findByResidentSerialNumber(save.getResidentSerialNumber());
    }

    @Transactional
    public ResidentDto modify(RegisterResidentDto dto, Long serialNumber) {
        Resident resident = repository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
        Resident save = repository.save(ResidentUtils.modifyByRegisterResidentDto(resident, dto));
        return repository.findByResidentSerialNumber(save.getResidentSerialNumber());
    }


    //view
    public Page<ResidentDto> getResidentsByPage(Pageable pageable) {
        return repository.getResidentsBy(pageable);
    }

    public ResidentPageDto getResidentReport(Long serialNumber){
        return repository.getResidentByResidentSerialNumber(serialNumber);
    }
    public ParentDto getParent(Long serialNumber,String type){
        return repository.getParentByType(serialNumber,type);
    }
    public void deleteResident(Long serialNumber){
        repository.deleteById(serialNumber);
    }
}
