package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.News;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Repository.NewsRepository;
import com.openclassrooms.mddapi.Repository.SubscriptionRepository;
import com.openclassrooms.mddapi.Services.Interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

//    public List<News> getNewsList(Date created_at, String size, String title, long owner_id, String picture, Date updated_at) {
//
//        PageRequest pageReq = PageRequest.of(created_at, size, Sort.Direction.fromString(sortDir), sort);
//
//        Page<News> news = newsRepository.findByUser(userService.getCurrentUser(), pageReq);
//        return news.getContent();
//    }
//
//    public News getNewsById(Long NewsID) {
//        return newsRepository.findById(NewsID);
//    }

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

    public News updateNews(News news) {
        news.setTitle(news.getTitle());
        news.setDescription(news.getDescription());
        news.setPicture(news.getPicture());
        news.setOwner_id(news.getOwner_id());
        news.setCreated_at(news.getCreated_at());
        news.setUpdated_at(news.getUpdated_at());

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

    @Override
    public List<News> getNewsByUserId(Long id) {
        List<News> news = new ArrayList<>(); ;
        List<Subscription> subscriptions = this.subscriptionRepository.findByUserId(id);
        List<Topic> topic = subscriptions.stream().map(Subscription::getTopic).collect(Collectors.toList());

        for (Topic unTopic : topic) {
            List<News> newsList = this.newsRepository.findByTopicId(unTopic.getId());

            for (News uneNews : newsList) {
                news.add(uneNews);
            }
        }

        return news;
    }
}
