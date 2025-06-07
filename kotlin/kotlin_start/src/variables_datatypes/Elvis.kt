package variables_datatypes

fun main() {
    var str1: String? = "Hello Kotlin" // safe call operator
    str1 = null
    println("str1: $str1 length: ${str1?.length ?: -1}") // elvis operator
}