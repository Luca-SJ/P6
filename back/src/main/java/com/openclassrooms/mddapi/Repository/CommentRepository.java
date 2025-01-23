package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long id);
}
