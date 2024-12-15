package me.leejinwook.springbootkotlin.core.response

data class ErrorResponse (
    val message: String,
    val errorType: String = "Invalid Argument"
)
