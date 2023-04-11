use DatamotionMovieDatabase;


#1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
SELECT ReleaseYear, Title, RunningTime , Plot FROM movie WHERE KoreanTitle = '퍼스트 맨';

#2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
SELECT Title, KoreanTitle FROM movie WHERE ReleaseYear = '2003';

#3. 영화 '글래디에이터'의 작곡가를 고르세요
SELECT Name, KoreanName FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        WHERE KoreanTitle='글래디에이터'
        AND RoleKorName='작곡';

#4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(KoreanName) as 감독_수 FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        WHERE KoreanTitle='매트릭스'
        AND RoleKorName='감독';

#5. 감독이 2명 이상인 영화를 출력하세요
SELECT Title FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        AND RoleKorName='감독'
        GROUP BY Title HAVING COUNT(Title) >= 2;

#6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
SELECT KoreanTitle FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        WHERE KoreanName = '한스 짐머'  AND WinOrNot = 'Winner';

#7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요
SELECT Title FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        WHERE KoreanName = '아놀드 슈워제네거' AND RoleKorName = '배우'
        AND Title IN (
        SELECT DISTINCT Title FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        WHERE KoreanName = '제임스 카메론');

#8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
SELECT Title FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        WHERE KoreanName = '레오나르도 디카프리오' AND RoleKorName = '배우'
        AND RunningTime >= 100;

#9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
SELECT KoreanTitle FROM movie
        INNER JOIN gradeinkorea g on movie.GradeInKoreaID = g.GradeInKoreaID
        WHERE GradeInKoreaName = '청소년 관람불가' AND Budget = (
        SELECT MAX(Budget) FROM movie
        INNER JOIN gradeinkorea g on movie.GradeInKoreaID = g.GradeInKoreaID
        WHERE GradeInKoreaName = '청소년 관람불가');

#10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오.
SELECT AVG(BoxOfficeWWGross) as 1999_이전영화_평균수익 FROM movie
            WHERE ReleaseYear <= 1999;

#11. 가장 많은 제작비가 투입된 영화를 감독한 사람은 누구입니까?
SELECT KoreanName FROM role
        INNER JOIN appear a on role.RoleID = a.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        WHERE RoleKorName = '감독'
        AND Budget = (
        SELECT MAX(Budget) FROM movie);

#12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
SELECT KoreanName FROM role
        INNER JOIN appear a on role.RoleID = a.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        WHERE RoleKorName = '감독'
        GROUP BY KoreanName HAVING SUM(Budget) = (
        SELECT SUM(Budget) FROM role
        INNER JOIN appear a on role.RoleID = a.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        WHERE RoleKorName = '감독'
        GROUP BY KoreanName HAVING SUM(Budget)
        ORDER BY SUM(Budget) desc limit 1);

#13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.

# 위처럼 할경우 가장 높은 값을 가지는 사람이 한명 이상일때 모두 출력할 수 있지만
# sql 문 이 복잡해져 아래와 같이 높은 값이 중복이 아닐경우 LIMIT 1 로 값이 나오도록 하였습니다.
SELECT KoreanName FROM role
        INNER JOIN appear a on role.RoleID = a.RoleID
        INNER JOIN person p on a.PersonID = p.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        WHERE RoleKorName = '배우'
        GROUP BY KoreanName HAVING SUM(BoxOfficeWWGross)
        ORDER BY SUM(BoxOfficeWWGross) desc limit 1;

#14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
SELECT Budget FROM movie WHERE Budget != 0 ORDER BY Budget limit 1;

#15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
SELECT AVG(BoxOfficeUSGross) as 미국내_평균_수익_0달러포함 FROM movie WHERE movie.Budget <= 50000000; # 0달러도 포함할 경우
SELECT AVG(BoxOfficeUSGross) as 미국내_평균_수익 FROM movie WHERE movie.BoxOfficeUSGross != 0 AND movie.Budget <= 50000000;

#16. 액션 장르 영화의 평균 수익을 집계하세요.
SELECT AVG(BoxOfficeWWGross) as 액션장르_평균수익 FROM movie
        INNER JOIN moviegenre m on movie.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        WHERE GenreKorName = '액션';

#17. 드라마, 전쟁 장르의 영화를 고르세요.
SELECT Title FROM movie
        INNER JOIN moviegenre m on movie.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        WHERE GenreKorName = '드라마'
        AND Title IN (
        SELECT Title FROM movie
        INNER JOIN moviegenre m on movie.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        WHERE GenreKorName = '전쟁');

#18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
SELECT Title, KoreanTitle, ReleaseYear FROM movie
        INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN person p on a.PersonID = p.PersonID
        WHERE KoreanName = '톰 행크스'
        ORDER BY RunningTime desc limit 1;

#19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
SELECT KoreanName FROM person INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        WHERE SectorKorName = '남우주연상' AND WinOrNot = 'Winner' AND RoleKorName = '배우' AND AwardKoreanTitle = '아카데미 영화제'
        GROUP BY KoreanName HAVING COUNT(SectorKorName)
        ORDER BY COUNT(SectorKorName) desc limit 1;

#20. 아카데미상을 가장 많이 수상한 배우 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
SELECT KoreanName FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        WHERE Name != 'John Doe' AND  WinOrNot = 'Winner' AND RoleKorName = '배우' AND AwardKoreanTitle = '아카데미 영화제'
        GROUP BY KoreanName HAVING COUNT(SectorKorName)
        ORDER BY COUNT(SectorKorName) desc LIMIT 1;

#21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
SELECT KoreanName FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        WHERE SectorKorName = '남우주연상' AND  WinOrNot = 'Winner' AND RoleKorName = '배우' AND AwardKoreanTitle = '아카데미 영화제'
        GROUP BY KoreanName HAVING COUNT(SectorKorName) > 1;

#23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
SELECT KoreanName FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        WHERE Name != 'John Doe' AND  WinOrNot = 'Winner' AND AwardKoreanTitle = '아카데미 영화제'
        GROUP BY KoreanName HAVING COUNT(SectorKorName)
        ORDER BY COUNT(SectorKorName) desc LIMIT 1;

#24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
SELECT Title FROM (SELECT DISTINCT a.MovieID, SectorKorName ,Title FROM movie INNER JOIN appear a on movie.MovieID = a.MovieID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        WHERE WinOrNot = 'Nominated' AND AwardKoreanTitle = '아카데미 영화제') 중복제거
        GROUP BY MovieID, Title
        ORDER BY COUNT(MovieID) desc limit 1;
# 같은 이름의 영화가 있어 따로 중복제거 : A STAR IS BORN 이 3개 존재

#25. 가장 많은 영화에 출연한 여배우를 고르세요.
SELECT Name FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        INNER JOIN role r on a.RoleID = r.RoleID
        WHERE RoleName = 'Actress' #내 2시간
        GROUP BY Name
        ORDER BY COUNT(Name) desc limit 1;

#26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
SELECT KoreanTitle FROM movie ORDER BY BoxOfficeWWGross desc limit 10;

#27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
SELECT KoreanTitle FROM movie
        WHERE BoxOfficeWWGross >= 1000000000
        AND Budget <= 100000000;

#28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
SELECT Name FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN moviegenre m on a.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        INNER JOIN movie m2 on a.MovieID = m2.MovieID
        WHERE GenreKorName = '전쟁' AND Name != 'John Doe' AND RoleKorName = '감독'
        GROUP BY Name
        ORDER BY COUNT(Title) desc limit 1;

#29. 드라마에 가장 많이 출연한 사람을 고르세요.
SELECT Name  FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN moviegenre m on a.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        INNER JOIN movie m2 on a.MovieID = m2.MovieID
        WHERE GenreKorName = '드라마' AND Name != 'John Doe'
        GROUP BY Name
        ORDER BY COUNT(Title) desc limit 1;

#30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
SELECT DISTINCT Name FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN moviegenre m on a.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        INNER JOIN movie m2 on a.MovieID = m2.MovieID
        WHERE GenreKorName = '드라마' AND Name != 'John Doe' AND RoleKorName = '배우'
        AND Name NOT IN (
        SELECT Name FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN role r on a.RoleID = r.RoleID
        INNER JOIN moviegenre m on a.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        INNER JOIN movie m2 on a.MovieID = m2.MovieID
        WHERE GenreKorName = '공포' AND Name != 'John Doe' AND r.RoleKorName = '배우'
        GROUP BY Name)
        GROUP BY Name;
# 호러라는 장르가 없어 공포로 대처

#31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
SELECT Location FROM award
        INNER JOIN awardyear a on award.AwardID = a.AwardID
        WHERE AwardKoreanTitle = '아카데미 영화제'
        GROUP BY Location
        ORDER BY COUNT(Location) desc LIMIT 1;

#33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
SELECT 2023 - Year as 년도차이 FROM award
        INNER JOIN awardyear a on award.AwardID = a.AwardID
        WHERE AwardKoreanTitle = '아카데미 영화제'
        ORDER BY Year limit 1;

#34. 아카데미에 가장 많이 노미네이트된 사람은 누구인가요?
SELECT Name FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        WHERE WinOrNot = 'Nominated' AND Name != 'John Doe' AND AwardKoreanTitle = '아카데미 영화제'
        GROUP BY Name
        ORDER BY COUNT(Name) desc LIMIT 1;

#35. 1999년에서 2009년 사이에 남우 주연상을 수상한 배우를 모두 고르세요.
SELECT DISTINCT Name FROM awardyear a
        INNER JOIN awardinvolve a2 on a.AwardYearID = a2.AwardYearID
        INNER JOIN appear a3 on a2.AppearID = a3.AppearID
        INNER JOIN person p on a3.PersonID = p.PersonID
        INNER JOIN moviegenre m on a3.MovieID = m.MovieID
        INNER JOIN genre g on m.GenreID = g.GenreID
        INNER JOIN sector s on a2.SectorID = s.SectorID
        INNER JOIN winning w on a2.WinningID = w.WinningID
        WHERE SectorKorName = '남우주연상' AND WinOrNot = 'winner'
        AND Year BETWEEN 1999 AND 2009;

#36. 아카데미를 한번 수상한 후, 가장 짧은 기간에 한번 더 수상한 사람을 고르세요.
SELECT 테이블1.Name
FROM (
    SELECT Name, Date FROM person
    INNER JOIN appear a on person.PersonID = a.PersonID
    INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
    INNER JOIN winning w on a2.WinningID = w.WinningID
    INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
    INNER JOIN sector s2 on a2.SectorID = s2.SectorID
    WHERE WinOrNot = 'winner' AND Name != 'JoHn Doe'
    GROUP BY Name, Date
    ORDER BY Name, Date) as 테이블1
INNER JOIN(
    SELECT Name, Date FROM person
    INNER JOIN appear a on person.PersonID = a.PersonID
    INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
    INNER JOIN winning w on a2.WinningID = w.WinningID
    INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
    INNER JOIN sector s2 on a2.SectorID = s2.SectorID
    WHERE WinOrNot = 'winner' AND Name != 'JoHn Doe'
    GROUP BY Name, Date
    ORDER BY Name, Date) as 테이블2
ON 테이블2.Name = 테이블1.Name
AND 테이블2.Date > 테이블1.Date
GROUP BY 테이블2.Name HAVING MIN(DATEDIFF(테이블2.Date,테이블1.Date)) = (
    SELECT MIN(DATEDIFF(테이블2.Date,테이블1.Date)) as 최소_날짜차이
        FROM (
            SELECT Name, Date FROM person
            INNER JOIN appear a on person.PersonID = a.PersonID
            INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
            INNER JOIN winning w on a2.WinningID = w.WinningID
            INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
            INNER JOIN sector s2 on a2.SectorID = s2.SectorID
            WHERE WinOrNot = 'winner' AND Name != 'JoHn Doe'
            GROUP BY Name, Date
            ORDER BY Name, Date) as 테이블1
        INNER JOIN(
            SELECT Name, Date FROM person
            INNER JOIN appear a on person.PersonID = a.PersonID
            INNER JOIN awardinvolve a2 on a.AppearID = a2.AppearID
            INNER JOIN winning w on a2.WinningID = w.WinningID
            INNER JOIN awardyear a3 on a2.AwardYearID = a3.AwardYearID
            INNER JOIN sector s2 on a2.SectorID = s2.SectorID
            WHERE WinOrNot = 'winner' AND Name != 'JoHn Doe'
            GROUP BY Name, Date
            ORDER BY Name, Date) as 테이블2
        ON 테이블2.Name = 테이블1.Name
        AND 테이블2.Date > 테이블1.Date
        GROUP BY 테이블2.Name
        ORDER BY 최소_날짜차이 LIMIT 1);
# 년마다 차이 1등 : 343 2등:348 3등:350

#37. '제임스 카메론'의 영화에 출연한 모든 배우를 고르세요
SELECT DISTINCT KoreanName FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        WHERE KoreanTitle IN
        (SELECT DISTINCT KoreanTitle FROM person
        INNER JOIN appear a on person.PersonID = a.PersonID
        INNER JOIN movie m on a.MovieID = m.MovieID
        WHERE KoreanName = '제임스 카메론');

#38. '월드 디즈니'가 수상한 아카데미상의 종류를 고르세요
SELECT DISTINCT SectorKorName FROM sector
        INNER JOIN awardinvolve a on sector.SectorID = a.SectorID
        INNER JOIN appear a2 on a.AppearID = a2.AppearID
        INNER JOIN person p on a2.PersonID = p.PersonID
        INNER JOIN winning w on a.WinningID = w.WinningID
        INNER JOIN awardyear a3 on a.AwardYearID = a3.AwardYearID
        INNER JOIN award a4 on a3.AwardID = a4.AwardID
        WHERE KoreanName = '월트 디즈니' AND WinOrNot = 'winner' AND AwardKoreanTitle = '아카데미 영화제';

#39. 장르별 영화의 제작비 평균을 구하세요.
SELECT GenreKorName , AVG(Budget) as 평균_제작비 FROM genre
        INNER JOIN moviegenre m on genre.GenreID = m.GenreID
        INNER JOIN movie m2 on m.MovieID = m2.MovieID
        GROUP BY GenreKorName HAVING AVG(Budget);

#40. 장르별 영화의 제작비 대비 수익률을 구하세요.
SELECT GenreKorName , AVG((BoxOfficeWWGross) /  Budget *100) as 수익률 FROM genre
       INNER JOIN moviegenre m on genre.GenreID = m.GenreID
       INNER JOIN movie m2 on m.MovieID = m2.MovieID
        GROUP BY GenreKorName HAVING AVG((BoxOfficeWWGross) /  Budget );