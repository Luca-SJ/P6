package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Dtos.TopicDTO;
import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import java.util.List;

 public interface ITopicService {

     //
     List<TopicDTO> findAll();

     /**
      *
      * @param topicID Topic identifier
      * @return {@link Topic}
      * @throws ResourceNotFoundException If the topic is not found, ResourceNotFoundException
      */
     TopicDTO findByID(Long topicID) throws ResourceNotFoundException;

     TopicDTO findByName(String name) throws ResourceNotFoundException;

     void unsubscribeByTopicId(Long topicID) throws ResourceNotFoundException;

     TopicDTO updateThemeByID(Long topicID, Topic topicDetails) throws ResourceNotFoundException;

     void createTheme(TopicDTO topicID);

     Subscription subscribe(long userId, long topicID);

     Subscription subscribeTest(long userId, long topicID);

     /**
      *
      * @param id User identifier
      * @return {@link Topic} List
      */
     List<TopicDTO> getTopicsByUserId(Long id);
 }
