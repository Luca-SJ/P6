package com.openclassrooms.mddapi.Mappers;

import com.openclassrooms.mddapi.Dtos.ArticleDTO;
import com.openclassrooms.mddapi.Dtos.TopicDTO;
import com.openclassrooms.mddapi.Models.Article;
import com.openclassrooms.mddapi.Models.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") // Enables integration with Spring
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    @Mapping(target = "created_at", source = "createdAt")
    @Mapping(target = "updated_at", source = "updatedAt")
    TopicDTO toTopicDTO(Topic topic);

    Topic toTopic(TopicDTO topicDTO);

    List<TopicDTO> toListTopicDTO(List<Topic> topics);
}
