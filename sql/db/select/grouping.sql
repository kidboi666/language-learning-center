SELECT gender, COUNT(*), AVG(height), MIN(weight)
FROM member
GROUP BY gender;

-- Aggregate function(집계 함수): 수치적인 특성을 구하는함수
-- Ex) COUNT, AVG, MIN, MAX

SELECT SUBSTRING(address, 1, 2) AS region, gender, COUNT(*)
FROM member
GROUP BY SUBSTRING(address, 1, 2), gender
HAVING region = '서울'
   AND gender = 'm'
ORDER BY SUBSTRING(address, 1, 2)
-- grouping 은 여러개의 컬럼을 기준으로 늘릴 수 있다.