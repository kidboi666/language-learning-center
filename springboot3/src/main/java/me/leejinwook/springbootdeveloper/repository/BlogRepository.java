package me.leejinwook.springbootdeveloper.repository;

import me.leejinwook.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
