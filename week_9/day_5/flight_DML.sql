### 고객
insert into customer values(1, '여현정', '010-1111-2222');
#- 일반, 탑승안하고 다른사람 태우려고 만든
insert into customer values(2, '박상웅', '010-2222-2222');
#- 본인이 탑승할 예정

insert into customer values(3, '오도그룹', '010-3333-3333');
#- 회사
insert into customer values(4, '오도독그룹', '010-4444-4444');
#- 회사2

insert into customer values(5, '오도현', '010-5555-1111');
#- 회사1 대표, 탑승객
insert into customer values(6, '김민수', '010-5555-2222');
#- 회사1에서 예약할 탑승객, 회사 1번의 법인고객직원
insert into customer values(7, '이나얼', '010-5555-3333');
#- 회사1에서 예약할 탑승객, 회사 1번의 법인고객직원
insert into customer values(8, '장이수', '010-5555-4444');
#- 회사2 에서 예약할 탑승객, 회사 2번의 법인고객직원
insert into customer values(9, '마동석', '010-5555-5555');
#- 회사2 에서 예약할 탑승객, 회사 2번의 법인고객직원

#- 아래는 유령회원
insert into customer values(10, '오예스', '010-5555-6666');
insert into customer values(11, '이하임', '010-5555-7777');
insert into customer values(12, '페리오', '010-5555-8888');

### 회사 타입
insert into simple_member_company_info values(1, '네이버');
insert into simple_member_company_info values(2, '카카오');

### reserved_type
insert into reserved_type values (1, '왕복');
insert into reserved_type values (2, '편도');
insert into reserved_type values (3, '다구간');

### PNR
insert into PNR values(1, 1, '2023-04-28 19:00:00', 1);  # 고객1번 (여현정)
insert into PNR values(2, 2, '2023-04-29 20:00:00', 1);  # 고객2번 (박상웅)
insert into PNR values(3, 3, '2023-04-29 21:00:00', 1);  # 고객3번 (오도그룹)
insert into PNR values(4, 4, '2023-04-29 23:00:00', 1);  # 고객4번 (오도독그룹)
insert into PNR values(5, 1, '2023-01-23 19:00:00', 1);  # 고객5번 (여현정)

### PAX pax pnr name age cellphone
insert into PAX values(1, 1, '김장섭', 26, '010-5555-5555'); # 장섭 탑승 여현정 예약
insert into PAX values(2, 1, '김장순', 9, '010-6666-6666');  # 장순 탑승 여현정 예약
insert into PAX values(3, 2, '박상웅', 26, '010-2222-2222');  # 박상웅 탑승 박상웅 예약
insert into PAX values(4, 3, '오도현', 50, '010-5555-1111');  # 오도그룹 예약 오도현 탑승
insert into PAX values(5, 3, '김민수', 40, '010-5555-2222'); # 김민수 탑승 오도그룹 예약
insert into PAX values(6, 3, '이나얼', 33, '010-5555-3333'); # 이나얼 탑승 오도그룹 예약
insert into PAX values(7, 4, '장이수', 35, '010-5555-4444'); # 장이수 탑승 오도독그룹 예약
insert into PAX values(8, 4, '마동석', 35, '010-5555-5555'); # 마동석 탑승 오도독그룹 예약
insert into PAX values(9, 5, '장첸', 30, '010-5555-1234'); # 장첸 탑승 여현정 예약
insert into PAX values(10, 5, '장얼빈', 32, '010-5555-4321'); # 장얼빈 탑승 여현정 예약

### 일반 개인 회원
insert into individual_customer values(1);
insert into individual_customer values(2);
insert into individual_customer values(5);
insert into individual_customer values(6);
insert into individual_customer values(7);
insert into individual_customer values(8);
insert into individual_customer values(9);
insert into individual_customer values(10);
insert into individual_customer values(11);
insert into individual_customer values(12);

### 간편 회원
insert into simple_member values(1, 1);
insert into simple_member values(5, 2);
insert into simple_member values(7, 1);
insert into simple_member values(9, 2);
insert into simple_member values(11, 1);

### 정회원
insert into full_member values(2, 'user2', 'pwd2');
insert into full_member values(6, 'user6', 'pwd6');
insert into full_member values(8, 'user8', 'pwd8');
insert into full_member values(10, 'user10', 'pwd10');

### SSR type
insert into SSR_type values(1, 'WCHR');
insert into SSR_type values(2, 'WCHS');
insert into SSR_type values(3, 'WCHC');
insert into SSR_type values(4, 'DEAF');
insert into SSR_type values(5, 'BLND');
insert into SSR_type values(6, 'VEGM');
insert into SSR_type values(7, 'VGML');
insert into SSR_type values(8, 'DBML');
insert into SSR_type values(9, 'BBML');
insert into SSR_type values(10, 'CHML');

### customer identification
insert into customer_identification values(1, '여현정 여권', '여현정 주민등록증');
insert into customer_identification values(2, '박상웅 여권', '박상웅 주민등록증');
insert into customer_identification values(5, '오도현 여권', '오도현 주민등록증');
insert into customer_identification values(6, '김민수 여권', '김민수 주민등록증');
insert into customer_identification values(7, '이나얼 여권', '이나얼 주민등록증');
insert into customer_identification values(8, '장이수 여권', '장이수 주민등록증');

### 법인고객 정보
insert into corporation_customer values(3, '오도현', '11111-11-111111', 'OHDO');
insert into corporation_customer values(4, '오도독', '22222-22-222222', 'OHDODOK');

### 법인 우대 혜택
insert into corporation_benefit values(1, '50% 할인', 3);
insert into corporation_benefit values(2, '기내 컵라면 무료 증정', 4);

### 상품 중 부가서비스 일부  ## TODO 좌석 추가, 항공권 추가
insert into product values(1, '사전 수하물 추가', 30000, '15kg 구매');
insert into product values(2, '사전 좌석 0원', 0, '사전 좌석 미선택');
insert into product values(3, '애견여행 도시락', 12000, '제주항공 탑승 댕댕이들을 위한 하늘위 애견전용 건강간식');
insert into product values(4, '불고기덮밥', 10000, '맛있는 불고기와 익힌 채소가 함께 제공되는 덮밥');


#제주 -> 광주
insert into product values(5, 'FLY', 50000, '제주광주 이코노미'); # 항공권 + 사전좌석 0원
insert into product values(6, 'BIZ-LITE', 80000, '제주광주 비즈니스');
insert into product values(7, 'FLYBAG', 60000, '제주광주 이코노미'); # 항공권 + 사전좌석 0원 + 위탁수하물 15KG

insert into product values(8, '제주-광주 항공권', 50000, '제주광주 이코노미'); #항공권 자체
insert into product values(9, '제주-광주 항공권', 80000, '제주광주 비즈니스');

#광주 -> 제주
insert into product values(10, 'FLY', 55000, '제주광주 이코노미'); # 항공권 + 사전좌석 0원
insert into product values(11, 'BIZ-LITE', 85000, '제주광주 비즈니스');
insert into product values(12, 'FLYBAG', 65000, '제주광주 이코노미'); # 항공권 + 사전좌석 0원 + 위탁수하물 15KG

insert into product values(13, '광주-제주 항공권', 55000, '광주제주 이코노미'); #항공권 자체
insert into product values(14, '광주-제주 항공권', 85000, '광주제주 비즈니스');

insert into product values(100, '사전 좌석 10000원', 10000, '사전 좌석 선택');

### 항공권 종류
insert into airline_ticket values (8);
insert into airline_ticket values (9);
insert into airline_ticket values (13);
insert into airline_ticket values (14);

### 부가서비스 종류
insert into extra_service_type values(1, '사전수하물');
insert into extra_service_type values(2, '사전기내식');
insert into extra_service_type values(3, '사전좌석예매');

### 부가서비스 데이터 TODO 좌석 추가 후 타입 연결
insert into extra_service values(1, 1);
insert into extra_service values(2, 3);
insert into extra_service values(3, 2);
insert into extra_service values(4, 2);
insert into extra_service values(100, 3);

### 결합 상품 정보
### fly, biz, flybag
insert into combined_product values (5);
insert into combined_product values (6);
insert into combined_product values (7);

insert into combined_product values (10);
insert into combined_product values (11);
insert into combined_product values (12);

### 결합 상품 상세 정보
### 결합 상품 상세 id, 경합 상품 id, 항공권, 부가서비스, 제휴상품, 액세서리
insert into combined_product_detail values (1, 5, 8, 2, NULL, NULL);
insert into combined_product_detail values (2, 6, 9, 2, NULL, NULL);
insert into combined_product_detail values (3, 7, 8, 2, NULL, NULL);

insert into combined_product_detail values (4, 10, 13, 2, NULL, NULL);
insert into combined_product_detail values (5, 11, 14, 2, NULL, NULL);
insert into combined_product_detail values (6, 12, 13, 2, NULL, NULL);

### 법인 고객 직원
insert into corporation_customer_employee values (6,3);
insert into corporation_customer_employee values (7,3);
insert into corporation_customer_employee values (8,4);
insert into corporation_customer_employee values (9,4);

### 승무원 id, pnr 번호
### 1번 pnr
insert into SEG values (1,1); # 기장
insert into SEG values (2,1); # SSR 보행담당
insert into SEG values (3,1); # 승무원
insert into SEG values (4,1); # 화물
insert into SEG values (5,1); # 기내식 담당
### 2번 pnr
insert into SEG values (6,2);
insert into SEG values (7,2);
insert into SEG values (8,2);
insert into SEG values (9,2);
insert into SEG values (10,2);
### 3번 pnr
insert into SEG values (11,3);
insert into SEG values (12,3);
insert into SEG values (13,3);
insert into SEG values (14,3);
insert into SEG values (15,3);

### 부가서비스 예약내역
### 부가서비스키, 직원정보 관리키, 탑승자키, 부가서비스상품키
insert into extra_service_reserved values (1,5,1,3); ##  부가서비스키, 기내식직원, 김장섭, 애견도시락 주문
insert into extra_service_reserved values (2,5,3,4); ##  부가서비스키, 기내식직원, 박상웅, 불고기덮밥 주문
insert into extra_service_reserved values (3,4,3,1); ##  부가서비스키, 화물직원, 박상웅, 사전수하물 15키로 신청
insert into extra_service_reserved values (4, 5, 1, 2);
insert into extra_service_reserved values (5, 5, 1, 2);
insert into extra_service_reserved values (6, 5, 2, 2);
insert into extra_service_reserved values (7, 5, 2, 2);


### SSR 특수주문
### 직원 정보, 탑승자키, 특수주문타입
insert into SSR values (2, 1, 5); ## SSR 보행담당 직원, 김장섭, 시각장애 도움 요청
insert into SSR values (5, 3, 7);  ## 기내식 담당 직원, 박상웅, 채식 식사 요청

##
Insert into crew_type values (1,'기내승무원');
Insert into crew_type values(2,'기장');
Insert into crew_type values(3,'부기장');
Insert into crew_type values(4,'승무원');
Insert into crew_type values(5,'캐빈크루');
Insert into crew_type values(6,'항법사');

Insert into crew values(1, '김지훈', 2);
Insert into crew values(2, '박승현', 3);
Insert into crew values(3, '주현승', 6);
Insert into crew values(4, '박지영', 4);
Insert into crew values(5, '김혜림', 5);
Insert into crew values(6, '신승재', 1);

Insert into crew_flight_plan values (1,'1번 승무원 그룹');
Insert into crew_flight_plan values (2,'2번 승무원 그룹');

Insert into crew_organization values(1,1,1);
Insert into crew_organization values(1,2,1);
Insert into crew_organization values(1,3,1);
Insert into crew_organization values(2,4,2);
Insert into crew_organization values(2,5,2);
Insert into crew_organization values(2,6,2);

Insert into airport values(1,'ICN','RKSI','인천');
Insert into airport values(2,'KJW','RKJJ','광주');
Insert into airport values(3,'MWX','RKJB','무안');
Insert into airport values(4,'FUK','RJFF','후쿠오카');
Insert into airport values(5,'CDG','LFPG','파리');
Insert into airport values(6,'JFK','KJFK','뉴욕');
Insert into airport values(7,'KIX','RJBB','오사카');
Insert into airport values(8,'CJU','RKPC','제주');

## TODO flight path 이후 추가
INSERT into flight_path values (1, '제주-광주 HANLA-MUDEUNG AIR 03 N APPR', 8, 2);
INSERT into flight_path values (2, '인천-후쿠오카 INC-FUK AIR 01 S APPR RW02', 1, 4);
INSERT into flight_path values (3, '파리-인천 CDG-INC AIR 03 W APPR RW03L', 5, 1);
INSERT into flight_path values (4, '무안-오사카 MWX-FUK AIR O3 E APPR RW02', 3, 7);
INSERT into flight_path values (5, '광주-제주 HANLA-MUDEUNG AIR 03 N APPR', 2, 8);

Insert into departure_arrival values(1,'3000');
Insert into departure_arrival values(2,'325');
Insert into departure_arrival values(3,'2535');
Insert into departure_arrival values(4,'3333');
Insert into departure_arrival values(5,'3000');

Insert into airline values(1,'제주항공', 'JJU');
Insert into airline values(2,'루프트한자', 'RUF');
Insert into airline values(3,'에티오피아', 'ETI');
Insert into airline values(4,'팬암', 'PEN');
Insert into airline values(5,'KLM', 'KLM');
Insert into airline values(6,'피치', 'PEC');
Insert into airline values(7,'G7', 'G7A');
Insert into airline values(8,'아시아나', 'ASI');
Insert into airline values(9,'티웨이', 'TWA');

Insert into aircraft values(1,'HL4525','BOEING 737',270);
Insert into aircraft values(2,'HL2884','BOEING 777',370);
Insert into aircraft values(3,'HG1444','AIRBUSA330',290);
Insert into aircraft values(4,'HK4166','MRJ',30);
Insert into aircraft values(5,'AG3333','BONBARDIER',111);

# seat_grade
Insert into seat_grade values(1, '이코노미');
Insert into seat_grade values(2, '비지니스');

# 이코노미
INSERT INTO seat (seat_id, seat_name, seat_grade_id, aircraft_id)
VALUES
(1, '1-A', 1, 1),
(2, '1-B', 1, 1),
(3, '1-C', 1, 1),
(4, '1-D', 1, 1),
(5, '1-E', 1, 1),
(6, '1-F', 1, 1),
(7, '2-A', 1, 1),
(8, '2-B', 1, 1),
(9, '2-C', 1, 1),
(10, '2-D', 1, 1),
(11, '2-E', 1, 1),
(12, '2-F', 1, 1),
(13, '3-A', 1, 1),
(14, '3-B', 1, 1),
(15, '3-C', 1, 1),
(16, '3-D', 1, 1),
(17, '3-E', 1, 1),
(18, '3-F', 1, 1),
(19, '4-A', 1, 1),
(20, '4-B', 1, 1),
(21, '4-C', 1, 1),
(22, '4-D', 1, 1),
(23, '4-E', 1, 1),
(24, '4-F', 1, 1),
(25, '5-A', 1, 1),
(26, '5-B', 1, 1),
(27, '5-C', 1, 1),
(28, '5-D', 1, 1),
(29, '5-E', 1, 1),
(30, '5-F', 1, 1);

# 비지니스
INSERT INTO seat (seat_id,  seat_name, seat_grade_id, aircraft_id)
VALUES
(31, '2-AA', 2, 1),
(32, '2-BB', 2, 1),
(33, '2-CC', 2, 1),
(34, '2-DD', 2, 1),
(35, '2-EE', 2, 1),
(36, '2-FF', 2, 1),
(37, '3-AA', 2, 1),
(38, '3-BB', 2, 1),
(39, '3-CC', 2, 1),
(40, '3-DD', 2, 1),
(41, '3-EE', 2, 1),
(42, '3-FF', 2, 1);

#운항계획
#운항계획_아이디, 항공기 키, 운항 경로, 출발시간, 도착시간, 시즌구분코드, 승무원 비행계획 아이디, 항공사, 편명
# 1, BOEING 737, 제주-광주 , 시간, 시간, 시즌, 1번 승무원 그룹, 제주항공, 편명
# 2, BOEING 737, 광주-제주 , 시간, 시간, 시즌, 2번 승무원 그룹, 제주항공, 편명
INSERT INTO flight_plan values (1, 1, 5, '2023-05-01 10:00:00', '2023-05-01 13:00:00', 'Summer', 1, 1, '7C1154');
INSERT INTO flight_plan values (2, 2, 1, '2023-05-02 14:00:00', '2023-05-02 17:00:00', 'Summer', 2, 1, '7C1123');


#좌석 재고 관리
#운항계획, 좌석 등급,  좌석수, 상품키
INSERT INTO seat_count values (1, 1, 30, 8); # 1번 운항계획(제주-광주), 이코노미, 30좌석, 8번 제주광주 이코노미상품
INSERT INTO seat_count values (1, 2, 12, 9); # 1번 운항계획(제주-광주), 비지니스, 12좌석, 9번 제주광주 비즈니스 상품
INSERT INTO seat_count values (2, 1, 30, 13);
INSERT INTO seat_count values (2, 2, 12, 14);

#  PAX_id         PNR_id         product_id
INSERT INTO ticket values (1, 1, 5);  # 김장섭, 여현정, 5번 FLY 제주-광주
INSERT INTO ticket values (1, 1, 10);  # 김장섭, 여현정, 10번 FLY 광주-제주
INSERT INTO ticket values (2, 1, 5);  # 김장순, 여현정, 5번 FLY 제주-광주
INSERT INTO ticket values (2, 1, 10);  # 김장순, 여현정, 10번 FLY 광주-제주

INSERT INTO ticket values (4, 3, 6);  # 오도현, 오도그룹,  6번 BIZ 제주-광주
INSERT INTO ticket values (4, 3, 11);  # 오도현, 오도그룹,  11번 BIZ 광주-제주

INSERT INTO ticket values (3, 2, 7);  # 박상웅, 박상웅,  7번 FLYBAG 제주-광주
INSERT INTO ticket values (3, 2, 12);  # 박상웅, 박상웅,  12번 FLYBAG 광주-제주

### 운임 내역

INSERT INTO fare values (1, 1, 5, '항공운임', 40000);
INSERT INTO fare values (1, 1, 10, '항공운임',45000);
INSERT INTO fare values (2, 1, 5, '항공운임', 40000);
INSERT INTO fare values (2, 1, 10, '항공운임', 45000);

INSERT INTO fare values (4, 3, 6, '항공운임', 65000);  # 오도현, 오도그룹,  6번 BIZ 제주-광주
INSERT INTO fare values (4, 3, 11, '항공운임', 70000);  # 오도현, 오도그룹,  11번 BIZ 광주-제주

INSERT INTO fare values (3, 2, 7, '항공운임', 48000);  # 박상웅, 박상웅,  7번 FLYBAG 제주-광주
INSERT INTO fare values (3, 2, 12, '항공운임', 53000);  # 박상웅, 박상웅,  12번 FLYBAG 광주-제주

### 세금 타입

INSERT INTO tax_type values (1, '세금');

### 세금 내역

INSERT INTO tax values (1, 1, 5, 1, 10000);
INSERT INTO tax values (1, 1, 10, 1, 10000);
INSERT INTO tax values (2, 1, 5, 1, 10000);
INSERT INTO tax values (2, 1, 10, 1, 10000);

INSERT INTO tax values (4, 3, 6, 1, 15000);
INSERT INTO tax values (4, 3, 11, 1, 15000);

INSERT INTO tax values (3, 2, 7, 1, 12000);
INSERT INTO tax values (3, 2, 12, 1, 12000);

### 결제수단 타입

INSERT INTO payment_type values (1, '네이버페이');
INSERT INTO payment_type values (2, '신용카드');
INSERT INTO payment_type values (3, '계좌이체');

### 결제수단

INSERT INTO payment values (1, 1, 5, 1, 'QWERTYUIOP', '2023-01-01', 50000);
INSERT INTO payment values (1, 1, 10, 1, 'QWERTYUIOP', '2023-01-01', 55000);
INSERT INTO payment values (2, 1, 5, 2, 'POIUYTREWQ', '2023-02-01', 50000);
INSERT INTO payment values (2, 1, 10, 2, 'POIUYTREWQ', '2023-02-01', 55000);

INSERT INTO payment values (4, 3, 6, 2, 'ASDFGHJKL' , '2023-03-22', 80000);
INSERT INTO payment values (4, 3, 11, 2, 'LKJHGFDSA', '2023-03-22', 85000);

INSERT INTO payment values (3, 2, 7, 3, 'ZXCVBNM', '2023-05-01', 60000);
INSERT INTO payment values (3, 2, 12, 3, 'MNBVCXZ', '2023-05-01', 65000);


##------------------------------------------------------------------------------------------------------------##

### 예약 내역 HEADER
DROP PROCEDURE IF EXISTS GET_PNR_HEADER_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_PNR_HEADER_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN
    SELECT DISTINCT P.PNR_id as '예약번호', reserved_time as '예약일', rt.reserved_type_name as '구분'
    FROM PNR
             JOIN PAX P on PNR.PNR_id = P.PNR_id
             JOIN reserved_type rt on PNR.reserved_type_id = rt.reserved_type_id
    WHERE P.PNR_id = IN_PNR_ID;
end $$
DELIMITER ;

CALL GET_PNR_HEADER_BY_PNR_ID(1);

### 여정 정보
DROP PROCEDURE IF EXISTS GET_PNR_TRAVEL_INFO_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_PNR_TRAVEL_INFO_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN
    SELECT DISTINCT
        fp.flight_plan_name as '편명',
           p3.name             as '상품 이름',
           fp.arrival_time     as '출발시간',
           fp.departure_time   as '도착시간',
           aa.name             as '출발지',
           ad.name             as '목적지'
    FROM PNR
             JOIN PAX P on PNR.PNR_id = P.PNR_id
             JOIN ticket t on P.PAX_id = t.PAX_id
             JOIN product p2 on t.product_id = p2.product_id
             JOIN combined_product cp on p2.product_id = cp.combined_product_id
             JOIN combined_product_detail cpd on cp.combined_product_id = cpd.combined_product_id
             JOIN product p3
                  on p3.product_id = cpd.airline_ticket_id # 무조건 결합 상품이기 때문에 결합 상품내에 있는 airline_ticket 을 통해 product 찾아야함.
             JOIN seat_count s on s.product_id = p3.product_id
             JOIN flight_plan fp on s.flight_plan_id = fp.flight_plan_id
             JOIN flight_path f on fp.flight_path_id = f.flight_path_id
             JOIN airport aa on f.arrival_airport_id = aa.airport_id #출발 공항의 이름을 얻기위한 조인
             JOIN airport ad on f.departure_airport_id = ad.airport_id #도착 공항의 이름을 얻기위한 조인
    WHERE P.PNR_id = IN_PNR_ID;
end $$
DELIMITER ;

CALL GET_PNR_TRAVEL_INFO_BY_PNR_ID(1);

###항공 운송료
DROP PROCEDURE IF EXISTS GET_PNR_FARE_INFO_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_PNR_FARE_INFO_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN
    SELECT SUM(tax) as '세금 합계', SUM(fare_price) as '운임 가격 합계'
    FROM PNR
             JOIN PAX P on PNR.PNR_id = P.PNR_id
             JOIN ticket t on P.PAX_id = t.PAX_id and P.PNR_id = t.PNR_id
             JOIN tax t2 on t.PAX_id = t2.PAX_id and t.PNR_id = t2.PNR_id and t.product_id = t2.product_id
             JOIN tax_type tt on t2.tax_type_id = tt.tax_id
             JOIN fare f on t.PAX_id = f.PAX_id and t.PNR_id = f.PNR_id and t.product_id = f.product_id
    WHERE P.PNR_id = IN_PNR_ID
    GROUP BY PNR.PNR_id
    HAVING SUM(tax)
       AND SUM(fare_price);
end $$

CALL GET_PNR_FARE_INFO_BY_PNR_ID(1);

###탑승객 정보
DROP PROCEDURE IF EXISTS GET_PAX_NAME_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_PAX_NAME_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN

SELECT P.name FROM PNR
JOIN PAX P on PNR.PNR_id = P.PNR_id
    WHERE P.PNR_id = IN_PNR_ID;
end $$

CALL GET_PAX_NAME_BY_PNR_ID(1);


### 결제 서비스 내역

DROP PROCEDURE IF EXISTS GET_PAYMENT_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_PAYMENT_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN
    SELECT payment_type.payment_type_value as '결제수단', p2.payment_approval_no as '승인번호',  p2.payment_date as '결제일', SUM(p2.payment_price) as '총금액' FROM PNR
        JOIN PAX P on PNR.PNR_id = P.PNR_id
             JOIN ticket t on P.PAX_id = t.PAX_id and P.PNR_id = t.PNR_id
             JOIN payment p2 on t.PAX_id = p2.PAX_id and t.PNR_id = p2.PNR_id and t.product_id = p2.product_id
             JOIN payment_type on p2.payment_type_id = payment_type.payment_type_id
        WHERE PNR.PNR_id = IN_PNR_ID
    GROUP BY p2.payment_approval_no , payment_type.payment_type_value , p2.payment_date
    having SUM(p2.payment_price);
end $$

CALL GET_PAYMENT_BY_PNR_ID(1);

# 부가 서비스
DROP PROCEDURE IF EXISTS GET_ADDITION_SERVICE_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_ADDITION_SERVICE_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN
    SELECT p2.name as '부가서비스 이름' , p2.price as '가격' FROM PNR
        JOIN PAX P on PNR.PNR_id = P.PNR_id
    JOIN extra_service_reserved esr on P.PAX_id = esr.PAX_id
    JOIN product p2 on p2.product_id = esr.product_id
    WHERE PNR.PNR_id = IN_PNR_ID;
end $$

CALL GET_ADDITION_SERVICE_BY_PNR_ID(1);

### 항공 운송료 상세

DROP PROCEDURE IF EXISTS GET_PNR_FARE_INFO_DETAIL_BY_PNR_ID;

DELIMITER $$
CREATE PROCEDURE GET_PNR_FARE_INFO_DETAIL_BY_PNR_ID(
    IN_PNR_ID int
)
BEGIN
    SELECT P.name as '이름', SUM(tax) as '세금 합계', SUM(fare_price) as '운임 가격 합계'
    FROM PNR
             JOIN PAX P on PNR.PNR_id = P.PNR_id
             JOIN ticket t on P.PAX_id = t.PAX_id and P.PNR_id = t.PNR_id
             JOIN tax t2 on t.PAX_id = t2.PAX_id and t.PNR_id = t2.PNR_id and t.product_id = t2.product_id
             JOIN tax_type tt on t2.tax_type_id = tt.tax_id
             JOIN fare f on t.PAX_id = f.PAX_id and t.PNR_id = f.PNR_id and t.product_id = f.product_id
    WHERE P.PNR_id = IN_PNR_ID
    GROUP BY P.PAX_id, P.name
    HAVING SUM(tax)
       AND SUM(fare_price);
end $$

CALL GET_PNR_FARE_INFO_DETAIL_BY_PNR_ID(1);