package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    void deleteByTopicId(Long topicId);

    List<Subscription> findByUserId(Long id);
}
