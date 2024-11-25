package com.openclassrooms.mddapi.Services.Interfaces;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> findAll();

    User findByID(Long UserID) throws ResourceNotFoundException;

    User findByEmail(String UserEmail) throws ResourceNotFoundException;

    void deleteByID(Long UserID) throws ResourceNotFoundException;

    User updateUserByID(Long UserID, User userDetails) throws ResourceNotFoundException;

    User createUser(User user);

}
