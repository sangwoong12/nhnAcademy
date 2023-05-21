package com.nhnacademy.resident.service.birth_report_resident;

import com.nhnacademy.resident.domain.birth_death_report_resident.*;
import com.nhnacademy.resident.entity.BirthDeathReportResident;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.exception.AccessDeniedException;
import com.nhnacademy.resident.exception.NotFoundBirthDeathReportResidentException;
import com.nhnacademy.resident.exception.NotFoundResidentException;
import com.nhnacademy.resident.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.utils.BirthDeathReportResidentUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("birthDeathReportResidentService")
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {
    BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    ResidentRepository residentRepository;

    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathReportResidentRepository, ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }


    @Transactional
    public ReportResidentDto update(Long serialNumber, Long targetSerialNumber, UpdateReportResidentDto dto, BirthDeathReportResident.BirthDeathType type) {
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(type, targetSerialNumber);
        BirthDeathReportResident birthDeathReportResident = birthDeathReportResidentRepository.findById(pk).orElseThrow(NotFoundBirthDeathReportResidentException::new);
        if (!birthDeathReportResident.getReportResident().getResidentSerialNumber().equals(serialNumber)) {
            throw new AccessDeniedException();
        }
        BirthDeathReportResident save = birthDeathReportResidentRepository.save(BirthDeathReportResidentUtils.updateByDto(birthDeathReportResident, dto,type));
        return birthDeathReportResidentRepository.findByPk(save.getPk());
    }

    public ReportResidentDto addReportResident(Long serialNumber, RegisterReportResidentDto dto, BirthDeathReportResident.BirthDeathType type) {
        BirthDeathReportResident death = BirthDeathReportResidentUtils.createByDto(type, dto);
        Resident resident = residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
        Resident target = residentRepository.findById(dto.getTargetResidentSerialNumber()).orElseThrow(NotFoundResidentException::new);
        death.setReportResident(resident);
        death.setResident(target);
        BirthDeathReportResident save = birthDeathReportResidentRepository.save(death);
        return birthDeathReportResidentRepository.findByPk(save.getPk());
    }

    @Transactional
    public void delete(Long serialNumber, Long targetSerialNumber, BirthDeathReportResident.BirthDeathType type) {
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(type, targetSerialNumber);
        birthDeathReportResidentRepository.deleteByPkAndReportResident_ResidentSerialNumber(pk, serialNumber);
    }
    public BirthReportDto findBirthReport(Long serialNumber) {
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.출생, serialNumber);
        return birthDeathReportResidentRepository.findBirthByPk(pk);
    }
    public DeathReportDto findDeathReport(Long serialNumber) {
        BirthDeathReportResident.Pk pk = BirthDeathReportResidentUtils.createPk(BirthDeathReportResident.BirthDeathType.사망, serialNumber);
        return birthDeathReportResidentRepository.findDeathByPk(pk);
    }
}
