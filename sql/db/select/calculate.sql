SELECT email, height, weight, weight / ((height / 100) * (height / 100))
FROM member;

SELECT email, height AS 키, weight AS 몸무게, weight / ((height / 100) * (height / 100)) AS bmi
FROM member;
-- as(alias): 별칭 (스페이스바로 공백만 줘도 가능)

SELECT email,
       CONCAT(height, 'cm', ', ', weight, 'kg')   AS '키와 몸무게',
       weight / ((height / 100) * (height / 100)) AS bmi,

       (CASE
            WHEN weight IS NULL OR height IS NULL THEN '비만 여부 알 수 없음'
            WHEN weight / ((height / 100) * (height / 100)) >= 25 THEN '과체중 또는 비만'
            WHEN weight / ((height / 100) * (height / 100)) >= 18.5
                AND weight / ((height / 100) * (height / 100)) < 25
                THEN '정상'
            ELSE '저체중'
           END)                                   AS obesity_check

FROM member
ORDER BY obesity_check DESC;