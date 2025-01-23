package com.openclassrooms.mddapi.Mappers;

import com.openclassrooms.mddapi.Dtos.CommentDTO;
import com.openclassrooms.mddapi.Models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") // Enables integration with Spring
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(target = "articleID", source = "articleId")
    @Mapping(target = "userID", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    CommentDTO toCommentDTO(Comment comment);
    @Mapping(source = "articleID", target = "articleId")
    @Mapping(source = "userID", target = "user.id")
    Comment toComment(CommentDTO commentDTO);

    List<CommentDTO> toListCommentDTO(List<Comment> comment);
}
