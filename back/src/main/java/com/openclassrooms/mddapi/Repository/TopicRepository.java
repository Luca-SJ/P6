package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    public Optional<Topic> findByName(String title);

}