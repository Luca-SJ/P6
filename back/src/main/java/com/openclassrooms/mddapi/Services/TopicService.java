package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Dtos.TopicDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Mappers.ArticleMapper;
import com.openclassrooms.mddapi.Mappers.TopicMapper;
import com.openclassrooms.mddapi.Models.Article;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repository.SubscriptionRepository;
import com.openclassrooms.mddapi.Repository.TopicRepository;
import com.openclassrooms.mddapi.Services.Interfaces.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<TopicDTO> findAll() {
        List<Topic> listTopics = topicRepository.findAll();

        return this.topicMapper.toListTopicDTO(listTopics);
    }

    @Override
    public TopicDTO findByID(Long topicID) throws ResourceNotFoundException {
        Topic topic = topicRepository.findById(topicID)
                .orElseThrow(()->new ResourceNotFoundException("Theme inexistant"));

        return this.topicMapper.toTopicDTO(topic);
    }

    @Override
    public TopicDTO findByName(String name) throws ResourceNotFoundException {
        Topic topic = topicRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Theme inexistant"));

        return this.topicMapper.toTopicDTO(topic);
    }

    @Override
    @Transactional
    public void unsubscribeByTopicId(Long topicID) throws ResourceNotFoundException {
        this.subscriptionRepository.deleteByTopicId(topicID);
    }

    @Override
    public TopicDTO updateThemeByID(Long topicID, Topic themeDetails) throws ResourceNotFoundException {
        Topic topic = topicRepository.findById(topicID)
                .orElseThrow(()->new ResourceNotFoundException("Theme avec ID : "+ topicID + " inexistant"));

        topic.setName(themeDetails.getName());
        topic.setDescription(themeDetails.getDescription());

        return this.topicMapper.toTopicDTO(topic);
    }

    @Transactional
    public void createTheme(TopicDTO topicDTO) {
        Topic topic = topicMapper.toTopic(topicDTO);

        this.topicRepository.save(topic);
    }

    @Override
    @Transactional
    public Subscription subscribe(long userId, long topicId) {
        Subscription subscription = new Subscription();

        User user = new User();
        user.setId(userId);

        Topic topic = new Topic();
        topic.setId(topicId);

        subscription.setUser(user);
        subscription.setTopic(topic);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription subscribeTest(long userId, long themeId) {
        return null;
    }

    @Override
    public List<TopicDTO> getTopicsByUserId(Long id) {
        List<Subscription> subscriptions = this.subscriptionRepository.findByUserId(id);

        return topicMapper.toListTopicDTO(subscriptions.stream().map(Subscription::getTopic).collect(Collectors.toList()));
    }
}
