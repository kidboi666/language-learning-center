#include <stdio.h>
#define IN "원의 반지름은 100입니다. \n"
#define PI 3.1415926535
#define AREA(X) (PI*(X)*(X))
#define OUT printf("원의 면적을 구하고 프로그램을 종료합니다. \n")

int main(void) {
  printf("%s \n", IN);
  printf("원의 면적은 %10.5f 입니다. \n\n", AREA(100));
  OUT;
}