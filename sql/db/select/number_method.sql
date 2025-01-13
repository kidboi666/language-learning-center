-- SUM() 함수 - 합계
SELECT SUM(age)
FROM member;

-- STD() 함수 - 표준편차
SELECT STD(age)
FROM member;

-- CEIL() 함수 - 올림 함수
SELECT CEIL(height)
FROM member;
-- ABS() - 절대값을 구하는 함수
-- SQRT() - 제곱근을 구하는 함수
-- CEIL() - 올림 함수

-- FLOOR() 함수 - 내림 함수
SELECT FLOOR(height)
FROM member;

-- ROUND() 함수 - 반올림 함수
SELECT ROUND(height)
FROM member;

-- 집계 함수 : 특정 컬럼의 여러 row의 값들을 동시에 고려해서 실행되는 함수
-- 산술 함수 : 특정 컬럼의 각 row의 값마다 실행되는 함수