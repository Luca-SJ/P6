package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.CommentDTO;
import com.openclassrooms.mddapi.Mappers.CommentMapper;
import com.openclassrooms.mddapi.Models.Article;
import com.openclassrooms.mddapi.Models.Comment;
import com.openclassrooms.mddapi.Repository.CommentRepository;
import com.openclassrooms.mddapi.Services.Interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> findAllByID(Long id) {
        List<Comment> listComments =  this.commentRepository.findByArticleId(id);

        return this.commentMapper.toListCommentDTO(listComments);
    }
    @Transactional
    public void createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toComment(commentDTO);
        commentRepository.save(comment);
    }
}
