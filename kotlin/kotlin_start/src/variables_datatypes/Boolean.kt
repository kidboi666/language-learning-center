package variables_datatypes

import java.util.Scanner

fun main() {
    val isOpen: Boolean = true
    val isUploaded: Boolean = false
    val ch2: Char = 65.toChar()

    val code: Int = 65
    val chFromCode: Char = code.toChar()
    println(chFromCode) // A
    println(ch2) // A

    val scan = Scanner(System.`in`)
    print("Enter a character: ")
    val str: String = scan.next()
    println("You entered: $str")
}