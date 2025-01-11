| Method and Route   | Body or Query String                                              | Description        |
|--------------------|-------------------------------------------------------------------|--------------------|
| POST /auth/signup  | Body - { email, password }                                        | 새 유저를 가입           |
| POST /auth/signin  | Body - { email, password }                                        | 새 유저를 로그인          |
| GET /reports       | QS - make, model, year, mileage, longitude, latitude              | 차의 값어치를 측정         |
| POST /reports      | Body - { make, model, year, mileage, longitude, latitude, price } | 차량이 얼마에 팔렸는지       |
| PATCH /reports/:id | Body - { approved }                                               | 사용자의 요청을 승인하거나 거절함 |
