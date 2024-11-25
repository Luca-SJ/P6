package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.News;
import com.openclassrooms.mddapi.Models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    public Optional<News> findByTitle(String title);

    List<News> findByTopicId(Long id);

}