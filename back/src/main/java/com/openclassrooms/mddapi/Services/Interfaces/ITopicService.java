package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import java.util.List;

 public interface ITopicService {

     //
     List<Topic> findAll();

     /**
      *
      * @param topicID Topic identifier
      * @return {@link Topic}
      * @throws ResourceNotFoundException If the topic is not found, ResourceNotFoundException
      */
     Topic findByID(Long topicID) throws ResourceNotFoundException;

     Topic findByName(String name) throws ResourceNotFoundException;

     void unsubscribeByTopicId(Long topicID) throws ResourceNotFoundException;

     Topic updateThemeByID(Long topicID, Topic topicDetails) throws ResourceNotFoundException;

     Topic createTheme(Topic topicID);

     Subscription subscribe(long userId, long topicID);

     Subscription subscribeTest(long userId, long topicID);

     /**
      *
      * @param id User identifier
      * @return {@link Topic} List
      */
     List<Topic> getTopicsByUserId(Long id);
 }
