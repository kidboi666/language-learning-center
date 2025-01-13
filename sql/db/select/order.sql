SELECT *
FROM member
ORDER BY height DESC;
-- height 순으로 정렬.
-- ASC: 오름차순 (기본)
-- DESC: 내림차순

SELECT *
FROM member
WHERE gender = 'm'
  AND weight >= 70
ORDER BY weight;

SELECT email, sign_up_day
FROM member
ORDER BY YEAR(sign_up_day) DESC, email ASC;
-- 가입년도를 내림차순으로 정렬하고 같은 년도끼린 email 을 알파벳 오름차순으로

SELECT *
FROM member
ORDER BY CAST(age AS DECIMAL) DESC;
-- CAST(_형변환대상_ AS _형변환타입_) 일시적 형변환
-- SIGNED: 양수와 음수를 포함한 모든 정수 타입
-- DECIMAL: 소수점이 있는 수를 나타내는 타입