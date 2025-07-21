package com.example.basickotlin

@JvmInline
value class UserId(val id: Int)

@JvmInline
value class ProductId(val id: Int)

fun findUser(id: UserId) {}
fun findProduct(id: ProductId) {}

fun main() {
//  val numbers = mutableListOf(0,1,2,3,4,5)
//
//  println(numbers)
//  for (index in 0 until numbers.size) {
//    println(numbers[index])
//    numbers[index] = numbers[index] * 2
//    println(numbers[index])
//  }
//  println(numbers)

  val userId = UserId(123)
  val productId = ProductId(456)

  findUser(userId)
  findProduct(productId)

//  findUser(productId)
//  findProduct(userId)

}