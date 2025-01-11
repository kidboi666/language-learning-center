package me.leejinwook.springbootkotlin.core.exception

class InvalidInputException (
    message: String = "Invalid Input"
) : RuntimeException(message)