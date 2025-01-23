package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.ArticleDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Mappers.ArticleMapper;
import com.openclassrooms.mddapi.Models.Article;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Repository.ArticlesRepository;
import com.openclassrooms.mddapi.Repository.SubscriptionRepository;
import com.openclassrooms.mddapi.Repository.UserRepository;
import com.openclassrooms.mddapi.Services.Interfaces.IArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticlesService implements IArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleDTO findByID(Long articlesID) {
        Article article = this.articlesRepository.findById(articlesID)
                .orElseThrow(()->new ResourceNotFoundException("Article inexistant"));

        return this.articleMapper.toArticlesDTO(article);
    }

    public void deleteByID(Long articlesID) {
        Article article = articlesRepository.findById(articlesID)
                .orElseThrow(()->new ResourceNotFoundException("Article avec ID : " + articlesID + " inexistant"));

        articlesRepository.delete(article);
    }

    public ArticleDTO updateArticlesByID(Long articlesID, Article articleDetails) {
        Article article = articlesRepository.findById(articlesID)
                .orElseThrow(()->new ResourceNotFoundException("Article avec ID : "+ articlesID + " inexistant"));

        return this.articleMapper.toArticlesDTO(article);
    }

    @Transactional
    public void createArticles(ArticleDTO articleDTO) {
        Article article = articleMapper.toArticles(articleDTO);

        this.articlesRepository.save(article);
    }

    @Override
    public List<ArticleDTO> getArticlesByUserId(Long id) {
        List<Subscription> subscriptions = this.subscriptionRepository.findByUserId(id);
        List<Topic> topics = subscriptions.stream().map(Subscription::getTopic).collect(Collectors.toList());
        List<Article> articleDTO = topics.stream().flatMap(topic -> this.articlesRepository.findByTopicId(topic.getId()).stream()).collect(Collectors.toList());

        return articleMapper.toListArticlesDTO(articleDTO);
    }

}
