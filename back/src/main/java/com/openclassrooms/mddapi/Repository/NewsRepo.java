package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepo extends JpaRepository<News, Long> {
    public Optional<News> findByTitle(String title);

}