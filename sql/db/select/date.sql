-- 연도 추출하기.
SELECT *
FROM practice.member
WHERE YEAR (birthday) = '1992';

-- 여름에 가입한 회원들 조회하기.
SELECT *
FROM practice.member
WHERE MONTH (sign_up_day) IN (6, 7, 8);

-- 각 달의 후반부에 가입했던 회원들만 조회하기
SELECT *
FROM practice.member
WHERE dayofmonth(sign_up_day) BETWEEN 15 AND 31;

-- 날짜간의 차이 구하기 datediff(날짜 a, 날짜 b) 는 날짜 a - 날짜 b 이고 그 차이 일수를 알려줌.
SELECT email, sign_up_day, datediff(sign_up_day, '2019-01-01')
FROM practice.member;

-- 오늘 날짜를 기준으로 살펴보기
SELECT email, sign_up_day, curdate(), datediff(sign_up_day, curdate())
FROM practice.member;

-- 몇 살일때 가입했는지 가입한날 나이가 구하기.
SELECT email, sign_up_day, datediff(sign_up_day, birthday) / 365
FROM practice.member;

-- 더하는 함수 date_add()
-- 빼는 함수 data_sub()
-- 가입일 이후 300일 이후의 날짜 구하기.
SELECT email, sign_up_day, date_add(sign_up_day, INTERVAL 300 DAY)
FROM practice.member;
-- 가입일 기준 250일 이전의 날짜 구하기.
SELECT email, sign_up_day, date_sub(sign_up_day, INTERVAL 250 DAY)
FROM practice.member;

-- 1970 1.1을 기준으로 총 몇초가 지났는지 나타내는 UNIX Timestamp로 값 나타내기.
SELECT email, sign_up_day, unix_timestamp(sign_up_day)
FROM practice.member;

-- 반대로 UNIX Timestamp로 작성된 값을 날짜 형태로 바꾸기.
SELECT email, sign_up_day, from_unixtime(unix_timestamp(sign_up_day))
FROM practice.member;