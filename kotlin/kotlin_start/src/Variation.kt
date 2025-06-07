fun main() {
    val number = 10
    var language = "Kotlin"
    val secondNumber = 20
    var str1: String? = "Hello"
    language = "Java"

    println("number = $number")
    println("language = $language")
    println("number + secondNumber = ${number + secondNumber}")
    str1 = null
}

typealias Username = String // 타입별칭
typealias Password = String

val user: Username = "admin"
val pass: Password = "<PASSWORD>"

