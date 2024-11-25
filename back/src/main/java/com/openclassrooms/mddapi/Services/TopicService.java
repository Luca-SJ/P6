package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
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

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findByID(Long ThemeID) throws ResourceNotFoundException {
        return topicRepository.findById(ThemeID)
                .orElseThrow(()->new ResourceNotFoundException("Theme inexistant"));
    }

    @Override
    public Topic findByName(String name) throws ResourceNotFoundException {
        return topicRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Theme inexistant"));
    }

    @Override
    @Transactional
    public void unsubscribeByTopicId(Long topicID) throws ResourceNotFoundException {
        this.subscriptionRepository.deleteByTopicId(topicID);
    }

    @Override
    public Topic updateThemeByID(Long ThemeID, Topic themeDetails) throws ResourceNotFoundException {
        Topic theme = topicRepository.findById(ThemeID)
                .orElseThrow(()->new ResourceNotFoundException("Theme avec ID : "+ ThemeID + " inexistant"));

        theme.setName(themeDetails.getName());
        theme.setDescription(themeDetails.getDescription());
        theme.setCreated_at(themeDetails.getCreated_at());
        theme.setUpdated_at(themeDetails.getUpdated_at());

        return topicRepository.save(theme);
    }

    @Override
    public Topic createTheme(Topic theme) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        theme.setCreated_at(sqlDate);
        theme.setDescription(theme.getDescription());
        theme.setName(theme.getName());

        return topicRepository.save(theme);
    }

    @Override
    @Transactional
    public Subscription subscribe(long userId, long topicId) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Subscription subscription = new Subscription();

        User user = new User();
        user.setId(userId);

        Topic topic = new Topic();
        topic.setId(topicId);

        subscription.setUser(user);
        subscription.setTopic(topic);
        subscription.setCreated_at(sqlDate);


        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription subscribeTest(long userId, long themeId) {
        return null;
    }

    @Override
    public List<Topic> getTopicsByUserId(Long id) {
        List<Subscription> subscriptions = this.subscriptionRepository.findByUserId(id);
        return subscriptions.stream().map(Subscription::getTopic).collect(Collectors.toList());
    }
}
