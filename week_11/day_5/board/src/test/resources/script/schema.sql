DROP TABLE IF EXISTS resident;
DROP TABLE IF EXISTS birth_death_report_resident;
DROP TABLE IF EXISTS family_relationship;
DROP TABLE IF EXISTS household;
DROP TABLE IF EXISTS household_movement_address;
DROP TABLE IF EXISTS household_composition_resident;
DROP TABLE IF EXISTS certificate_issue;

CREATE TABLE IF NOT EXISTS resident (
    resident_serial_number INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    resident_registration_number VARCHAR(300) NOT NULL,
    gender_code VARCHAR(20) NOT NULL,
    birth_date DATETIME NOT NULL,
    birth_place_code VARCHAR(20) NOT NULL,
    registration_base_address VARCHAR(500) NOT NULL,
    death_date DATETIME NULL,
    death_place_code VARCHAR(20) NULL,
    death_place_address VARCHAR(500) NULL,
    PRIMARY KEY (resident_serial_number)
);
ALTER TABLE resident ALTER COLUMN resident_serial_number INT AUTO_INCREMENT NOT NULL;

CREATE TABLE IF NOT EXISTS birth_death_report_resident (
    resident_serial_number INT NOT NULL,
    birth_death_type_code VARCHAR(20) NOT NULL,
    report_resident_serial_number INT NOT NULL,
    birth_death_report_date DATE NOT NULL,
    birth_report_qualifications_code VARCHAR(20) NULL,
    death_report_qualifications_code VARCHAR(20) NULL,
    email_address VARCHAR(50) NULL,
    phone_number VARCHAR(20) NOT NULL,
    PRIMARY KEY (resident_serial_number, birth_death_type_code)
);

CREATE TABLE IF NOT EXISTS family_relationship (
    base_resident_serial_number INT NOT NULL,
    family_resident_serial_number INT NOT NULL,
    family_relationship_code VARCHAR(20) NOT NULL,
    PRIMARY KEY (base_resident_serial_number, family_resident_serial_number)
);

CREATE TABLE IF NOT EXISTS household (
    household_serial_number INT NOT NULL,
    household_resident_serial_number INT NOT NULL,
    household_composition_date DATE NOT NULL,
    household_composition_reason_code VARCHAR(20) NOT NULL,
    current_house_movement_address VARCHAR(500) NOT NULL,
    PRIMARY KEY (household_serial_number)
);

CREATE TABLE IF NOT EXISTS household_movement_address (
    household_serial_number INT NOT NULL,
    house_movement_report_date DATE NOT NULL,
    house_movement_address VARCHAR(500) NOT NULL,
    last_address_yn VARCHAR(1) DEFAULT 'Y' NOT NULL,
    PRIMARY KEY (household_serial_number, house_movement_report_date)
);

CREATE TABLE IF NOT EXISTS household_composition_resident (
    household_serial_number INT NOT NULL,
    resident_serial_number INT NOT NULL,
    report_date DATE NOT NULL,
    household_relationship_code VARCHAR(20) NOT NULL,
    household_composition_change_reason_code VARCHAR(20) NOT NULL,
    PRIMARY KEY (household_serial_number, resident_serial_number)
);

CREATE TABLE IF NOT EXISTS certificate_issue (
    certificate_confirmation_number BIGINT NOT NULL,
    resident_serial_number INT NOT NULL,
    certificate_type_code VARCHAR(20) NOT NULL,
    certificate_issue_date DATE NOT NULL,
    PRIMARY KEY (certificate_confirmation_number)
);

commit;

MERGE into resident values(1, '남길동', '130914-1234561', '남', '1913-09-14 07:22:00', '자택', '경기도 성남시 분당구 대왕판교로645번길', '2021-04-29 09:03:00', '주택', '강원도 고성군 금강산로 290번길');
MERGE into resident values(2, '남석환', '540514-1234562', '남', '1954-05-14 17:30:00', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);
MERGE into resident values(3, '박한나', '551022-1234563', '여', '1955-10-22 11:15:00', '병원', '서울특별시 중구 세종대로 110번길', null, null, null);
MERGE into resident values(4, '남기준', '790510-1234564', '남', '1979-05-10 20:45:00', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);
MERGE into resident values(5, '이주은', '820821-1234565', '여', '1982-08-21 01:28:00', '병원', '경기도 수원시 팔달구 효원로 1번길', null, null, null);
MERGE into resident values(6, '이선미', '851205-1234566', '여', '1985-12-05 22:01:00', '병원', '경기도 수원시 팔달구 효원로 1번길', null, null, null);
MERGE into resident values(7, '남기석', '120315-1234567', '남', '2012-03-15 14:59:00', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);

commit;

MERGE into birth_death_report_resident values (7, '출생', 4, '20120317', '부', null, 'nam@nhnad.co.kr', '010-1234-5678');
MERGE into birth_death_report_resident values (1, '사망', 2, '20200502', '비동거친족', null, null, '010-2345-6789');

commit;



MERGE into family_relationship values(1, 2, '자녀');
MERGE into family_relationship values(2, 1, '부');
MERGE into family_relationship values(2, 3, '배우자');
MERGE into family_relationship values(2, 4, '자녀');
MERGE into family_relationship values(3, 2, '배우자');
MERGE into family_relationship values(3, 4, '자녀');
MERGE into family_relationship values(4, 2, '부');
MERGE into family_relationship values(4, 3, '모');
MERGE into family_relationship values(4, 5, '배우자');
MERGE into family_relationship values(4, 7, '자녀');
MERGE into family_relationship values(5, 4, '배우자');
MERGE into family_relationship values(5, 7, '자녀');
MERGE into family_relationship values(7, 4, '부');
MERGE into family_relationship values(7, 5, '모');

commit;


MERGE into household values(1, 4, '20091002', '세대분리', '경기도 성남시 분당구 대왕판교로 645번길');

commit;


-- 7. household_movement_address 테이블 데이터 추가
MERGE into household_movement_address values(1, '20071031', '서울시 동작구 상도로 940번길', 'N');
MERGE into household_movement_address values(1, '20091031', '경기도 성남시 분당구 불정로 90번길', 'N');
MERGE into household_movement_address values(1, '20130305', '경기도 성남시 분당구 대왕판교로 645번길', 'Y');

commit;


-- 8. household_composition_resident 테이블 데이터 추가
MERGE into household_composition_resident values(1, 4, '20091002', '본인', '세대분리');
MERGE into household_composition_resident values(1, 5, '20100215', '배우자', '전입');
MERGE into household_composition_resident values(1, 7, '20120317', '자녀', '출생등록');
MERGE into household_composition_resident values(1, 6, '20151129', '동거인', '전입');

commit;


-- 9. certificate_issue 테이블 데이터 추가
MERGE into certificate_issue values(1234567891011121, 4, '가족관계증명서', '20211025');
MERGE into certificate_issue values(9876543210987654, 4, '주민등록등본', '20211025');

commit;