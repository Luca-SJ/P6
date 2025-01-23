package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Dtos.CommentDTO;

import java.util.List;

public interface ICommentService {
    List<CommentDTO> findAllByID(Long id);
    void createComment(CommentDTO comment);
}
