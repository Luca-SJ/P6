package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<Article, Long> {
    public Optional<Article> findByTitle(String title);

    List<Article> findByTopicId(Long id);

}