//package com.nhnacademy.resident.repository.custom;
//
//import com.nhnacademy.resident.entity.HouseholdMovementAddress;
//import com.nhnacademy.resident.entity.QHouseholdMovementAddress;
//import com.nhnacademy.resident.exception.NotFoundHouseholdMovementAddressException;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import java.time.LocalDate;
//import java.util.Objects;
//
//public class HouseholdMovementAddressRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMovementAddressRepositoryCustom {
//    public HouseholdMovementAddressRepositoryImpl() {
//        super(HouseholdMovementAddress.class);
//    }
//
//    @Override
//    public void updateLastAddressBySerialNumber(Long serialNumber, LocalDate maxHouseMovementReportDate) {
//
//        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;
//        // 예외 처리
//        if (Objects.isNull(maxHouseMovementReportDate)) {
//            throw new NotFoundHouseholdMovementAddressException();
//        }
//        // 메인 UPDATE 쿼리 실행
//        update(householdMovementAddress)
//                .set(householdMovementAddress.lastAddressYn, HouseholdMovementAddress.LastAddressYn.N)
//                .where(householdMovementAddress.pk.householdSerialNumber.eq(serialNumber)
//                        .and(householdMovementAddress.pk.houseMovementReportDate.eq(maxHouseMovementReportDate)))
//                .execute();
//    }
//
//    @Override
//    public LocalDate getMaxHouseMovementReportDate(Long serialNumber) {
//        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;
//        return select(householdMovementAddress.pk.houseMovementReportDate.max())
//                .from(householdMovementAddress)
//                .where(householdMovementAddress.pk.householdSerialNumber.eq(serialNumber))
//                .fetchOne();
//    }
//}
