package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.News;
import com.openclassrooms.mddapi.Models.Topic;

import java.util.List;

public interface INewsService {
    List<News> findAll();

    News findByID(Long NewsID) throws ResourceNotFoundException;

    News findByTitle(String title) throws ResourceNotFoundException;

    void deleteByID(Long NewsID) throws ResourceNotFoundException;

    News updateNewsByID(Long NewsID, News newsDetails) throws ResourceNotFoundException;

    News updateNews(News news);

    News createNews(News news);

    /**
     *
     * @param id Topic identifier
     * @return {@link News} List
     */
    List<News> getNewsByUserId(Long id);
}
