#include "header.h"

void main() {
  int add_result, sub_result;
  printf("|%05d| \n", 1234);
  printf("|%6.2f| \n", 123.456);
  printf("|%07.2f| \n", 123.45);

  printf("10과 5를 더하면 %d 이다. \n", ADD(10, 5));
  printf("10에서 5를 빼면 %d 이다. \n", SUB(10, 5));
}