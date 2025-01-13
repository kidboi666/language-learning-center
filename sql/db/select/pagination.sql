SELECT email, sign_up_day
FROM member
ORDER BY sign_up_day DESC
LIMIT 9, 10;
-- LIMIT _생략할 row의 인덱스(9면 0부터 8까지는 생략)_, 보여줄 row 갯수