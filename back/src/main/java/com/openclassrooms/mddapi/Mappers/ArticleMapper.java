package com.openclassrooms.mddapi.Mappers;

import com.openclassrooms.mddapi.Dtos.ArticleDTO;
import com.openclassrooms.mddapi.Models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") // Enables integration with Spring
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "owner_name", source = "user.name")
    @Mapping(target = "created_at", source = "createdAt")
    @Mapping(target = "updated_at", source = "updatedAt")
    ArticleDTO toArticlesDTO(Article article);

    @Mapping(target = "user.id", source = "owner_id")
    @Mapping(target = "topic.id", source = "topic_id")
    Article toArticles(ArticleDTO articleDTO);

    List<ArticleDTO> toListArticlesDTO(List<Article> articles);
}
