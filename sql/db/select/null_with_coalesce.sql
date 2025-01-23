SELECT
    coalesce(height, '값이없음'),
    coalesce(weight, '값이없음'),
    coalesce(address, '값이없음')
FROM member;
-- coalesce(첫번째 인자의 컬럼 값이 있을 경우 그 값을 리턴 하고 없으면 두번째 인자를 리턴)

select * from member where address is null; -- O
select * from member where address = null; -- X
-- null은 값이 아니기에 등호를 사용해선 어떤 값과 비교할 수 없음. is null로만 비교가 가능함.