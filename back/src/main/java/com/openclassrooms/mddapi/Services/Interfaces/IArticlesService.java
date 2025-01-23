package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Dtos.ArticleDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Article;

import java.util.List;

public interface IArticlesService {

    ArticleDTO findByID(Long ArticlesID);

    void deleteByID(Long ArticlesID);

    ArticleDTO updateArticlesByID(Long ArticlesID, Article articleDetails);

    void createArticles(ArticleDTO articleDTO);

    /**
     *
     * @param id Topic identifier
     * @return {@link Article} List
     */
    List<ArticleDTO> getArticlesByUserId(Long id);
}
