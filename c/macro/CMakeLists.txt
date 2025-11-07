cmake_minimum_required(VERSION 4.0)
project(macro C)

set(CMAKE_C_STANDARD 11)

add_executable(macro main.c
        header.h)
