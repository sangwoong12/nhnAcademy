use DatamotionMovieDatabase;
#1. DBManager 라는 이름을 가지는 사용자를 작성하세요.
CREATE USER DBManager identified by '12345678';

#2. User 라는 이름을 가지는 사용자를 작성하세요.
CREATE USER User identified by '12345678';

#3. DBManger 사용자는 DatamotionMovieDatabase의 모든 개체에 모든 권한을 가지되, 다른 데이터베이스에 대한 권한은 가지지 않아야 합니다.
GRANT ALL PRIVILEGES ON DatamotionMovieDatabase.* TO DBManager;

#4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
GRANT SELECT ON DatamotionMovieDatabase.* TO User;
GRANT INSERT ON DatamotionMovieDatabase.PersonTrivia TO User;
GRANT INSERT ON DatamotionMovieDatabase.MovieTrivia TO User;

use DatamotionMovieDatabase;

#5. '영화'에 출연한 '배우'와 관련된 뷰를 작성하세요.
DROP VIEW movieActor_view;
CREATE VIEW movieActor_view as
SELECT p.*, movie.* FROM movie
                             INNER JOIN appear a on movie.MovieID = a.MovieID
                             INNER JOIN person p on a.PersonID = p.PersonID
                             INNER JOIN role r on a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우';

SELECT * FROM movieActor_view;

#6. '영화'를 감독한 '감독'과 관련된 뷰를 작성하세요.
DROP VIEW movieDirector_view;

CREATE VIEW movieDirector_view as
SELECT p.*, movie.* FROM movie
                             INNER JOIN appear a on movie.MovieID = a.MovieID
                             INNER JOIN person p on a.PersonID = p.PersonID
                             INNER JOIN role r on a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독';

SELECT * FROM movieDirector_view;

#7. '아카데미 시상 결과'과 관련된 뷰를 작성하세요.
DROP VIEW academyAwardResults;

CREATE VIEW academyAwardResults as
SELECT Year,SectorKorName,Name,Location FROM awardyear
        INNER JOIN award a on awardyear.AwardID = a.AwardID
        INNER JOIN awardinvolve a2 on awardyear.AwardYearID = a2.AwardYearID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN appear a3 on a2.AppearID = a3.AppearID
        INNER JOIN person p on a3.PersonID = p.PersonID
        WHERE w.WinOrNot = 'winner';

SELECT * FROM academyAwardResults;

#8. '영화 장르별 제작비와 흥행 수익'에 관련된 뷰를 작성하세요.
DROP VIEW  genre_avg_budget_Revenue;

CREATE VIEW genre_avg_budget_Revenue as
SELECT GenreKorName, AVG(Budget) as 장르별_평균_제작비 , AVG(BoxOfficeWWGross) as 장르별_평균_수익 FROM movie
                                                                                             INNER JOIN moviegenre m on movie.MovieID = m.MovieID
                                                                                             INNER JOIN genre g on m.GenreID = g.GenreID
GROUP BY g.GenreKorName;

SELECT * FROM genre_avg_budget_Revenue;

#9. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(KoreanTitle) FROM movieDirector_view as m
WHERE m.KoreanTitle = '매트릭스';

#10. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 출력하세요.
SELECT KoreanTitle FROM movieActor_view
WHERE movieActor_view.RunningTime >= 100 AND movieActor_view.KoreanName = '레오나르도 디카프리오';

#11. '알란 실버스트리'가 음악을 작곡한 영화와 '애런 소킨'이 각본을 쓴 영화를 출력하세요. (애런 소킨 데이터를 찾아보세요) //아론 소킨...
SELECT KoreanTitle FROM movie_role AS m
WHERE (m.KoreanName='알란 실버스트리' AND m.RoleKorName='작곡')
   OR (m.KoreanName='아론 소킨' AND m.RoleKorName='각본가');

#12. '쿠엔틴 타란티노'가 감독한 영화에 출연한 배우들이 출연한 영화의 수익율을 배우별로 출력하세요.
SELECT KoreanName , KoreanTitle,  (BoxOfficeWWGross / movieActor_view.Budget * 100)
    as 수익률 FROM movieActor_view WHERE KoreanName IN
    (SELECT KoreanName FROM movieActor_view
    WHERE KoreanTitle IN (
    SELECT KoreanTitle
    FROM movieDirector_view as m
    WHERE m.KoreanName = '쿠엔틴 타란티노'));

#13. 위 문제들 중, 생성한 뷰에서 질의가 어려운 쿼리에 대한 뷰를 작성하세요.
DROP VIEW movie_role;

CREATE VIEW movie_role AS
SELECT KoreanName,RoleKorName,KoreanTitle FROM movie
                                                   INNER JOIN appear a on movie.MovieID = a.MovieID
                                                   INNER JOIN person p on a.PersonID = p.PersonID
                                                   INNER JOIN role r on a.RoleID = r.RoleID;
SELECT * FROM movie_role;

SELECT * FROM movie_role;

#14. 새로 생성한 뷰를 사용해서 쿼리를 다시 작성하세요.


#14. 사용자 테이블을 작성하세요. 사용자 테이블은 사용자의 ID, 이름, 패스워드, 전자메일 주소를 필드로 가집니다.
CREATE TABLE User (
      UserId int,
      UserName varchar(20),
      UserPassword varchar(10),
      UserEmail varchar(20),
      CONSTRAINT PRIMARY KEY (UserId)
);

DESC User;

#15. 사용자가 MovieTrivia 테이블과 PersonTrivia 테이블에 투플을 삽입할 수 있고,
# 각 투플에 삽입한 사용자를 식별할 수 있는 정보가 포함되어야 할 때, 두 테이블의 릴레이션 스키마를 수정하세요.
SELECT * FROM movietrivia;
SELECT * FROM persontrivia;

DESC movietrivia;
ALTER TABLE movietrivia ADD COLUMN UserId int;
DESC movietrivia;

DESC persontrivia;
ALTER TABLE persontrivia ADD COLUMN UserId int;
DESC persontrivia;

#16. 수정된 테이블 두 테이블(MovieTrivia, PersonTrivia)과 사용자 테이블 사이의 참조 무결성을 위한 제약조건을 설정하세요.
ALTER TABLE movietrivia ADD CONSTRAINT pk_UserId FOREIGN KEY (UserId) REFERENCES User(UserId);
ALTER TABLE persontrivia ADD CONSTRAINT pk_UserId2 FOREIGN KEY (UserId) REFERENCES User(UserId);

#17. User 사용자가 두 테이블(MovieTrivia, PersonTrivia)에 데이터를 삽입만 할 수 있도록 권한을 설정하세요.//TODO
REVOKE SELECT on MovieTrivia from User@'%';
REVOKE SELECT on persontrivia from User@'%';

GRANT INSERT ON DatamotionMovieDatabase.MovieTrivia TO User;

#18. MovieTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DROP PROCEDURE SetMovieTrivia;

DELIMITER $$
CREATE PROCEDURE SetMovieTrivia(
    TID int,
    MID int,
    UID int
)
BEGIN
INSERT INTO movietrivia (TriviaID,MovieID,movietrivia.UserId) VALUES
    (TID,MID,UID);
END $$
DELIMITER ;

#19. PersonTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DROP PROCEDURE SetPersonTrivia;
DELIMITER $$
CREATE PROCEDURE SetPersonTrivia(
    TID int,
    PID int,
    UID int
)
BEGIN
INSERT INTO PersonTrivia (TriviaID,PersonID,persontrivia.UserId) VALUES
    (TID,PID,UID);
END $$
DELIMITER ;

#20. 주어진 감독이 감독한 영화를 모두 출력하는 저장 프로시저를 작성하세요.
DROP PROCEDURE getMovie;

DELIMITER $$
CREATE PROCEDURE getMovie(
    inputName varchar(200)
)
BEGIN
SELECT KoreanTitle FROM movieDirector_view
WHERE KoreanName = inputName;
END $$
DELIMITER ;

CALL getMovie('제임스 카메론');

#21. 주어진 영화에 출연한 배우를 모두 출력하는 저장 프로시저를 작성하세요.
DROP PROCEDURE getActor;

DELIMITER $$
CREATE PROCEDURE getActor(
    inputMovie varchar(200)
)
BEGIN
SELECT DISTINCT KoreanName FROM movieActor_view
WHERE KoreanTitle = inputMovie;
END $$
DELIMITER ;

CALL getActor('터미네이터');

#22. 주어진 영화에 참여한, 감독, 각본, 배우를 제외한 모든 사람을 출력하는 저장 프로시저를 작성하세요.
DROP PROCEDURE getAllPersonExpectActorScenarioDirector;

DELIMITER $$
CREATE PROCEDURE getAllPersonExpectActorScenarioDirector(
    IN inputMovie varchar(200)
)
BEGIN
SELECT DISTINCT KoreanName FROM movie_role
WHERE RoleKorName != '감독' AND RoleKorName != '각본' AND RoleKorName != '배우'
      AND KoreanTitle = inputMovie;
END $$
DELIMITER ;

CALL getAllPersonExpectActorScenarioDirector('터미네이터');

#23. 주어진 사람이 '참여'한 영화를 출력하는 저장 프로시저를 작성하세요.
DROP PROCEDURE getMovieOfPerson;

DELIMITER $$
CREATE PROCEDURE getMovieOfPerson(
    IN inputName varchar(200)
)
BEGIN
SELECT DISTINCT KoreanTitle FROM movie_role
WHERE KoreanName = inputName;
END $$
DELIMITER ;

CALL getMovieOfPerson('제임스 카메론');

#24. 주어진 장르에 대한 제작비, 전체 수익과 수익율을 출력하는 저장 프로시저를 작성하세요. //해석: 장르의 통합 제작비, 통합 수익, 통합 제작비 와 수익에 대한 수익률
DROP PROCEDURE getMovieBudget;

DELIMITER $$
CREATE PROCEDURE getMovieBudget(
    IN inputGenre varchar(200)
)
BEGIN
SELECT DISTINCT SUM(Budget) , SUM(BoxOfficeWWGross) , (SUM(BoxOfficeWWGross)/SUM(Budget)*100)
FROM genre_By_Revenue
WHERE GenreKorName = inputGenre
GROUP BY GenreKorName;
END $$
DELIMITER ;

CALL getMovieBudget('드라마');

#<수업하지 않은 내용>
#25. 저장 프로시저의 파라미터는 출력/입력/입출력 형태의 파라미터를 가질 수 있습니다.
# 주어진 영화(MovieID)로 출연/참여자 정보를 제외한 모든 정보를 출력 파라미터로 가지고,
# 실행 결과가 출력 파라미터에 기록되도록 하는 저장 프로시저를 작성하세요.
DROP PROCEDURE getMovieAllDataByMovieId;

DELIMITER $$
CREATE PROCEDURE getMovieAllDataByMovieId(
    IN movie_id INT,

    OUT out_movie_id INT,
    OUT out_title VARCHAR(250),
    OUT out_korean_title VARCHAR(250),
    OUT out_plot VARCHAR(4000),
    OUT out_release_year SMALLINT,
    OUT out_running_time SMALLINT,
    OUT out_grade_id SMALLINT,
    OUT out_grade_in_korea_id SMALLINT,
    OUT out_poster VARCHAR(200),
    OUT out_release_date_in_korea DATE,
    OUT out_box_office_ww_gross BIGINT,
    OUT out_box_office_us_gross BIGINT,
    OUT out_budget BIGINT,
    OUT out_original_author VARCHAR(200),
    OUT out_original_source VARCHAR(400),


    OUT out_RegisteredDate DATE,
    OUT out_Status VARCHAR(10),

    OUT OriginalSourceID INT,
    OUT OriginalTitle VARCHAR(200),
    OUT OriginalKoreanTitle VARCHAR(200),
    OUT KindOfSourceId INT,
    OUT Image VARCHAR(200),
    OUT URL VARCHAR(200),
    OUT Comment VARCHAR(4000),
    OUT Year INT,
    OUT kindOfSource VARCHAR(30),

    OUT TriviaID int,
    OUT Contents VARCHAR(4000),

    OUT Rating INT,
    OUT RatingComment VARCHAR(300),
    OUT RatingURL VARCHAR(150),
    OUT RatingImage VARCHAR(50),
    OUT RatingSourceKorName VARCHAR(100),
    OUT RatingSourceName VARCHAR(100),
    OUT RatingFullMark INT
)
BEGIN
SELECT *
INTO out_movie_id,out_title, out_korean_title, out_plot,
    out_release_year, out_running_time, out_grade_id,
    out_grade_in_korea_id, out_poster, out_release_date_in_korea,
    out_box_office_ww_gross, out_box_office_us_gross,
    out_budget, out_original_author, out_original_source
FROM movie
WHERE MovieID = movie_id;

SELECT RegisteredDate, Status FROM movie
                                       INNER JOIN moviesonscreen m on movie.MovieID = m.MovieID
GROUP BY movie.MovieID
HAVING movie.MovieID = movie_id
    INTO out_RegisteredDate, out_Status;

SELECT GROUP_CONCAT(DISTINCT o.OriginalSourceID),GROUP_CONCAT(DISTINCT o.Title),
       GROUP_CONCAT(DISTINCT o.KoreanTitle), GROUP_CONCAT(DISTINCT o.KindOfSourceID),
       GROUP_CONCAT(DISTINCT o.Image), GROUP_CONCAT(DISTINCT o.URL),
       GROUP_CONCAT(DISTINCT o.Comment),GROUP_CONCAT(DISTINCT o.Year),
       GROUP_CONCAT(DISTINCT k.kindOfSource)  FROM movie
                                                       INNER JOIN movie_original mo on movie.MovieID = mo.MovieID
                                                       INNER JOIN originalsource o on mo.OriginalSourceID = o.OriginalSourceID
                                                       INNER JOIN kindofsource k on o.KindOfSourceID = k.KindOfSourceID
GROUP BY movie.MovieId
HAVING movie.MovieId = movie_id
    INTO OriginalSourceID, OriginalTitle, OriginalKoreanTitle, KindOfSourceId, Image, URL, Comment, Year, kindOfSource;

SELECT GROUP_CONCAT(DISTINCT t.TriviaID), GROUP_CONCAT(DISTINCT t.Contents)
FROM movie
         INNER JOIN movietrivia m on movie.MovieID = m.MovieID
         INNER JOIN trivia t on m.TriviaID = t.TriviaID
         INNER JOIN persontrivia p on t.TriviaID = p.TriviaID
GROUP BY movie.MovieID
HAVING movie.MovieID = movie_id
    INTO TriviaID , Contents;

SELECT GROUP_CONCAT(DISTINCT r.Rating) ,GROUP_CONCAT(DISTINCT ra.Comment),
       GROUP_CONCAT(DISTINCT ra.URL) ,GROUP_CONCAT(DISTINCT ra.image),
       GROUP_CONCAT(DISTINCT ra.SourceKorName), GROUP_CONCAT(DISTINCT ra.SourceName),
       GROUP_CONCAT(DISTINCT  ra.FullMark)
FROM movie
         INNER JOIN rating r on r.MovieID = movie.MovieID
         INNER JOIN ratingsource ra on r.RatingSourceID = ra.RatingSourceID
GROUP BY movie.MovieID
HAVING movie.MovieID = movie_id
    INTO Rating,RatingComment,RatingURL,RatingImage,RatingSourceKorName,RatingSourceName,RatingFullMark;
END $$
DELIMITER ;

SET group_concat_max_len=150000;
CALL getMovieAllDataByMovieId(1,@MovieID,@MovieTitle,@MovieKoreanTitle,
    @Plot,@ReleaseYear,@RunningTime,@GradeID,
    @GradeInKoreaID,@Poster,@ReleaseDateInKorea,@BoxOfficeWWGross,@BoxOfficeUSGross,
    @Budget,@OriginalAuthor,@OriginalSource,
@RegisteredDate,@StatusOriginalSourceID ,
    @OriginalSourceID, @Title, @KoreanTitle,
    @KindOfSourceId , @Image , @URL , @Comment ,
    @Year, @kindOfSource,@TriviaID , @Contents,
          @Rating,@RatingComment,@RatingURL,@RatingImage,
    @RatingSourceKorName,@RatingSourceName,@RatingFullMark);

SELECT @MovieID,@MovieTitle,@MovieKoreanTitle,
       @Plot,@ReleaseYear,@RunningTime,@GradeID,
       @GradeInKoreaID,@Poster,@ReleaseDateInKorea,@BoxOfficeWWGross,@BoxOfficeUSGross,
       @Budget,@OriginalAuthor,@OriginalSource,
       @RegisteredDate,@StatusOriginalSourceID ,
       @OriginalSourceID, @Title, @KoreanTitle,
       @KindOfSourceId , @Image , @URL , @Comment ,
       @Year, @kindOfSource,@TriviaID , @Contents,
       @Rating,@RatingComment,@RatingURL,@RatingImage,
       @RatingSourceKorName,@RatingSourceName,@RatingFullMark;
