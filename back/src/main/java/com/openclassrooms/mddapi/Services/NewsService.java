package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.News;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepo newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public News findByID(Long NewsID) throws ResourceNotFoundException {
        return newsRepository.findById(NewsID)
                .orElseThrow(()->new ResourceNotFoundException("Article inexistant"));
    }

    public News findByTitle(String title) throws ResourceNotFoundException {
        return newsRepository.findByTitle(title)
                .orElseThrow(()->new ResourceNotFoundException("Article inexistant"));
    }

    public void deleteByID(Long NewsID) throws ResourceNotFoundException {
        News news = newsRepository.findById(NewsID)
                .orElseThrow(()->new ResourceNotFoundException("Article avec ID : " + NewsID + " inexistant"));
        newsRepository.delete(news);
    }

    public News updateNewsByID(Long NewsID, News newsDetails) throws ResourceNotFoundException {
        News news = newsRepository.findById(NewsID)
                .orElseThrow(()->new ResourceNotFoundException("Article avec ID : "+ NewsID + " inexistant"));

        news.setTitle(newsDetails.getTitle());
        news.setDescription(newsDetails.getDescription());
        news.setPicture(newsDetails.getPicture());
        news.setOwner_id(newsDetails.getOwner_id());
        news.setCreated_at(newsDetails.getCreated_at());
        news.setUpdated_at(newsDetails.getUpdated_at());

        return newsRepository.save(news);
    }

    public News createNews(News news) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        news.setCreated_at(sqlDate);
        news.setDescription(news.getDescription());
        news.setTitle(news.getTitle());
        news.setOwner_id(news.getOwner_id());
        news.setPicture(news.getPicture());

        return newsRepository.save(news);
    }
}
