select distinct(gender) from member;
-- distinct: 특정 컬럼의 고유값들을 미리 살펴봄

select * from member;

select distinct(substring(address,1,2)) from member;
-- substring(값, 시작, 갯수): 정해진 위치의 문자열을 출력해줌

select *, length(address) from member;
-- length(): 문자열의 길이

select email, upper(email) from member;
-- upper(),lower(): 소문자 대문자 변형

select email, lpad(age, 6, '가나다') from member;
-- lpad(), rpad(): 좌우측 특정 위치에 특정 문자열을 넣어줌

select ltrim(sentence) from test;
-- ltrim(), rtrim(): 왼쪽 오른쪽 공백 삭제
-- trim(): 양쪽 공백 삭제