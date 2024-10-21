package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
//    public Optional<Subscription> findByIdUser_IdTheme(long idUser, long idTheme);
}
