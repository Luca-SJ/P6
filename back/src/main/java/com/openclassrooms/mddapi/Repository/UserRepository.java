package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     *
     * @param email
     * @param name
     * @return Optional
     */
    public Optional<User> findByEmailOrName(String email, String name);

}
