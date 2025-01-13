-- 소괄호안에 값과 일치하는지를 의미.
SELECT *
FROM member
WHERE age IN (20, 30);

-- !=, <> 는 같지 않음을 의미.
SELECT *
FROM member
WHERE gender != 'm';

-- _언더 하이픈은 글자 하나를 의미.
SELECT *
FROM member
WHERE email LIKE 'c_____@%';

-- 두가지 조건을 모두 만족시키는 AND
SELECT *
FROM practice.member
WHERE gender = 'm'
  AND address LIKE '서울%'
  AND age BETWEEN 25 AND 29;

-- 둘중 하나만 만족하면 되는 OR
SELECT *
FROM practice.member
WHERE MONTH(sign_up_day) BETWEEN 3
    AND 5
   OR MONTH(sign_up_day) BETWEEN 9
    AND 11;

-- 우선순위는 AND가 OR보다 높다.
SELECT *
FROM practice.member
WHERE (gender = 'f' OR age < 30)
  AND height > 180;

SELECT *
FROM practice.member
WHERE age BETWEEN 20 AND 29
  AND MONTH(sign_up_day) IN (7);

-- LIKE: 문자열 패턴 매칭 조건을 걸기 위해 사용되는 키워드
-- %: 임의의 길이를 가진 문자열(0자도 포함)
-- _: 한 자리의 문자

-- 문자로서의 의미를 주고 싶을떈 이스케이프!
SELECT *
FROM test
WHERE sentence LIKE '%\%%';

-- 문자의 대소문자 구별을 하라는 의미의 binary
SELECT *
FROM test
WHERE sentence LIKE BINARY '%G%'