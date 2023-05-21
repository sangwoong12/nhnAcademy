package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.resident.ParentDto;
import com.nhnacademy.resident.domain.resident.ResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentPageDto;
import com.nhnacademy.resident.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    ResidentDto findByResidentSerialNumber(Long residentSerialNumber);

    Page<ResidentDto> getResidentsBy(Pageable pageable);

    @EntityGraph("ResidentWithBDReport")
    ResidentPageDto getResidentByResidentSerialNumber(Long residentSerialNumber);

    @Query(value = "select r.name as name, r.resident_registration_number as residentRegistrationNumber " +
            "from resident r " +
            "join family_relationship fr on fr.family_resident_serial_number = r.resident_serial_number " +
            "join resident r2 on r2.resident_serial_number = fr.base_resident_serial_number " +
            "where r2.resident_serial_number = :serialNumber and fr.family_relationship_code = :type", nativeQuery = true)
    ParentDto getParentByType(@Param("serialNumber") Long serialNumber, @Param("type") String type);
}
