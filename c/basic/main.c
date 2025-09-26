#include "stdio.h"
#define AREA(x) (3.141592 * (x) * (x))
#define Hap1(x,y) x + y
#define Hap2(x,y) ((x) + (y))
#define Gop1(x,y) x * y
#define Gop2(x,y) ((x) * (y))

int a;

void func3() {
  int h1, h2, g1, g2;
  h1 = 10 * Hap1(3,4);
  h2 = 10 * Hap2(3,4);
  g1 = Gop1(1 + 2, 3 + 4);
  g2 = Gop2(1 + 2, 3 + 4);
  printf("h1 = %d, h2 = %d \n", h1, h2);
  printf("g1 = %d, g2 = %d \n", g1, g2);
}

void func() {
  int a = 200;
  int b;
  int k = AREA(5);
  printf("k : %d\n", k);
  printf("b = %d\n", b);
  printf("a = %d\n", a);
}

void func2() {
  printf("a = %d\n", a);
  a = 100;
  printf("a = %d\n", a);
  func();
  int num1 = 104;
  int num2 = 0150;
  int num3 = 0x68;

  enum day { SUN, MON, TUE, WED, THU, FRI, SAT };
  enum day d1, d2;

  printf("enum : %d \n", d1);
  printf("num1에 저장된 정숫값은 : %d \n", num1);
  printf("num2에 저장된 정숫값은 : %d \n", num2);
  printf("num3에 저장된 정숫값은 : %d \n", num3);
}

int main(void) {
  func3();
}
