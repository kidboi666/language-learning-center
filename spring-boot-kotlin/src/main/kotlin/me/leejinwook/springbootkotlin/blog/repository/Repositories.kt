package me.leejinwook.springbootkotlin.blog.repository

import me.leejinwook.springbootkotlin.blog.entity.Wordcount
import org.springframework.data.repository.CrudRepository

interface WordRepository : CrudRepository<Wordcount, String> {
    fun findTop10ByOrderByCntDesc(): List<Wordcount>
}