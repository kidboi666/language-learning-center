package com.example.basickotlin

data class User(val name: String, val age: Int)


fun main() {
  val users = listOf(User("Bob", 31), User("Charlie", 23), User("Alice", 29))

  val sortedByAge = users.sortedBy { it.age }
  println(sortedByAge)

  val sortedByName = users.sortedBy { it.name }
  println(sortedByName)
  val sortedByAgeAndName = users.sortedWith(compareBy<User> { it.age }.thenBy { it.name })
  println(sortedByAgeAndName)
}